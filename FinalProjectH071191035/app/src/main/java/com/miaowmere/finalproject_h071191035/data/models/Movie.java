package com.miaowmere.finalproject_h071191035.data.models;

import com.google.gson.annotations.SerializedName;
import com.miaowmere.finalproject_h071191035.Const;
import com.miaowmere.finalproject_h071191035.ImageSize;

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

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRuntime() {
        return runtime != null ? String.format("%s Min", runtime) : "None";
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getVoteCount() {
        return String.format("%s Votes", voteCount);
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
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

    public void setBackdropImage(String backdropImage) {
        this.backdropPath = backdropImage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath(ImageSize size) {
        return Const.IMG_URL + size.getValue() + posterPath;
    }

    public void setPosterImage(String posterImage) {
        this.posterPath = posterImage;
    }

    public float getPopularity() {
        return popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
