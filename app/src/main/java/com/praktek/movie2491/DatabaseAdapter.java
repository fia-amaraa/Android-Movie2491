package com.praktek.movie2491;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.praktek.movie2491.model.Movie;

import java.util.ArrayList;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.ViewHolder> {

    private ArrayList<Movie> listMovie;
    private Movie movie;

    private Context mContext;

    public DatabaseAdapter(Context context, ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_db,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        movie = listMovie.get(position);

        holder.txtTitledb.setText(movie.getTitle());
        holder.txtYeardb.setText(movie.getReleaseDate());
        holder.txtOverviewdb.setText(movie.getOverview());

        Glide.with(holder.itemView)
                .load(movie.getPosterPath())
                .into(holder.imgPosterdb);

        Log.d("Testing PosterPath", "Testing Link"+movie.getPosterPath());
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPosterdb;
        TextView txtTitledb;
        TextView txtOverviewdb;
        TextView txtYeardb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPosterdb = itemView.findViewById(R.id.imgPosterdb);
            txtTitledb = itemView.findViewById(R.id.txtTitledb);
            txtOverviewdb = itemView.findViewById(R.id.txtOverviewdb);
            txtYeardb = itemView.findViewById(R.id.txtYeardb);
        }
    }
}
