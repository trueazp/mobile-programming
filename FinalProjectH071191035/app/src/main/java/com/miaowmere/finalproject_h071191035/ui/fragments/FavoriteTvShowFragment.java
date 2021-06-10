package com.miaowmere.finalproject_h071191035.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miaowmere.finalproject_h071191035.R;
import com.miaowmere.finalproject_h071191035.data.models.FavoriteMovie;
import com.miaowmere.finalproject_h071191035.data.models.FavoriteTvShow;
import com.miaowmere.finalproject_h071191035.data.models.Movie;
import com.miaowmere.finalproject_h071191035.data.models.TvShow;
import com.miaowmere.finalproject_h071191035.ui.activities.DetailActivity;
import com.miaowmere.finalproject_h071191035.ui.adapters.FavoriteTvShowAdapter;
import com.miaowmere.finalproject_h071191035.ui.adapters.clicklistener.OnItemClickListener;
import com.miaowmere.finalproject_h071191035.utilities.ImageSize;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteTvShowFragment extends Fragment implements OnItemClickListener {

    private RecyclerView recyclerView;
    private Realm realm;
    private FavoriteTvShowAdapter favoriteTvShowAdapter;
    private ConstraintLayout clFavIsEmpty;

    public FavoriteTvShowFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Realm.init(getContext());
        String realmTitle = "final project";
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).name(realmTitle).build();
        realm = Realm.getInstance(configuration);
        View view = inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
        recyclerView = view.findViewById(R.id.rv_tv_show_favorite);
        clFavIsEmpty = view.findViewById(R.id.cl_tv_show_favorite_empty);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        List<FavoriteTvShow> tvShows = (List<FavoriteTvShow>) realm.where(FavoriteTvShow.class).findAll();

        if (tvShows.size() == 0) {
            clFavIsEmpty.setVisibility(View.VISIBLE);
        } else {
            favoriteTvShowAdapter = new FavoriteTvShowAdapter(tvShows);
            favoriteTvShowAdapter.setClickListener(FavoriteTvShowFragment.this);
            favoriteTvShowAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(favoriteTvShowAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
            clFavIsEmpty.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        List<FavoriteTvShow> tvShows = realm.where(FavoriteTvShow.class).findAll();
        if (tvShows.size() == 0) {
            clFavIsEmpty.setVisibility(View.VISIBLE);
        } else {
            favoriteTvShowAdapter = new FavoriteTvShowAdapter(tvShows);
            favoriteTvShowAdapter.setClickListener(FavoriteTvShowFragment.this);
            favoriteTvShowAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(favoriteTvShowAdapter);
            clFavIsEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(Movie movie) {

    }

    @Override
    public void onClick(TvShow tvShow) {

    }

    @Override
    public void onClick(FavoriteMovie favoriteMovie) {

    }

    @Override
    public void onClick(FavoriteTvShow favoriteTvShow) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("ID", favoriteTvShow.getId());
        intent.putExtra("TITLE", favoriteTvShow.getTitle());
        intent.putExtra("POSTER_PATH", favoriteTvShow.getPosterPath(ImageSize.W154));
        intent.putExtra("SELECTED_FRAGMENT", "tv_show");
        startActivity(intent);
    }
}