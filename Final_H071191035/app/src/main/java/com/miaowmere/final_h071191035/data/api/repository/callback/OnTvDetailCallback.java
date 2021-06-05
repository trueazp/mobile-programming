package com.miaowmere.final_h071191035.data.api.repository.callback;

import com.miaowmere.final_h071191035.data.models.TvShow;

public interface OnTvDetailCallback {
    void onSuccess(TvShow tvShow, String message);

    void onFailure(String message);
}
