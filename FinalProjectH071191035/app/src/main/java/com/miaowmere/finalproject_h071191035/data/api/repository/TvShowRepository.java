package com.miaowmere.finalproject_h071191035.data.api.repository;

import android.util.Log;

import androidx.annotation.NonNull;


import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnCastCallback;
import com.miaowmere.finalproject_h071191035.data.models.CastResponse;
import com.miaowmere.finalproject_h071191035.utilities.Const;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnCallback;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnDetailCallback;
import com.miaowmere.finalproject_h071191035.data.api.services.Service;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnSearchCallback;
import com.miaowmere.finalproject_h071191035.data.models.TvShow;
import com.miaowmere.finalproject_h071191035.data.models.TvShowResponse;
import com.miaowmere.finalproject_h071191035.utilities.Repository;
import com.miaowmere.finalproject_h071191035.utilities.SingletonRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowRepository extends Repository<TvShow> {
    private static TvShowRepository repository;

    private TvShowRepository(Service service) {
        this.service = service;
    }

    public static TvShowRepository getInstance() {
        if (repository == null) {
            Service service = SingletonRequest.getInstance();
            repository = new TvShowRepository(service);
        }
        return repository;
    }

    public void getModel(int page, final OnCallback<TvShow> callback) {
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

    public void getModelDetail(int id, final OnDetailCallback<TvShow> callback) {
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

    public void getCast(int id, final OnCastCallback castCallback) {
        service.getCasts(id, Const.API_KEY, Const.getLang())
                .enqueue(new Callback<CastResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<CastResponse> call, @NonNull Response<CastResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body() != null) {
                                    castCallback.onSuccess(response.body().getCasts(), response.message());
                                } else {
                                    castCallback.onFailure("No Casts");
                                }
                            } else {
                                castCallback.onFailure("response.body() is null");
                            }
                        } else {
                            castCallback.onFailure(response.message() + " : " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CastResponse> call, @NonNull Throwable t) {
                        castCallback.onFailure(t.getLocalizedMessage());
                    }
                });
    }
}
