package com.miaowmere.finalproject_h071191035.data.api;

import com.miaowmere.finalproject_h071191035.data.models.TvShow;
import com.miaowmere.finalproject_h071191035.data.models.TvShowAiringTodayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvShowApiInterface {
    @GET("airing_today")
    Call<TvShowAiringTodayResponse> getAiringToday(@Query("api_key") String apiKey);

    @GET("tv/{tv_id}")
    Call<TvShow> getTvShow(@Path("tv_id") String id, @Query("api_key") String apiKey);
}
