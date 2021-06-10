package com.miaowmere.finalproject_h071191035.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnCastCallback;
import com.miaowmere.finalproject_h071191035.data.models.Cast;
import com.miaowmere.finalproject_h071191035.data.models.FavoriteMovie;
import com.miaowmere.finalproject_h071191035.data.models.FavoriteTvShow;
import com.miaowmere.finalproject_h071191035.data.models.Genre;
import com.miaowmere.finalproject_h071191035.ui.adapters.CastAdapter;
import com.miaowmere.finalproject_h071191035.ui.adapters.GenreAdapter;
import com.miaowmere.finalproject_h071191035.utilities.ImageSize;
import com.miaowmere.finalproject_h071191035.R;
import com.miaowmere.finalproject_h071191035.data.api.repository.MovieRepository;
import com.miaowmere.finalproject_h071191035.data.api.repository.TvShowRepository;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnDetailCallback;
import com.miaowmere.finalproject_h071191035.data.models.Movie;
import com.miaowmere.finalproject_h071191035.data.models.TvShow;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

public class DetailActivity extends AppCompatActivity {
    private ImageView ivBackdrop, ivPoster;
    private TextView tvName, tvFirstAirDate, tvLastAirDate, tvSeason, tvEpisode, tvError,
            tvLabelFirstAirDate, tvLabelLastAirDate, tvLabelEpisode, tvLabelSeason, tvRating, tvRuntime, tvVoteCount;
    private RatingBar rbRating;
    private ExpandableTextView etvOverview;
    private ArrayList<String> genres;
    private LinearProgressIndicator linearProgressIndicator;
    private TvShowRepository tvShowRepository;
    private MovieRepository movieRepository;
    private RecyclerView rvGenre;
    private RecyclerView rvCast;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Realm.init(DetailActivity.this);
        String realmTitle = "final project";
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).name(realmTitle).build();
        realm = Realm.getInstance(configuration);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

        ivBackdrop = findViewById(R.id.iv_banner_detail);
        ivPoster = findViewById(R.id.iv_poster_detail);
        tvName = findViewById(R.id.tv_full_title_detail);
        rbRating = findViewById(R.id.rb_vote_average_detail);
        tvFirstAirDate = findViewById(R.id.tv_first_air_date);
        tvLastAirDate = findViewById(R.id.tv_last_air_date);
        tvSeason = findViewById(R.id.tv_season);
        tvEpisode = findViewById(R.id.tv_episode);
        etvOverview = findViewById(R.id.etv_overview_detail);
        tvError = findViewById(R.id.tv_no_internet_connection);
        tvLabelEpisode = findViewById(R.id.tv_episode_label);
        tvLabelSeason = findViewById(R.id.tv_season_label);
        tvLabelFirstAirDate = findViewById(R.id.tv_first_air_date_label);
        tvLabelLastAirDate = findViewById(R.id.tv_first_air_date_label);
        tvRating = findViewById(R.id.tv_vote_average_detail);
        tvRuntime = findViewById(R.id.tv_runtime);
        tvVoteCount = findViewById(R.id.tv_vote_count);
