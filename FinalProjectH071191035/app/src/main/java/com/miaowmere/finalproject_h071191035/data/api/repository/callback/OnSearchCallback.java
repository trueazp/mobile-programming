package com.miaowmere.finalproject_h071191035.data.api.repository.callback;

import com.miaowmere.finalproject_h071191035.data.models.Movie;
import com.miaowmere.finalproject_h071191035.data.models.TvShow;

import java.util.List;

public interface OnSearchCallback {
    void onSuccess(List<TvShow> tvShowList, List<Movie> movieList, String message, int page);
    void onFailure(String message);
}
