package com.miaowmere.finalproject_h071191035.utilities;

import com.miaowmere.finalproject_h071191035.data.api.services.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonRequest {
    private static Service service;

    public static Service getInstance() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Const.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            service = retrofit.create(Service.class);
        }
        return service;
    }
}
