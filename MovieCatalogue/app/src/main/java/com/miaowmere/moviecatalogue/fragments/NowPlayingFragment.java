package com.miaowmere.moviecatalogue.fragments;

import com.miaowmere.moviecatalogue.R;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.miaowmere.moviecatalogue.activities.MovieDetailActivity;
import com.miaowmere.moviecatalogue.adapters.NowPlayingAdapter;
import com.miaowmere.moviecatalogue.models.NowPlayingModel;
import com.miaowmere.moviecatalogue.models.NowPlayingResponse;
import com.miaowmere.moviecatalogue.networks.Const;
import com.miaowmere.moviecatalogue.networks.MovieApiClient;
import com.miaowmere.moviecatalogue.networks.MovieApiInterface;
import com.miaowmere.moviecatalogue.networks.OnItemClickListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingFragment extends Fragment implements OnItemClickListener<NowPlayingModel> {
    private static final String TAG = "NowPlayingFragment";
    private RecyclerView rvNowPlaying;
    private NowPlayingAdapter nowPlayingAdapter;
    private FrameLayout progressOverlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        progressOverlay = view.findViewById(R.id.progress_overlay);
        progressOverlay.setVisibility(View.VISIBLE);
        rvNowPlaying = view.findViewById(R.id.rv_now_playing);
        loadData();
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(view.getContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setAlignItems(AlignItems.FLEX_START);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        rvNowPlaying.setLayoutManager(layoutManager);
        return view;
    }

    private void loadData() {
        MovieApiInterface movieApiInterface = new MovieApiClient().getRetrofit().create(MovieApiInterface.class);
        Call<NowPlayingResponse> nowPlayingResponseCall = movieApiInterface.getNowPlaying(Const.API_KEY);
        nowPlayingResponseCall.enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(Call<NowPlayingResponse> call, Response<NowPlayingResponse> response) {
                if (response.isSuccessful() && response.body().getNowPlayingModels() != null) {
                    nowPlayingAdapter = new NowPlayingAdapter(response.body().getNowPlayingModels(), NowPlayingFragment.this);
                    rvNowPlaying.setAdapter(nowPlayingAdapter);
                    progressOverlay.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(NowPlayingModel nowPlayingModel) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.MOVIE_ID, nowPlayingModel.getId());
        startActivity(intent);
    }
}