package com.miaowmere.finalproject_h071191035.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.miaowmere.finalproject_h071191035.R;
import com.miaowmere.finalproject_h071191035.data.api.repository.MovieRepository;
import com.miaowmere.finalproject_h071191035.data.api.repository.TvShowRepository;

public class DetailActivity extends AppCompatActivity {
    private ImageView ivBackdrop, ivPoster;
    private TextView tvName, tvFirstAirDate, lastAirDate, tvSeason, tvEpisode, tvOverview, tvError,
            tvLabelFirstAirDate, tvLabelLastAirDate, tvLabelEpisode, tvLabelSeason;
    private RatingBar rbRating;
    private TvShowRepository tvShowRepository;
    private MovieRepository movieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivBackdrop = findViewById(R.id.iv_banner_detail);
        ivPoster = findViewById(R.id.iv_poster_detail);
        tvName = findViewById(R.id.tv_full_title_detail);
        rbRating = findViewById(R.id.rb_vote_average_detail);
//        tvFirstAirDate = findViewById(R.id.tv_first_air_date);
//        tvLastAirDate = findViewById(R.id.tv_last_air_date);
//        tvSeason = findViewById(R.id.tv_season);
//        tvEpisode = findViewById(R.id.tv_episode);
        tvOverview = findViewById(R.id.etv_overview_detail);
        tvError = findViewById(R.id.tv_no_internet_connection);
//        tvLabelEpisode = findViewById(R.id.label_episode);
//        tvLabelSeason = findViewById(R.id.label_season);
//        tvLabelFirstAirDate = findViewById(R.id.label_first_air_date);
//        tvLabelLastAirDate = findViewById(R.id.label_last_air_date);
        tvShowRepository = TvShowRepository.getInstance();
        movieRepository = MovieRepository.getInstance();
    }
}