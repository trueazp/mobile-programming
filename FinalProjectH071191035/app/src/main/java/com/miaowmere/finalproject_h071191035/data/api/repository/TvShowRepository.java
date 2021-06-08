package com.miaowmere.finalproject_h071191035.data.api.repository;

import android.util.Log;

import androidx.annotation.NonNull;


import com.miaowmere.finalproject_h071191035.Const;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnCallback;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnDetailCallback;
import com.miaowmere.finalproject_h071191035.data.api.services.Service;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnSearchCallback;
import com.miaowmere.finalproject_h071191035.data.models.TvShow;
import com.miaowmere.finalproject_h071191035.data.models.TvShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowRepository {
    private static TvShowRepository repository;
    private Service service;

    private TvShowRepository(Service service) {
        this.service = service;
    }

    public static TvShowRepository getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Const.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            repository = new TvShowRepository(retrofit.create(Service.class));
        }
        return repository;
    }

    public void getTvShow(int page, final OnCallback<TvShow> callback) {
        service.getTvResults(Const.API_KEY, Const.getLang(), page).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            Log.d("TV Show Response", response.body().getResults().toString());
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
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void getTvDetail(int id, final OnDetailCallback<TvShow> callback) {
        service.getTvDetail(id, Const.API_KEY, Const.getLang()).enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(@NonNull Call<TvShow> call, @NonNull Response<TvShow> response) {
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
            public void onFailure(@NonNull Call<TvShow> call, @NonNull Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void search(String query, int page, final OnSearchCallback<TvShow> callback) {
        service.searchTv(Const.API_KEY, query, Const.getLang(), page).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
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
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
