package com.miaowmere.finalproject_h071191035.data.api.repository.callback;

import java.util.List;

public interface OnCallback<T> {
    void onSuccess(int page, List<T> list);
    void onFailure(String message);
}
