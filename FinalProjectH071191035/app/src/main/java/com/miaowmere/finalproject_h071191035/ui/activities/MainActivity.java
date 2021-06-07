package com.miaowmere.finalproject_h071191035.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miaowmere.finalproject_h071191035.R;
import com.miaowmere.finalproject_h071191035.ui.fragments.FavoriteFragment;
import com.miaowmere.finalproject_h071191035.ui.fragments.MovieFragment;
import com.miaowmere.finalproject_h071191035.ui.fragments.TvShowFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationItemView = findViewById(R.id.bottom_nav);

        if(getSupportActionBar()!=null){
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#292929"));
            getSupportActionBar().setBackgroundDrawable(colorDrawable);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        bottomNavigationItemView.setOnNavigationItemSelectedListener(this);
        bottomNavigationItemView.setSelectedItemId(R.id.menu_item_movie);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.menu_item_movie:
                setActionBarTitle(getString(R.string.movie_label));
                fragment = new MovieFragment();
                break;
            case R.id.menu_item_tv_show:
                setActionBarTitle(getString(R.string.tv_show_label));
                fragment = new TvShowFragment();
                break;
            case R.id.menu_item_favorite:
                setActionBarTitle(getString(R.string.favorite_label));
                fragment = new FavoriteFragment();
                break;
        }
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).commit();
            return true;
        }
        return true;
    }

    private void setActionBarTitle(String title) {
        title = title.replaceAll("\\s","");
        String first = title.substring(0,title.length()/2);
        String end = title.substring(title.length()/2,title.length());
        getSupportActionBar().setTitle(Html.fromHtml(first+"<font color=\"#FFFFFFFF\">"+end+"</font>"));
    }
}