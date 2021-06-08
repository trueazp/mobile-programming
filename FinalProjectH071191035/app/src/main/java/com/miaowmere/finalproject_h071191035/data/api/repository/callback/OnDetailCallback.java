package com.miaowmere.finalproject_h071191035.data.api.repository.callback;

public interface OnDetailCallback<T> {
    void onSuccess(T media, String message);
    void onFailure(String message);
}
