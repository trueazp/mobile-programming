package com.miaowmere.finalproject_h071191035.data.api.repository;

import androidx.annotation.NonNull;

import com.miaowmere.finalproject_h071191035.Const;
import com.miaowmere.finalproject_h071191035.data.api.services.Service;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnMovieCallback;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnMovieDetailCallback;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnSearchCallback;
import com.miaowmere.finalproject_h071191035.data.models.Movie;
import com.miaowmere.finalproject_h071191035.data.models.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository repository;
    private Service service;

    private MovieRepository(Service service) {
        this.service = service;
    }

    public void getMovie(int page, final OnMovieCallback callback) {
        service.getMovieResults(Const.API_KEY, Const.getLang(), page).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            callback.onSuccess(response.body().getPage(), response.body().getResults());
                        } else {
                            callback.onFailure("response.body().getResults() is null");
                        }
                    } else {
                        callback.onFailure("response.body() is null");
                    }
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void getMovieDetail(int id, final OnMovieDetailCallback callback) {
        service.getMovieDetail(id, Const.API_KEY, Const.getLang()).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body(), response.message());
                    } else {
                        callback.onFailure("response.body() is null");
                    }
                } else {
                    callback.onFailure(response.message() + "Error code : " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void search(String query, int page, final OnSearchCallback callback) {
        service.searchMovie(Const.API_KEY, query, Const.getLang(), page).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            callback.onSuccess(null, response.body().getResults(), response.message(), response.body().getPage());
                        } else {
                            callback.onFailure("No Result");
                        }
                    } else {
                        callback.onFailure("response.body() is null");
                    }
                } else {
                    callback.onFailure(response.message() + "Error code : " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
