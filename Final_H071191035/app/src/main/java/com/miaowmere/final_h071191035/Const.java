package com.miaowmere.final_h071191035;

import java.util.Locale;

public class Const {
    public static final String API_KEY = "1962023bc25ec36c99195c4835e13a0c";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String IMG_URL = "https://image.tmdb.org/t/p/";

    public static String getLang() {
        switch (Locale.getDefault().toString()) {
            case "in_ID":
                return "id-ID";
            default:
                return "en-US";
        }
    }
}
