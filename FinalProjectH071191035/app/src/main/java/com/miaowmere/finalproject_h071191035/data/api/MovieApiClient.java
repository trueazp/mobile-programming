package com.miaowmere.finalproject_h071191035.data.api;

import com.miaowmere.finalproject_h071191035.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApiClient {
    private static Retrofit retrofitMovies, retrofitMovieDetail;

    public static Retrofit getRetrofitMovies() {
        if (retrofitMovies == null) {
            retrofitMovies = new Retrofit.Builder().baseUrl(Const.MOVIE_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofitMovies;
    }

    public static Retrofit getRetrofitMovieDetail() {
        if (retrofitMovieDetail == null) {
            retrofitMovieDetail = new Retrofit.Builder().baseUrl(Const.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofitMovieDetail;
    }
}
