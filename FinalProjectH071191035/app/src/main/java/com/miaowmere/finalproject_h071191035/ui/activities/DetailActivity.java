package com.miaowmere.finalproject_h071191035.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.miaowmere.finalproject_h071191035.ImageSize;
import com.miaowmere.finalproject_h071191035.R;
import com.miaowmere.finalproject_h071191035.data.api.repository.MovieRepository;
import com.miaowmere.finalproject_h071191035.data.api.repository.TvShowRepository;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnDetailCallback;
import com.miaowmere.finalproject_h071191035.data.models.Movie;
import com.miaowmere.finalproject_h071191035.data.models.TvShow;

public class DetailActivity extends AppCompatActivity {
    private ImageView ivBackdrop, ivPoster;
    private TextView tvName, tvFirstAirDate, tvLastAirDate, tvSeason, tvEpisode, tvOverview, tvError,
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
        tvFirstAirDate = findViewById(R.id.tv_first_air_date);
        tvLastAirDate = findViewById(R.id.tv_last_air_date);
        tvSeason = findViewById(R.id.tv_season);
        tvEpisode = findViewById(R.id.tv_episode);
        tvOverview = findViewById(R.id.etv_overview_detail);
        tvError = findViewById(R.id.tv_no_internet_connection);
        tvLabelEpisode = findViewById(R.id.tv_episode_label);
        tvLabelSeason = findViewById(R.id.tv_season_label);
        tvLabelFirstAirDate = findViewById(R.id.tv_first_air_date_label);
        tvLabelLastAirDate = findViewById(R.id.tv_first_air_date_label);
        tvShowRepository = TvShowRepository.getInstance();
        movieRepository = MovieRepository.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_detail, menu);
        // TODO: switch favourite button state
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent mainActivity = new Intent(DetailActivity.this, MainActivity.class);
                mainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mainActivity.putExtra("SELECTED_FRAGMENT", getIntent().getStringExtra("SELECTED_FRAGMENT"));
                startActivity(mainActivity);
                return true;
            case R.id.menu_item_favorite:
//                if (helper.isFavorite(id)) {
//                    if (helper.delete(id) > 0) {
//                        // TODO: Set favorite button state
//                    }
//                } else {
//                    if (helper.insert(tvShow) > 0) {
//                        // TODO: Set favorite button state
//                    }
//                }
                return true;
            case R.id.item_language_setting:
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Integer id = getIntent().getIntExtra("ID", 0);
        Log.d("MEDIA ID", id.toString());
        String selectedFragment = getIntent().getStringExtra("SELECTED_FRAGMENT");
        Log.d("SELECTED FRAGMENT", selectedFragment);
        load(id, selectedFragment);
    }

    private void load(Integer id, String selectedFragment) {
        if (selectedFragment.equals("tv_show")) {
            tvShowRepository.getTvDetail(id, new OnDetailCallback<TvShow>() {
                @Override
                public void onSuccess(TvShow media, String message) {
                    String imageUri = media.getPosterPath(ImageSize.W154);
                    String backdropUri = media.getBackdropPath(ImageSize.W200);
                    float rating = (float) (media.getVoteAverage() / 2.0);
                    Log.d("RATING", Float.toString(rating));
                    Glide.with(DetailActivity.this)
                            .load(imageUri)
                            .into(ivPoster);
                    Glide.with(DetailActivity.this)
                            .load(backdropUri)
                            .into(ivBackdrop);
                    tvName.setText(media.getName());
                    tvFirstAirDate.setText(media.getFirstAirDate());
                    tvLastAirDate.setText(media.getLastAirDate());
                    tvEpisode.setText(Integer.toString(media.getNumberOfEpisode()));
                    tvOverview.setText(media.getOverview());
                    tvSeason.setText(Integer.toString(media.getNumberOfSeason()));
                    rbRating.setRating(rating);
                    setActionBar(media.getName());
                }

                @Override
                public void onFailure(String message) {
                    // TODO error text
                }
            });
        } else {
            movieRepository.getMovieDetail(id, new OnDetailCallback<Movie>() {
                @Override
                public void onSuccess(Movie media, String message) {
                    Log.d("MOVIE TITLE", media.getTitle());
                    String imageUri = media.getPosterPath(ImageSize.W154);
                    String backdropUri = media.getBackdropPath(ImageSize.W200);
                    float rating = (float) (media.getVoteAverage() / 2.0);
                    Log.d("RATING", Float.toString(rating));
                    Glide.with(DetailActivity.this)
                            .load(imageUri)
                            .into(ivPoster);
                    Glide.with(DetailActivity.this)
                            .load(backdropUri)
                            .into(ivBackdrop);
                    tvName.setText(media.getTitle());
                    tvOverview.setText(media.getOverview());
                    rbRating.setRating(rating);
                    tvLabelEpisode.setVisibility(View.GONE);
                    tvLabelSeason.setVisibility(View.GONE);
                    tvLabelFirstAirDate.setVisibility(View.GONE);
                    tvLabelLastAirDate.setVisibility(View.GONE);
                    setActionBar(media.getTitle());
                }

                @Override
                public void onFailure(String message) {
                    // TODO error text
                }
            });
        }
    }

    private void setActionBar(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}