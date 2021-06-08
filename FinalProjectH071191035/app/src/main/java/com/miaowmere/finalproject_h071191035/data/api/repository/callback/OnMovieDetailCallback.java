package com.miaowmere.finalproject_h071191035.data.api.repository.callback;

import com.miaowmere.finalproject_h071191035.data.models.Movie;

public interface OnMovieDetailCallback {
    void onSuccess(Movie movie, String message);
    void onFailure(String message);
}
