package com.satriakurniawandicoding.submission3.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.satriakurniawandicoding.submission3.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {

    private static final String API_KEY = "b1abc908274aa3653ce0c504edd3205a&";
    private MutableLiveData<ArrayList<Movie>> listMovie = new MutableLiveData<>();

    public void setWeather(Context context, final String language, final String type) {

        final ArrayList<Movie> listItemMovie = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/"+type+"?api_key="+API_KEY+"language="+language;
        Log.d("mamaaay", "setWeather: " + url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.length() > 0){
                    try {
                        JSONArray jsonArray = response.getJSONArray("results");
                        for (int j = 0; j < jsonArray.length() ; j++) {
                            JSONObject dataMovie = jsonArray.getJSONObject(j);
                            Movie movie = new Movie();
                           if(type.equals("movie")) {
                               movie.setId(dataMovie.getString("id"));
                               movie.setTitle(dataMovie.getString("title"));
                               movie.setDescription(dataMovie.getString("overview"));
                               movie.setImage("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + dataMovie.getString("poster_path"));
                               movie.setDate(dataMovie.getString("release_date"));
                               movie.setType("movies");
                               listItemMovie.add(movie);
                           }else{
                               movie.setId(dataMovie.getString("id"));
                               movie.setTitle(dataMovie.getString("original_name"));
                               movie.setDescription(dataMovie.getString("overview"));
                               movie.setImage("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + dataMovie.getString("poster_path"));
                               movie.setDate(dataMovie.getString("first_air_date"));
                               movie.setType("tvshow");
                               listItemMovie.add(movie);
                           }
                        }
                        listMovie.postValue(listItemMovie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("mama", "onResponse: " + e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }

    public LiveData<ArrayList<Movie>> getMovie() {
        return listMovie;
    }

}
