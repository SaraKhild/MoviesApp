package com.example.MoviesApp.adapters;

import android.widget.ImageView;

import com.example.MoviesApp.models.Movie;

public interface MovieItemClickListener {


    void onMovieClick(Movie movie , ImageView movieImageView);
}
