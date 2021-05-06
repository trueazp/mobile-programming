package com.miaowmere.moviecatalogue.networks;

import com.miaowmere.moviecatalogue.models.AiringTodayResponse;
import com.miaowmere.moviecatalogue.models.NowPlayingResponse;
import com.miaowmere.moviecatalogue.models.movie.MovieModel;
import com.miaowmere.moviecatalogue.models.tv.TelevisionModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiInterface {
    @GET("movie/now_playing")
    Call<NowPlayingResponse> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}")
    Call<MovieModel> getMovie(
            @Path("movie_id") String id,
            @Query("api_key") String apiKey
    );

    @GET("tv/airing_today")
    Call<AiringTodayResponse> getAiringToday(
            @Query("api_key") String apiKey
    );

    @GET("tv/{tv_id}")
    Call<TelevisionModel> getTelevision(
            @Path("tv_id") String id,
            @Query("api_key") String apiKey
    );
}
