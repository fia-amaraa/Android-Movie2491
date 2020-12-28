package com.praktek.movie2491;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.praktek.movie2491.model.Movie;
import com.praktek.movie2491.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder> {

    private ArrayList<Movie> listMovie;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public MovieAdapter(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        final Movie movie = listMovie.get(position);

        holder.txtTitle.setText(movie.getTitle());
        holder.txtYear.setText(movie.getReleaseDate());
        holder.txtOverview.setText(movie.getOverview());

        Glide.with(holder.itemView)
                .load("https://image.tmdb.org/t/p/w200/"+movie.getPosterPath())
                .into(holder.imgPoster);

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtYear;
        TextView txtOverview;
        ImageView imgPoster;
        Button btnDetail;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtYear = itemView.findViewById(R.id.txtYear);
            txtOverview = itemView.findViewById(R.id.txtOverview);
            imgPoster = itemView.findViewById(R.id.imgPoster);
            btnDetail = itemView.findViewById(R.id.btnDetail);
        }
    }

}
