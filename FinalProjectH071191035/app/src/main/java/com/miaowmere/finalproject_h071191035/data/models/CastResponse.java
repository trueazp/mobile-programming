package com.miaowmere.finalproject_h071191035.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResponse {
    @SerializedName("cast")
    List<Cast> casts;

    public List<Cast> getCasts() {
        return casts;
    }
}
