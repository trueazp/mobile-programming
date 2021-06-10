package com.miaowmere.finalproject_h071191035.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.miaowmere.finalproject_h071191035.R;
import com.miaowmere.finalproject_h071191035.ui.adapters.FavoritePageAdapter;

public class FavoriteFragment extends Fragment {

    public FavoriteFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ViewPager2 viewPager2 = view.findViewById(R.id.vp_favorite);
        viewPager2.setAdapter(new FavoritePageAdapter(getActivity()));
        TabLayout tabLayout = view.findViewById(R.id.tl_favorite);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                tab.setText("OBJECT " + (position + 1))).attach();
        (tabLayout.getTabAt(0)).setText("Movie");
        (tabLayout.getTabAt(1)).setText("Tv Show");
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        return view;
    }
}