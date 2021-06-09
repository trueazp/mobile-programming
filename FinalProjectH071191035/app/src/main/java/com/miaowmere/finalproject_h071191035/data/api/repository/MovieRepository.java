package com.miaowmere.finalproject_h071191035.data.api.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.miaowmere.finalproject_h071191035.utilities.Const;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnCallback;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnDetailCallback;
import com.miaowmere.finalproject_h071191035.data.api.services.Service;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnSearchCallback;
import com.miaowmere.finalproject_h071191035.data.models.Movie;
import com.miaowmere.finalproject_h071191035.data.models.MovieResponse;
import com.miaowmere.finalproject_h071191035.utilities.Repository;
import com.miaowmere.finalproject_h071191035.utilities.SingletonRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository extends Repository<Movie> {
    private static MovieRepository repository;

    private MovieRepository(Service service) {
        this.service = service;
    }

    public static MovieRepository getInstance() {
        if (repository == null) {
            Service service = SingletonRequest.getInstance();
            repository = new MovieRepository(service);
        }
        return repository;
    }

    public void getModel(int page, final OnCallback<Movie> callback) {
        service.getMovieResults(Const.API_KEY, Const.getLang(), page).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            Log.d("Movie Response", response.body().getResults().get(0).getTitle());
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

    public void getModelDetail(int id, final OnDetailCallback<Movie> callback) {
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

    public void search(String query, int page, final OnSearchCallback<Movie> callback) {
        service.searchMovie(Const.API_KEY, query, Const.getLang(), page).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            callback.onSuccess(response.body().getResults(), response.message(), response.body().getPage());
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
