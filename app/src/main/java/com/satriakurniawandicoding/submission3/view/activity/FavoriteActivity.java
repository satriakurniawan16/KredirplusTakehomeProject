package com.satriakurniawandicoding.submission3.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.satriakurniawandicoding.submission3.R;
import com.satriakurniawandicoding.submission3.model.Movie;
import com.satriakurniawandicoding.submission3.view.adapter.MovieCatalogueAdapter;
import com.satriakurniawandicoding.submission3.view.database.MovieHelper;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieHelper movieHelper;
    private MovieCatalogueAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Favorite");

        if(getSupportActionBar()!= null) {
            getSupportActionBar().setElevation(0);
        }

        movieHelper = MovieHelper.getInstance(this);
        movieHelper.open();

        recyclerView= findViewById(R.id.rv_favorite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MovieCatalogueAdapter();
        adapter.setData(movies);

        adapter.setOnItemClickCallback(new MovieCatalogueAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                intentDetailMovie(data);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_favorite, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.all:
                showAll();
                return true;
            case R.id.movie:
                showFilter("movies");
                return true;
            case R.id.tv:
                showFilter("tvshow");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showFilter(String type){
        movieHelper.open();
        movies = movieHelper.showFilter(type);
        adapter.setData(movies);
    }

    public void showAll(){
        movieHelper.open();
        movies = movieHelper.queryAll();
        adapter.setData(movies);
    }

    @Override
    public void onResume() {
        super.onResume();
        showAll();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }

    private void intentDetailMovie(Movie movie){
        Intent moveWithObjectIntent = new Intent(FavoriteActivity.this, DetailMovieActivity.class);
        moveWithObjectIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
        startActivity(moveWithObjectIntent);
    }
}
