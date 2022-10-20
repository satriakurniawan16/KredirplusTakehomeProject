package com.satriakurniawandicoding.submission3.view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.satriakurniawandicoding.submission3.model.Movie;

import java.util.ArrayList;

import static com.satriakurniawandicoding.submission3.view.database.DatabaseContract.MovieColumns.DATE;
import static com.satriakurniawandicoding.submission3.view.database.DatabaseContract.MovieColumns.DESCRIPTION;
import static com.satriakurniawandicoding.submission3.view.database.DatabaseContract.MovieColumns.ID;
import static com.satriakurniawandicoding.submission3.view.database.DatabaseContract.MovieColumns.IMAGE;
import static com.satriakurniawandicoding.submission3.view.database.DatabaseContract.MovieColumns.TABLE_NAME;
import static com.satriakurniawandicoding.submission3.view.database.DatabaseContract.MovieColumns.TITLE;
import static com.satriakurniawandicoding.submission3.view.database.DatabaseContract.MovieColumns.TYPE;

public class MovieHelper {

    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper movieDatabaseHelper;
    private static MovieHelper INSTANCE;

    private static SQLiteDatabase database;

    private MovieHelper(Context context) {
        movieDatabaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = movieDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        movieDatabaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<Movie> queryAll() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                ID + " ASC",
                null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setId(cursor.getString(cursor.getColumnIndexOrThrow(ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setImage(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                movie.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                movie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                movie.setType(cursor.getString(cursor.getColumnIndexOrThrow(TYPE)));

                arrayList.add(movie);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public ArrayList<Movie> showFilter(String type) {
        ArrayList<Movie> arrayList = new ArrayList<>();

        String[] whereArgs = new String[] {
                type
        };

        Cursor cursor = database.query(DATABASE_TABLE, null,
                "type=?",
                 whereArgs,
                null,
                null,
                ID + " ASC",
                null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                        movie.setId(cursor.getString(cursor.getColumnIndexOrThrow(ID)));
                        movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                        movie.setImage(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                        movie.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                        movie.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                        movie.setType(cursor.getString(cursor.getColumnIndexOrThrow(TYPE)));
                        arrayList.add(movie);
                        cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Movie movie) {
        ContentValues args = new ContentValues();
        args.put(ID, movie.getId());
        args.put(TITLE, movie.getTitle());
        args.put(IMAGE, movie.getImage());
        args.put(DESCRIPTION, movie.getDescription());
        args.put(DATE, movie.getDate());
        args.put(TYPE, movie.getType());

        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteMovie(String id) {
        return database.delete(TABLE_NAME, ID + " = '" + id + "'", null);
    }
}