//        tvGenresDetail = findViewById(R.id.tv_genres_detail);
        genres = new ArrayList<>();
        rvGenre = findViewById(R.id.rv_genre);
        rvCast = findViewById(R.id.rv_cast);
        linearProgressIndicator = findViewById(R.id.lpi_detail);
        tvShowRepository = TvShowRepository.getInstance();
        movieRepository = MovieRepository.getInstance();
    }

    private FavoriteMovie filterFavoriteMovieById(int id) {
        RealmQuery<FavoriteMovie> query = realm.where(FavoriteMovie.class);
        query.equalTo("id", getIntent().getIntExtra("ID", 0));
        FavoriteMovie favoriteMovie = query.findFirst();
        return favoriteMovie;
    }

    private FavoriteTvShow filterFavoriteTvShowById(int id) {
        RealmQuery<FavoriteTvShow> query = realm.where(FavoriteTvShow.class);
        query.equalTo("id", getIntent().getIntExtra("ID", 0));
        FavoriteTvShow favoriteTvShow = query.findFirst();
        return favoriteTvShow;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_detail, menu);
        String selectedFragment = getIntent().getStringExtra("SELECTED_FRAGMENT");
        if (selectedFragment.equals("movie")) {
            FavoriteMovie favoriteMovie = filterFavoriteMovieById(getIntent().getIntExtra("ID", 0));
            if (favoriteMovie != null) {
                menu.findItem(R.id.item_favorite).setIcon(R.drawable.ic_favorite);
            }
        } else {
            FavoriteTvShow favoriteTvShow = filterFavoriteTvShowById(getIntent().getIntExtra("ID", 0));
            if (favoriteTvShow != null) {
                menu.findItem(R.id.item_favorite).setIcon(R.drawable.ic_favorite);
            }
        }
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
            case R.id.item_favorite:
                if (getIntent().getStringExtra("SELECTED_FRAGMENT").equals("movie")) {
                    FavoriteMovie favoriteMovie = filterFavoriteMovieById(getIntent().getIntExtra("ID", 0));
                    if (favoriteMovie != null) {
                        realm.executeTransaction(transactionRealm -> {
                            favoriteMovie.deleteFromRealm();
                        });
                        item.setIcon(R.drawable.ic_favorite_border);
                    } else {
                        FavoriteMovie movie = new FavoriteMovie();
                        String title = getIntent().getStringExtra("TITLE");
                        String posterPath = getIntent().getStringExtra("POSTER_PATH");
                        int id = getIntent().getIntExtra("ID", 0);
                        movie.setId(id);
                        movie.setTitle(title);
                        movie.setPosterPath(posterPath);
                        realm.executeTransaction(transactionRealm -> transactionRealm.insert(movie));
                        Log.d("Favorite Movie", movie.getTitle());
                        item.setIcon(R.drawable.ic_favorite);
                    }
                } else {
                    FavoriteTvShow favoriteTvShow = filterFavoriteTvShowById(getIntent().getIntExtra("ID", 0));
                    if (favoriteTvShow != null) {
                        realm.executeTransaction(transactionRealm -> {
                            favoriteTvShow.deleteFromRealm();
                        });
                        item.setIcon(R.drawable.ic_favorite_border);
                    } else {
                        FavoriteTvShow tvShow = new FavoriteTvShow();
                        String title = getIntent().getStringExtra("TITLE");
                        String posterPath = getIntent().getStringExtra("POSTER_PATH");
                        int id = getIntent().getIntExtra("ID", 0);
                        tvShow.setId(id);
                        tvShow.setTitle(title);
                        tvShow.setPosterPath(posterPath);
                        realm.executeTransaction(transactionRealm -> transactionRealm.insert(tvShow));
                        Log.d("Favorite Movie", tvShow.getTitle());
                        item.setIcon(R.drawable.ic_favorite);
                    }
                }
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
            tvShowRepository.getModelDetail(id, new OnDetailCallback<TvShow>() {
                @Override
                public void onSuccess(TvShow media, String message) {
                    String imageUri = media.getPosterPath(ImageSize.W154);
                    String backdropUri = media.getBackdropPath(ImageSize.W200);
                    float rating = (float) (media.getVoteAverage() / 2.0);
                    Log.d("RATING", Float.toString(rating));
                    Glide.with(DetailActivity.this).load(imageUri).into(ivPoster);
                    Glide.with(DetailActivity.this).load(backdropUri).into(ivBackdrop);
                    tvName.setText(media.getName());
                    tvFirstAirDate.setText(media.getFirstAirDate());
                    tvLastAirDate.setText(media.getLastAirDate());
                    tvEpisode.setText(Integer.toString(media.getNumberOfEpisode()));
                    etvOverview.setText(media.getOverview());
                    tvSeason.setText(Integer.toString(media.getNumberOfSeason()));
                    rbRating.setRating(rating);
                    setGenres(media.getGenres());
                    Log.d("Genre", media.getGenres().get(0).getName());
                    rvGenre.setLayoutManager(new LinearLayoutManager(DetailActivity.this, RecyclerView.HORIZONTAL, false));
                    rvGenre.setAdapter(new GenreAdapter(genres, DetailActivity.this));
                    loadCast(id, selectedFragment);
                    tvRating.setText(Float.toString(media.getVoteAverage()));
                    tvRuntime.setText(media.getRuntime());
                    tvVoteCount.setText(media.getVoteCount());
                    setActionBar(media.getName());
                    linearProgressIndicator.hide();
                }

                @Override
                public void onFailure(String message) {
                    // TODO error text
                }
            });
        } else {
            movieRepository.getModelDetail(id, new OnDetailCallback<Movie>() {
                @Override
                public void onSuccess(Movie media, String message) {
                    Log.d("MOVIE TITLE", media.getTitle());
                    String imageUri = media.getPosterPath(ImageSize.W154);
                    String backdropUri = media.getBackdropPath(ImageSize.W200);
                    float rating = (float) (media.getVoteAverage() / 2.0);
                    Log.d("RATING", Float.toString(rating));
                    Glide.with(DetailActivity.this).load(imageUri).into(ivPoster);
                    Glide.with(DetailActivity.this).load(backdropUri).into(ivBackdrop);
                    tvName.setText(media.getTitle());
                    tvFirstAirDate.setText((media.getReleaseDate()));
                    tvLastAirDate.setText(media.getReleaseDate());
                    etvOverview.setText(media.getOverview());
                    tvLabelEpisode.setVisibility(View.GONE);
                    tvEpisode.setVisibility(View.GONE);
                    tvLabelSeason.setVisibility(View.GONE);
                    tvSeason.setVisibility(View.GONE);
                    rbRating.setRating(rating);
                    setGenres(media.getGenres());
                    Log.d("Genre", media.getGenres().get(0).getName());
                    rvGenre.setLayoutManager(new LinearLayoutManager(DetailActivity.this, RecyclerView.HORIZONTAL, false));
                    rvGenre.setAdapter(new GenreAdapter(genres, DetailActivity.this));
                    loadCast(id, selectedFragment);
                    tvRating.setText(Float.toString(media.getVoteAverage()));
                    tvRuntime.setText(media.getRuntime());
                    tvVoteCount.setText(media.getVoteCount());
                    setActionBar(media.getTitle());
                    linearProgressIndicator.hide();
                }

                @Override
                public void onFailure(String message) {
                    // TODO error text
                }
            });
        }
    }

    private void loadCast(Integer id, String selectedFragment) {
        if (selectedFragment.equals("movie")) {
            movieRepository.getCast(id, new OnCastCallback() {
                @Override
                public void onSuccess(List<Cast> casts, String message) {
                    Log.d("Cast", casts.get(0).getName());
                    rvCast.setLayoutManager(new LinearLayoutManager(DetailActivity.this, RecyclerView.HORIZONTAL, false));
                    rvCast.setAdapter(new CastAdapter(casts, DetailActivity.this));
                }

                @Override
                public void onFailure(String message) {
                    Log.d("Error Fetching", message);
                }
            });
        } else {
            tvShowRepository.getCast(id, new OnCastCallback() {
                @Override
                public void onSuccess(List<Cast> casts, String message) {
                    rvCast.setLayoutManager(new LinearLayoutManager(DetailActivity.this, RecyclerView.HORIZONTAL, false));
                    rvCast.setAdapter(new CastAdapter(casts, DetailActivity.this));
                }

                @Override
                public void onFailure(String message) {
                    Log.d("Error Fetching", message);
                }
            });
        }
    }

    private void setGenres(List<Genre> genreList) {
        for (int i = 0; i < genreList.size(); i++) {
            genres.add(genreList.get(i).getName());
        }
    }

    private void setActionBar(String title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#292929")));
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}