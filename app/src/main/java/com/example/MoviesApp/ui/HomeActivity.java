package com.example.MoviesApp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.MoviesApp.models.Movie;
import com.example.MoviesApp.adapters.MovieAdapter;
import com.example.MoviesApp.adapters.MovieItemClickListener;
import com.example.MoviesApp.R;
import com.example.MoviesApp.models.Slide;
import com.example.MoviesApp.adapters.SliderPagerAdapter;
import com.example.MoviesApp.utils.DataSource;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements MovieItemClickListener {


    private List<Slide> lstSlides;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView MoviesRV,MoviesRvWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iniViews();

        //prepare a list of slides

        iniSlider();

        //Recycleview setup

        iniPopularMovies();
        iniWeekMovies();


    }//end onCreateMethod

    private void iniWeekMovies() {

       MovieAdapter weekMoviesAdapter = new MovieAdapter(this,DataSource.getWeekMovies(),this);
       MoviesRvWeek.setAdapter(weekMoviesAdapter);
        MoviesRvWeek.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));// make it horizantal
    }

    private void iniPopularMovies() {


        MovieAdapter movieAdapter = new MovieAdapter(this, DataSource.getPopularMovies(),this);
        MoviesRV.setAdapter(movieAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void iniSlider() {
        lstSlides = new ArrayList<>();
        lstSlides.add(new Slide(R.drawable.slide1, "Slide Title /n more text here"));
        lstSlides.add(new Slide(R.drawable.slide2, "Slide Title /n more text here"));
        lstSlides.add(new Slide(R.drawable.slide1, "Slide Title /n more text here"));
        lstSlides.add(new Slide(R.drawable.slide2, "Slide Title /n more text here"));

        SliderPagerAdapter adapter = new SliderPagerAdapter(this,lstSlides);
        sliderpager.setAdapter(adapter);

        //setp timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);

        indicator.setupWithViewPager(sliderpager,true);
    }

    private void iniViews() {
        sliderpager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        MoviesRV = findViewById(R.id.Rv_movies);
        MoviesRvWeek=findViewById(R.id.Rv_movies_week);
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {



        Intent intent = new Intent(this, MovieDetail.class);
        // send movie information to deatilActivity
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());

         //  intent.putExtra("imgCover",movie.getCoverPhoto());
        // lets crezte the animation
         ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,
                movieImageView,"sharedName");

        startActivity(intent,options.toBundle());



        // i l make a simple test to see if the click works

        Toast.makeText(this,"item clicked : " + movie.getTitle(),Toast.LENGTH_LONG).show();
        // it works great






    }


    class SliderTimer extends TimerTask{


        @Override
        public void run() {

            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    if(sliderpager.getCurrentItem()<lstSlides.size()-1){

                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);

                    }else
                    {

                        sliderpager.setCurrentItem(0);
                    }


                }
            });

        }
    }
}//end class
