package com.miaowmere.finalproject_h071191035.data.models;

import com.google.gson.annotations.SerializedName;
import com.miaowmere.finalproject_h071191035.ImageSize;

import java.util.List;

public class Movie {
    private int id;
    private String overview;
    @SerializedName("original_title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropImage;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("poster_path")
    private String posterImage;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public void setBackdropImage(String backdropImage) {
        this.backdropImage = backdropImage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterImage(ImageSize w154) {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
