package com.miaowmere.final_h071191035.data.api.repository.callback;

import java.util.List;

import com.miaowmere.final_h071191035.data.models.TvShow;

public interface OnSearchCallback {
    void onSuccess(List<TvShow> tvShowList, String msg, int page);

    void onFailure(String msg);
}
