package com.miaowmere.finalproject_h071191035.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miaowmere.finalproject_h071191035.R;
import com.miaowmere.finalproject_h071191035.ui.fragments.FavoriteFragment;
import com.miaowmere.finalproject_h071191035.ui.fragments.TvShowFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bnv_main);
        bottomNav.setOnNavigationItemSelectedListener(this);
        setSelectedItem(bottomNav);
    }

    private void setSelectedItem(BottomNavigationView bottomNav) {
        if (getIntent().getStringExtra("SELECTED_FRAGMENT") != null) {
            switch (getIntent().getStringExtra("SELECTED_FRAGMENT")) {
                case "airing_today":
                    bottomNav.setSelectedItemId(R.id.item_airing_today);
                    break;
                case "popular":
                    bottomNav.setSelectedItemId(R.id.item_popular);
                    break;
                case "top_rated":
                    bottomNav.setSelectedItemId(R.id.item_top_rated);
                    break;
                case "favorite":
                    bottomNav.setSelectedItemId(R.id.item_favorite);
                    break;
            }
        } else {
            bottomNav.setSelectedItemId(R.id.item_airing_today);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        String sortBy = null;
        switch (item.getItemId()) {
            case R.id.item_airing_today:
                setActionBar(getString(R.string.airing_today), R.drawable.ic_airing_today);
                sortBy = "airing_today";
                fragment = new TvShowFragment();
                break;
            case R.id.item_popular:
                setActionBar(getString(R.string.popular), R.drawable.ic_popular);
                fragment = new TvShowFragment();
                break;
            case R.id.item_top_rated:
                setActionBar(getString(R.string.top_rated), R.drawable.ic_top_rated);
                fragment = new TvShowFragment();
                break;
            case R.id.item_favorite:
                setActionBar(getString(R.string.favorite), R.drawable.ic_favorite);
                fragment = new FavoriteFragment();
                break;
        }
        if (fragment != null) {
            // Method that handle which data to show base on @sortBy params
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).commit();
            startFragment(fragment, sortBy);
            return true;
        }
        return false;
    }

    private void setActionBar(String title, int logo) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("\t" + title);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setLogo(logo);
        }
    }

    private void startFragment(Fragment fragment, String bundle) {
        if (bundle != null) {
            Bundle sortBy = new Bundle();
            sortBy.putString("SORT_BY", bundle);
            fragment.setArguments(sortBy);
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_main, fragment)
                .commit();
    }
}