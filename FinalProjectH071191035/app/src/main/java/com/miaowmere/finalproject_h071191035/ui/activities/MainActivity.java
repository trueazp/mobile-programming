package com.miaowmere.finalproject_h071191035.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miaowmere.finalproject_h071191035.R;
import com.miaowmere.finalproject_h071191035.ui.fragments.FavoriteFragment;
import com.miaowmere.finalproject_h071191035.ui.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationItemView = findViewById(R.id.bottom_nav);
        bottomNavigationItemView.setOnNavigationItemSelectedListener(this);
        setSelectedItem(bottomNavigationItemView);

//        if(getSupportActionBar()!=null){
//            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#292929"));
//            getSupportActionBar().setBackgroundDrawable(colorDrawable);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }
//
//        bottomNavigationItemView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationItemView.setSelectedItemId(R.id.menu_item_movie);
    }

    private void setSelectedItem(BottomNavigationView bottomNavigationItemView) {
        if (getIntent().getStringExtra("SELECTED_FRAGMENT") != null) {
            switch (getIntent().getStringExtra("SELECTED_FRAGMENT")) {
                case "movie":
                    bottomNavigationItemView.setSelectedItemId(R.id.menu_item_movie);
                    break;
                case "tv_show":
                    bottomNavigationItemView.setSelectedItemId(R.id.menu_item_tv_show);
                    break;
                case "favorite":
                    bottomNavigationItemView.setSelectedItemId(R.id.menu_item_favorite);
                    break;
            }
        } else {
            bottomNavigationItemView.setSelectedItemId(R.id.menu_item_movie);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        String sortBy = null;
        switch (item.getItemId()){
            case R.id.menu_item_movie:
                setActionBar(getString(R.string.movie_label), R.drawable.ic_movie);
                sortBy = "movie";
                fragment = new MainFragment();
                break;
            case R.id.menu_item_tv_show:
                setActionBar(getString(R.string.tv_show_label), R.drawable.ic_tv_show);
                sortBy = "tv_show";
                fragment = new MainFragment();
                break;
            case R.id.menu_item_favorite:
                setActionBar(getString(R.string.favorites_label), R.drawable.ic_star);
                fragment = new FavoriteFragment();
                break;
        }
        if(fragment != null){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).commit();
            startFragment(fragment, sortBy);
            return true;
        }
        return true;
    }

    private void startFragment(Fragment fragment, String sortBy) {
        if (sortBy != null) {
            Bundle bundle = new Bundle();
            bundle.putString("SORT_BY", sortBy);
            fragment.setArguments(bundle);
        }
    }

    private void setActionBar(String title, int logo) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("\t" + title);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setLogo(logo);
        }
    }
}