package com.miaowmere.animelist_bottomnav;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment implements OnItemClickListener<Anime> {

    private RecyclerView rvAnime;
    private AnimeRecyclerAdapter adapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        rvAnime = rootView.findViewById(R.id.rv_anime);
        adapter = new AnimeRecyclerAdapter();
        adapter.setAnimes(AnimeDatasource.getListData());
        rvAnime.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAnime.setHasFixedSize(true);
        adapter.setClickListener(this);
        rvAnime.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onClick(Anime anime) {
        Intent intent = new Intent(getActivity(), Details.class);
        intent.putExtra(Details.details, anime);
        startActivity(intent);
    }
}