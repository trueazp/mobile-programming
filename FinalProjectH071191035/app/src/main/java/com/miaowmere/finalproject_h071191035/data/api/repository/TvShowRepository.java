package com.miaowmere.finalproject_h071191035.data.api.repository;

import androidx.annotation.NonNull;


import com.miaowmere.finalproject_h071191035.Const;
import com.miaowmere.finalproject_h071191035.data.api.services.Service;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnSearchCallback;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnTvDetailCallback;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnTvShowCallback;
import com.miaowmere.finalproject_h071191035.data.models.TvShow;
import com.miaowmere.finalproject_h071191035.data.models.TvShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowRepository {
    private static TvShowRepository repository;
    private Service service;

    private TvShowRepository(Service service) {
        this.service = service;
    }

    public void getTvShow(int page, final OnTvShowCallback callback) {
        service.getTvResults(Const.API_KEY, Const.getLang(), page).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
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
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void getTvDetail(int id, final OnTvDetailCallback callback) {
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

    public void search(String query, int page, final OnSearchCallback callback) {
        service.searchTv(Const.API_KEY, query, Const.getLang(), page).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null) {
                            callback.onSuccess(response.body().getResults(), null, response.message(), response.body().getPage());
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
