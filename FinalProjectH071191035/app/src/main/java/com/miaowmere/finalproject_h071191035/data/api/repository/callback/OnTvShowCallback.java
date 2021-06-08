package com.miaowmere.finalproject_h071191035.data.api.repository.callback;

import com.miaowmere.finalproject_h071191035.data.models.TvShow;

import java.util.List;

public interface OnTvShowCallback {
    void onSuccess(int page, List<TvShow> tvShowList);
    void onFailure(String message);
}
