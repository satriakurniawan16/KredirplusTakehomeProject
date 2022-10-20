package com.satriakurniawandicoding.submission3.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.satriakurniawandicoding.submission3.R;
import com.satriakurniawandicoding.submission3.model.Movie;
import com.satriakurniawandicoding.submission3.view.database.MovieHelper;

import java.util.ArrayList;

public class DetailMovieActivity extends AppCompatActivity {

    public static String EXTRA_MOVIE = "extra_movie";
    private boolean isFavorite = false;
    private Movie movie;
    private ImageView addFavorite;
    private ImageView removeFavorite;
    MovieHelper movieHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        ImageView imageMovie = findViewById(R.id.detail_image_movie);
        TextView titleMovie = findViewById(R.id.title_image_movie);
        TextView dateMovie = findViewById(R.id.date_movie_catalogue);
        TextView desctiptionMovie = findViewById(R.id.description_movie_catalogue);

        movieHelper = MovieHelper.getInstance(getApplicationContext());
        movieHelper.open();

        addFavorite = findViewById(R.id.add_favorite);
        addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFavorite();
            }
        });

        removeFavorite = findViewById(R.id.remove_favorite);
        removeFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFavorite();
            }
        });

        ImageView backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailMovieActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Glide.with(this)
                .load(movie.getImage())
                .into(imageMovie);
        titleMovie.setText(movie.getTitle());
        dateMovie.setText(movie.getDate());
        desctiptionMovie.setText(movie.getDescription());


        checkFavorite();

    }

    private void checkFavorite() {
        Log.d("test ajooo", "onCreate: " + movie.getType());
        ArrayList<Movie> moviesInDatabase = (ArrayList<Movie>) movieHelper.queryAll();
        for (Movie movie: moviesInDatabase){
            if (this.movie.getId().equals(movie.getId())){
                isFavorite = true;
                Log.d("disini", "checkFavorite: ");
            }
            if (isFavorite == true) {
                Log.d("disini", "checkFavorite: ado");
                break;
            }
        }
        setIconFavorite();
    }

    private void setFavorite(){
        if (isFavorite) {
            movieHelper.deleteMovie(movie.getId());
            Toast.makeText(this, "Dihapus dari favorite", Toast.LENGTH_SHORT).show();
        } else {
            movieHelper.insert(movie);
            Toast.makeText(this, "Ditambahkan ke favorite", Toast.LENGTH_SHORT).show();
        }
        isFavorite = !isFavorite;
        setIconFavorite();
    }

    private void setIconFavorite(){
        if (isFavorite) {
            removeFavorite.setVisibility(View.VISIBLE);
            addFavorite.setVisibility(View.GONE);
        } else {
            addFavorite.setVisibility(View.VISIBLE);
            removeFavorite.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }

}
