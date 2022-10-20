package com.satriakurniawandicoding.submission3.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.satriakurniawandicoding.submission3.R;
import com.satriakurniawandicoding.submission3.model.Movie;
import com.satriakurniawandicoding.submission3.view.activity.DetailMovieActivity;
import com.satriakurniawandicoding.submission3.view.activity.MainActivity;
import com.satriakurniawandicoding.submission3.view.adapter.MovieCatalogueAdapter;
import com.satriakurniawandicoding.submission3.viewmodel.MovieViewModel;

import java.util.ArrayList;


public class TVShowFragment extends Fragment {

    private MovieCatalogueAdapter listMovieAdapter;
    private ProgressBar progressBar;
    private MovieViewModel movieViewModel;

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MainActivity activity = (MainActivity) getActivity();
        String stringLanguage = activity.sendData();

        progressBar = view.findViewById(R.id.progressBar);

        movieViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);

        listMovieAdapter = new MovieCatalogueAdapter();
        listMovieAdapter.setOnItemClickCallback(new MovieCatalogueAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                intentDetailMovie(data);
            }
        });
        listMovieAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listMovieAdapter);

        movieViewModel.getMovie().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movieItems) {
                if (movieItems != null) {
                    listMovieAdapter.setData(movieItems);
                    showLoading(false);
                }
            }
        });

        movieViewModel.setWeather(getContext(),stringLanguage,"tv");
        showLoading(true);

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void intentDetailMovie(Movie movie){
        Intent moveWithObjectIntent = new Intent(getContext(), DetailMovieActivity.class);
        moveWithObjectIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
        startActivity(moveWithObjectIntent);
    }

}
