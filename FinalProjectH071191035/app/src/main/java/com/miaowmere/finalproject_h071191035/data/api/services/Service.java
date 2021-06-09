package com.miaowmere.finalproject_h071191035.data.api.services;

import com.miaowmere.finalproject_h071191035.data.models.CastResponse;
import com.miaowmere.finalproject_h071191035.data.models.Movie;
import com.miaowmere.finalproject_h071191035.data.models.MovieResponse;
import com.miaowmere.finalproject_h071191035.data.models.TvShow;
import com.miaowmere.finalproject_h071191035.data.models.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    // movies
    @GET("movie/popular")
    Call<MovieResponse> getMovieResults (
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("search/movie")
    Call<MovieResponse> searchMovie (
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("movie/{id}")
    Call<Movie> getMovieDetail (
            @Path("id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{id}/credits")
    Call<CastResponse> getCasts(
            @Path("id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    // tv shows
    @GET("tv/popular")
    Call<TvShowResponse> getTvResults (
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("search/tv")
    Call<TvShowResponse> searchTv (
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("tv/{id}")
    Call<TvShow> getTvDetail (
            @Path("id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
