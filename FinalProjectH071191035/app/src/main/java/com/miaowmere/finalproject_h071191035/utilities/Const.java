package com.miaowmere.finalproject_h071191035.utilities;

import java.util.Locale;

public class Const {
    public static final String API_KEY = "1962023bc25ec36c99195c4835e13a0c";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String IMG_URL = "https://image.tmdb.org/t/p/";

    public static String getLang() {
        if ("in_ID".equals(Locale.getDefault().toString())) {
            return "id-ID";
        }
        return "en-US";
    }
}
