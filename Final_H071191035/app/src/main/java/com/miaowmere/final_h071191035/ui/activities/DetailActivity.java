package com.miaowmere.final_h071191035.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import com.miaowmere.final_h071191035.ImageSize;
import com.miaowmere.final_h071191035.R;
import com.miaowmere.final_h071191035.data.api.repository.callback.OnTvDetailCallback;
import com.miaowmere.final_h071191035.data.api.repository.TvShowRepository;
import com.miaowmere.final_h071191035.data.local.FavoriteHelper;
import com.miaowmere.final_h071191035.data.models.TvShow;

public class DetailActivity extends AppCompatActivity {
    private int id;
    private TvShowRepository repository;
    private ImageView ivBackdrop;
    private ImageView ivPoster;
    private TextView tvName;
    private RatingBar rbRate;
    private TextView tvFirstAirDate;
    private TextView tvLastAirDate;
    private TextView tvSeason;
    private TextView tvEpisode;
    private TextView tvOverview;
    private TextView tvError;
    private FavoriteHelper helper;
    private TvShow tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setActionBar("");
        ivBackdrop = findViewById(R.id.iv_backdrop);
        ivPoster = findViewById(R.id.iv_poster);
        rbRate = findViewById(R.id.rb_rating);
        tvName = findViewById(R.id.tv_name);
        tvFirstAirDate = findViewById(R.id.tv_first_air_date);
        tvLastAirDate = findViewById(R.id.tv_last_air_date);
        tvSeason = findViewById(R.id.tv_season);
        tvEpisode = findViewById(R.id.tv_episode);
        tvOverview = findViewById(R.id.tv_overview);
        tvError = findViewById(R.id.tv_error);
        if (getIntent() != null) {
            id = getIntent().getIntExtra("ID", 0);
        }
        repository = TvShowRepository.getInstance();
        helper = new FavoriteHelper(this);
        getRepositoryData(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_detail_activity, menu);
        // TODO: switch favourite button state
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent mainActivity = new Intent(DetailActivity.this, MainActivity.class);
                mainActivity.putExtra("SELECTED_FRAGMENT", getIntent().getStringExtra("SELECTED_FRAGMENT"));
                startActivity(mainActivity);
                return true;
            case R.id.item_favorite:
                if (helper.isFavorite(id)) {
                    if (helper.delete(id) > 0) {
                        // TODO: Set favorite button state
                    }
                } else {
                    if (helper.insert(tvShow) > 0) {
                        // TODO: Set favorite button state
                    }
                }
                return true;
//            case R.id.item_language_setting:
//                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getRepositoryData(int id) {
        repository.getTvDetail(id, new OnTvDetailCallback() {
            @Override
            public void onSuccess(TvShow tvShow, String message) {
                onBindView(tvShow);
                // TODO: Hide error text
            }

            @Override
            public void onFailure(String message) {
                tvError.setText(message);
            }
        });
    }

    private void onBindView(TvShow tvShow) {
        this.tvShow = tvShow;
        setActionBar(tvShow.getName());
        Glide.with(this)
                .load(tvShow.getBackdropPath(ImageSize.W500))
                .into(ivBackdrop);
        Glide.with(this)
                .load(tvShow.getPosterPath(ImageSize.W154))
                .into(ivPoster);
        tvName.setText(tvShow.getName());
        rbRate.setRating(tvShow.getVoteAverage() / 2);
        tvLastAirDate.setText(tvShow.getLastAirDate());
        tvFirstAirDate.setText(tvShow.getFirstAirDate());
        tvEpisode.setText(String.valueOf(tvShow.getNumberOfEpisode()));
        tvSeason.setText(String.valueOf(tvShow.getNumberOfSeaon()));
        tvOverview.setText(tvShow.getOverview());
    }

    private void setActionBar(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}