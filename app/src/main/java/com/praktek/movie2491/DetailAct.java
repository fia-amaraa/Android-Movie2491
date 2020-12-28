package com.praktek.movie2491;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.praktek.movie2491.model.Movie;

public class DetailAct extends AppCompatActivity {

    public static final String ITEM_EXTRA = "item_extra";
    ImageView img_detail;
    TextView title_detail;
    TextView date_detail;
    TextView overview_detail;
    Button btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img_detail= findViewById(R.id.img_detail);
        title_detail = findViewById(R.id.title_detail);
        date_detail = findViewById(R.id.date_detail);
        overview_detail = findViewById(R.id.overview_detail);
        btnFavorite = findViewById(R.id.btnFavorite);

        final Movie movie = getIntent().getParcelableExtra(ITEM_EXTRA);

        if (movie != null) {
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w200/"+movie.getPosterPath())
                    .into(img_detail);
            title_detail.setText(movie.getTitle());
            date_detail.setText(movie.getReleaseDate());
            overview_detail.setText(movie.getOverview());
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Movie");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = title_detail.getText().toString();
                String date = date_detail.getText().toString();
                String overview = overview_detail.getText().toString();

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                Movie mv = new Movie();

                mv.setPosterPath("https://image.tmdb.org/t/p/w200/"+movie.getPosterPath());
                mv.setTitle(title);
                mv.setReleaseDate(date);
                mv.setOverview(overview);

                db.addMovie(mv);

                Intent main = new Intent(DetailAct.this,MainActivity.class);
                startActivity(main);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}