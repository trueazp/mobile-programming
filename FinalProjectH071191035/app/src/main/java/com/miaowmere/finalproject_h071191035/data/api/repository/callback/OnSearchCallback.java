package com.miaowmere.finalproject_h071191035.data.api.repository.callback;

import com.miaowmere.finalproject_h071191035.data.models.TvShow;

import java.util.List;

public interface OnSearchCallback {
    void onSuccess(List<TvShow> tvShowList, String msg, int page);
    void onFailure(String msg);
}
