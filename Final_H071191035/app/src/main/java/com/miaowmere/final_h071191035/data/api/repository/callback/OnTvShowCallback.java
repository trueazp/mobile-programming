package com.miaowmere.final_h071191035.data.api.repository.callback;

import java.util.List;

import com.miaowmere.final_h071191035.data.models.TvShow;

public interface OnTvShowCallback {
    void onSuccess(int page, List<TvShow> tvShowList);

    void onFailure(String message);
}
