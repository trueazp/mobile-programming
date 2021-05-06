package com.miaowmere.moviecatalogue.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingResponse {
    @SerializedName("results")
    @Expose
    private List<NowPlayingModel> nowPlayingModels;

    public List<NowPlayingModel> getNowPlayingModels() {
        return nowPlayingModels;
    }
}
