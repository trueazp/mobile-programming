package com.miaowmere.finalproject_h071191035.data.api.services;

import com.miaowmere.finalproject_h071191035.data.models.FavoriteMovie;

import io.realm.Realm;
import io.realm.RealmQuery;

public class FavoriteHelper {
    private static Realm realm;
    private static FavoriteHelper favoriteHelper;

    private FavoriteHelper(Realm realm) {
        this.realm = realm;
    }

    public static FavoriteHelper getInstance(Realm realm) {
        if (favoriteHelper == null) {
            favoriteHelper = new FavoriteHelper(realm);
        }
        return favoriteHelper;
    }

    public FavoriteMovie findMovieById(int id) {
        RealmQuery<FavoriteMovie> query = realm.where(FavoriteMovie.class);
        query.equalTo("id", id);
        FavoriteMovie result = query.findFirst();
        return result;
    }

    public void insertMovie(String title, String posterPath, int id) {
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        favoriteMovie.setPosterPath(posterPath);
        favoriteMovie.setTitle(title);
        favoriteMovie.setId(id);
        realm.executeTransaction(transactionRealm -> {
            transactionRealm.insert(favoriteMovie);
        });
    }
}
