package com.satriakurniawandicoding.submission3.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.satriakurniawandicoding.submission3.R;
import com.satriakurniawandicoding.submission3.model.Movie;

import java.util.ArrayList;

public class MovieCatalogueAdapter extends RecyclerView.Adapter<MovieCatalogueAdapter.MovieCatalogueViewHolder> {
    private ArrayList<Movie> mData = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    public void setData(ArrayList<Movie> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }


    @NonNull
    @Override
    public MovieCatalogueViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_catalogue, viewGroup, false);
        return new MovieCatalogueViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCatalogueViewHolder movieCatalogueViewHolder, int position) {
        movieCatalogueViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieCatalogueViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImage;
        TextView movieTitle;
        TextView movieDescription;
        TextView movieDate;

        MovieCatalogueViewHolder(@NonNull View itemView) {

            super(itemView);
            movieImage = itemView.findViewById(R.id.image_catalogue);
            movieTitle = itemView.findViewById(R.id.title_catalogue);
            movieDescription = itemView.findViewById(R.id.description_catalogue);
            movieDate = itemView.findViewById(R.id.date_catalogue);

        }

        void bind(Movie movie) {
            Glide.with(itemView.getContext())
                    .load(movie.getImage())
                    .into(movieImage);

            movieTitle.setText(movie.getTitle());
            movieDescription.setText(movie.getDescription());
            movieDate.setText(movie.getDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClicked(mData.get(getAdapterPosition()));
                }
            });

        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Movie data);
    }
}