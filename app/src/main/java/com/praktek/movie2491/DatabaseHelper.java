package com.praktek.movie2491;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.praktek.movie2491.model.Movie;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_movie";
    private static final int DATABASE_VERSION= 1;

    private static final String TABLE_NAME = "table_movie";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_OVERVIEW = "overview";
    private static final String COLUMN_DATE = "date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_IMAGE + " TEXT," +
                        COLUMN_TITLE + " TEXT," +
                        COLUMN_OVERVIEW + " TEXT," +
                        COLUMN_DATE + " DATE" +
                        ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public ArrayList<Movie> getAllMovie() {
        ArrayList<Movie> listMovie = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_IMAGE, COLUMN_TITLE, COLUMN_OVERVIEW, COLUMN_DATE}, null, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            do {
                Movie movie = new Movie();

                movie.setId(Integer.parseInt(c.getString(0)));
                movie.setPosterPath(c.getString(1));
                movie.setTitle(c.getString(2));
                movie.setOverview(c.getString(3));
                movie.setReleaseDate(c.getString(4));

                listMovie.add(movie);

            } while (c.moveToNext());
        }

        db.close();
        return listMovie;
    }

    public void addMovie(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_IMAGE,movie.getPosterPath());
        values.put(COLUMN_TITLE,movie.getTitle());
        values.put(COLUMN_OVERVIEW,movie.getOverview());
        values.put(COLUMN_DATE,movie.getReleaseDate());

        db.insert(TABLE_NAME,null, values);
        db.close();
    }
}
