package com.miaowmere.finalproject_h071191035.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.miaowmere.finalproject_h071191035.ui.fragments.FavoriteMovieFragment;
import com.miaowmere.finalproject_h071191035.ui.fragments.FavoriteTvShowFragment;

import org.jetbrains.annotations.NotNull;

public class FavoritePageAdapter extends FragmentStateAdapter {

    private final Fragment[] fragments;

    public FavoritePageAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragments = new Fragment[] {
                new FavoriteMovieFragment(), new FavoriteTvShowFragment()
        };
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
