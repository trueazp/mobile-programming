package com.miaowmere.finalproject_h071191035.utilities;

import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnCallback;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnDetailCallback;
import com.miaowmere.finalproject_h071191035.data.api.repository.callback.OnSearchCallback;
import com.miaowmere.finalproject_h071191035.data.api.services.Service;

public abstract class Repository<T> {
    protected Service service;

    protected abstract void getModel(int page, final OnCallback<T> callback);
    protected abstract void getModelDetail(int id, final OnDetailCallback<T> callback);
    protected abstract void search(String query, int page, final OnSearchCallback<T> callback);
}
