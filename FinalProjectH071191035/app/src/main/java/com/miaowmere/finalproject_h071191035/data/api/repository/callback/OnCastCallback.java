package com.miaowmere.finalproject_h071191035.data.api.repository.callback;

import com.miaowmere.finalproject_h071191035.data.models.Cast;

import java.util.List;

public interface OnCastCallback {
    void onSuccess(List<Cast> casts, String message);
    void onFailure(String message);
}
