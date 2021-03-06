package com.miaowmere.finalproject_h071191035.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.miaowmere.finalproject_h071191035.R;
import com.miaowmere.finalproject_h071191035.data.api.repository.MovieRepository;
import com.miaowmere.finalproject_h071191035.data.api.repository.TvShowRepository;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnCallback;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnSearchCallback;
import com.miaowmere.finalproject_h071191035.data.models.FavoriteMovie;
import com.miaowmere.finalproject_h071191035.data.models.FavoriteTvShow;
import com.miaowmere.finalproject_h071191035.data.models.Movie;
import com.miaowmere.finalproject_h071191035.data.models.TvShow;
import com.miaowmere.finalproject_h071191035.ui.activities.DetailActivity;
import com.miaowmere.finalproject_h071191035.ui.adapters.MainAdapter;
import com.miaowmere.finalproject_h071191035.ui.adapters.clicklistener.OnItemClickListener;
import com.miaowmere.finalproject_h071191035.utilities.ImageSize;

import java.util.List;

import javax.annotation.Nonnull;

public class MainFragment extends Fragment implements OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private LinearProgressIndicator linearProgressIndicator;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private MovieRepository movieRepository;
    private TvShowRepository tvShowRepository;
    private TextView tvError;
    private boolean isFetching;
    private int movieCurrentPage = 1, tvShowCurrentPage = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@Nonnull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_main, menu);
        MenuItem menuItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        setSearchView(searchView);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setSearchView(SearchView searchView) {
        searchView.setQueryHint(getString(R.string.search_hint_label));
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.item_language_setting) {
            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        linearProgressIndicator = view.findViewById(R.id.lpi_main);
        swipeRefreshLayout = view.findViewById(R.id.srl_main);
        recyclerView = view.findViewById(R.id.rv_main);
        tvError = view.findViewById(R.id.tv_no_internet_connection);
        movieRepository = MovieRepository.getInstance();
        tvShowRepository = TvShowRepository.getInstance();

        if (getBundle().equals("movie")) {
            getMovieRepositoryData("", movieCurrentPage);
        } else {
            getTvShowRepositoryData("", tvShowCurrentPage);
        }

        onScrollListener();
        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    private void onScrollListener() {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int totalItem = gridLayoutManager.getItemCount();
                int visibleItem = gridLayoutManager.getChildCount();
                int firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItem + visibleItem >= totalItem / 2) {
                    if (!isFetching) {
                        isFetching = true;
                        Log.d("TV SHOW PAGE", Integer.toString(tvShowCurrentPage));
                        Log.d("MOVIE PAGE", Integer.toString(movieCurrentPage));
                        if (getBundle().equals("tv_show")) {
                            tvShowCurrentPage++;
                            getTvShowRepositoryData("", tvShowCurrentPage);
                        } else {
                            movieCurrentPage++;
                            getMovieRepositoryData("", movieCurrentPage);
                        }
                        isFetching = false;
                    }
                }
            }
        });
    }

    private void getMovieRepositoryData(String query, int page) {
        isFetching = true;
        if (query.equals("")) {
            movieRepository.getModel(page, new OnCallback<Movie>() {
                @Override
                public void onSuccess(int page, List<Movie> movies) {
                    tvError.setVisibility(View.GONE);
                    if (mainAdapter == null) {
                        mainAdapter = new MainAdapter(null, movies);
                        mainAdapter.setClickListener(MainFragment.this);
                        mainAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(mainAdapter);
                        linearProgressIndicator.hide();
                    } else {
                        mainAdapter.appendList(null, movies);
                    }
                    movieCurrentPage = page;
                    isFetching = false;
                    linearProgressIndicator.hide();
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(String message) {
                    Log.d("Error Fetching", message);
                    tvError.setText(R.string.no_internet_connection);
                    tvError.setVisibility(View.VISIBLE);
                }
            });
        } else {
            movieRepository.search(query, page, new OnSearchCallback<Movie>() {
                @Override
                public void onSuccess(List<Movie> movies, String message, int page) {
                    tvError.setVisibility(View.GONE);
                    if (mainAdapter == null) {
                        mainAdapter = new MainAdapter(null, movies);
                        mainAdapter.setClickListener(MainFragment.this);
                        mainAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(mainAdapter);
                    } else {
                        mainAdapter.appendList(null, movies);
                    }
                    movieCurrentPage = page;
                    isFetching = false;
                    linearProgressIndicator.hide();
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(String message) {
                    Log.d("Error Fetching", message);
                    tvError.setText(R.string.no_internet_connection);
                    tvError.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void getTvShowRepositoryData(String query, int page) {
        isFetching = true;
        if (query.equals("")) {
            tvShowRepository.getModel(page, new OnCallback<TvShow>() {
                @Override
                public void onSuccess(int page, List<TvShow> tvShows) {
                    tvError.setVisibility(View.GONE);
                    if (mainAdapter == null) {
                        Log.d("Debugging", "adapter:null");
                        mainAdapter = new MainAdapter(tvShows, null);
                        mainAdapter.setClickListener(MainFragment.this);
                        mainAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(mainAdapter);
                    } else {
                        mainAdapter.appendList(tvShows, null);
                    }
                    tvShowCurrentPage = page;
                    isFetching = false;
                    linearProgressIndicator.hide();
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(String message) {
                    Log.d("Error Fetching", message);
                    tvError.setText(R.string.no_internet_connection);
                    tvError.setVisibility(View.VISIBLE);
                }
            });
        } else {
            tvShowRepository.search(query, page, new OnSearchCallback<TvShow>() {
                @Override
                public void onSuccess(List<TvShow> tvShows, String message, int page) {
                    tvError.setVisibility(View.GONE);
                    if (mainAdapter == null) {
                        mainAdapter = new MainAdapter(tvShows, null);
                        mainAdapter.setClickListener(MainFragment.this);
                        mainAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(mainAdapter);
                    } else {
                        mainAdapter.appendList(tvShows, null);
                    }
                    tvShowCurrentPage = page;
                    isFetching = false;
                    linearProgressIndicator.hide();
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(String message) {
                    Log.d("Error Fetching", message);
                    tvError.setText(R.string.no_internet_connection);
                    tvError.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private String getBundle() {
        if (getArguments() != null) {
            return getArguments().getString("SORT_BY");
        }
        return "tv_show";
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.length() > 0) {
            mainAdapter = null;
            if (getBundle().equals("tv_show")) {
                getTvShowRepositoryData(newText, tvShowCurrentPage);
            } else {
                getMovieRepositoryData(newText, movieCurrentPage);
            }
        } else {
            mainAdapter = null;
            if (getBundle().equals("tv_show")) {
                getTvShowRepositoryData("", tvShowCurrentPage);
            } else {
                getMovieRepositoryData("", movieCurrentPage);
            }
        }
        return true;
    }

    @Override
    public void onRefresh() {
        mainAdapter = null;
        tvShowCurrentPage = 1;
        movieCurrentPage = 1;
        if (getBundle().equals("tv_show")) {
            getTvShowRepositoryData("", tvShowCurrentPage);
        } else {
            getMovieRepositoryData("", movieCurrentPage);
        }
    }

    @Override
    public void onClick(FavoriteMovie favoriteMovie) {

    }

    @Override
    public void onClick(FavoriteTvShow favoriteTvShow) {

    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("ID", movie.getId());
        intent.putExtra("TITLE", movie.getTitle());
        intent.putExtra("POSTER_PATH", movie.getPosterPath(ImageSize.W154));
        intent.putExtra("SELECTED_FRAGMENT", getBundle());
        startActivity(intent);
    }

    @Override
    public void onClick(TvShow tvShow) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("ID", tvShow.getId());
        intent.putExtra("TITLE", tvShow.getName());
        intent.putExtra("POSTER_PATH", tvShow.getPosterPath(ImageSize.W154));
        intent.putExtra("SELECTED_FRAGMENT", getBundle());
        startActivity(intent);
    }
}