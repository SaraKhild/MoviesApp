package com.example.MoviesApp.utils;

import com.example.MoviesApp.R;
import com.example.MoviesApp.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class DataSource {



    public static List <Movie> getPopularMovies(){

        List<Movie> lsMovies = new ArrayList<>();
        lsMovies.add(new Movie("John3Wick", R.drawable.m1,R.drawable.img1));
        lsMovies.add(new Movie("Spider-Man",R.drawable.m2,R.drawable.img2));
        lsMovies.add(new Movie("The Shawshank",R.drawable.m3 ,R.drawable.img3));
        lsMovies.add(new Movie("The Addams Family",R.drawable.m4,R.drawable.img4));

        return lsMovies;
    }

    public static List<Movie> getWeekMovies(){


        List<Movie> lsMovies = new ArrayList<>();
        lsMovies.add(new Movie("Fantasy Island", R.drawable.m5,R.drawable.img5));
        lsMovies.add(new Movie("Escape",R.drawable.m6,R.drawable.img6));
        lsMovies.add(new Movie("Crawl",R.drawable.m7 ,R.drawable.img7));

        return lsMovies;


    }
}
