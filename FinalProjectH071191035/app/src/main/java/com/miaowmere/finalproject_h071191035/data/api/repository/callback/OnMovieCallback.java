package com.miaowmere.finalproject_h071191035.data.api.repository.callback;

import com.miaowmere.finalproject_h071191035.data.models.Movie;

import java.util.List;

public interface OnMovieCallback {
    void onSuccess(int page, List<Movie> movieList);
    void onFailure(String message);
}
