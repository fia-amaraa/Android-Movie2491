package com.praktek.movie2491.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.praktek.movie2491.DatabaseAdapter;
import com.praktek.movie2491.DatabaseHelper;
import com.praktek.movie2491.R;
import com.praktek.movie2491.model.Movie;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    RecyclerView rvMoviedb;
    private ArrayList<Movie> listMovie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        rvMoviedb = view.findViewById(R.id.rv_movie_fav);

        rvMoviedb.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseHelper db = new DatabaseHelper(getContext());
        listMovie = db.getAllMovie();

        if (listMovie.size() != 0) {
            DatabaseAdapter adapter = new DatabaseAdapter(getContext(), listMovie);
            rvMoviedb.setAdapter(adapter);
        }

        return view;
    }
}