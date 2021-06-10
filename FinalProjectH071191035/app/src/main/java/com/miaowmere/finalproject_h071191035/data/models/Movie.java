package com.miaowmere.finalproject_h071191035.data.models;

import com.google.gson.annotations.SerializedName;
import com.miaowmere.finalproject_h071191035.utilities.Const;
import com.miaowmere.finalproject_h071191035.utilities.ImageSize;

import java.util.List;

public class Movie {
    private int id;
    private String overview;
    private String runtime;
    @SerializedName("original_title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("vote_count")
    private String voteCount;
    @SerializedName("genres")
    private List<Genre> genres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public String getRuntime() {
        return runtime != null ? String.format("%s Min", runtime) : "None";
    }

    public String getVoteCount() {
        return String.format("%s Votes", voteCount);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath(ImageSize size) {
        return Const.IMG_URL + size.getValue() + backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath(ImageSize size) {
        return Const.IMG_URL + size.getValue() + posterPath;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public List<Genre> getGenres() {
        return genres;
    }
}
