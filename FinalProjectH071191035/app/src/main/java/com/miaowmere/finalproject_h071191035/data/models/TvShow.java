package com.miaowmere.finalproject_h071191035.data.models;

import com.google.gson.annotations.SerializedName;
import com.miaowmere.finalproject_h071191035.utilities.Const;
import com.miaowmere.finalproject_h071191035.utilities.ImageSize;

import java.util.List;

public class TvShow {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("last_air_date")
    private String lastAirDate;
    @SerializedName("number_of_episodes")
    private int numberOfEpisode;
    @SerializedName("number_of_seasons")
    private int numberOfSeason;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("vote_count")
    private String voteCount;
    @SerializedName("episode_run_time")
    private String[] runtime;
    @SerializedName("genres")
    private List<Genre> genres;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBackdropPath(ImageSize size) {
        return Const.IMG_URL + size.getValue() + backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public int getNumberOfEpisode() {
        return numberOfEpisode;
    }

    public int getNumberOfSeason() {
        return numberOfSeason;
    }

    public String getOverview() {
        return overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPosterPath(ImageSize size) {
        return Const.IMG_URL + size.getValue() + posterPath;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getVoteCount() {
        return String.format("%s Votes", voteCount);
    }

    public String getRuntime() {
        return runtime.length > 0 ? String.format("%s Min Per Ep.", runtime[0]) : "None";
    }

    public List<Genre> getGenres() {
        return genres;
    }
}
