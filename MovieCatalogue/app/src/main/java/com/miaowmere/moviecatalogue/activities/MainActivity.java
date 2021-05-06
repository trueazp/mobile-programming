package com.miaowmere.moviecatalogue.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miaowmere.moviecatalogue.R;
import com.miaowmere.moviecatalogue.fragments.AiringTodayFragment;
import com.miaowmere.moviecatalogue.fragments.NowPlayingFragment;
import com.miaowmere.moviecatalogue.fragments.UpcomingFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FrameLayout frame_layout_main;
    private BottomNavigationView bottom_nav_main;
    private Map<Integer, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame_layout_main = findViewById(R.id.frame_layout_main);
        bottom_nav_main = findViewById(R.id.bottom_nav_main);

        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.menu_item_now_playing, new NowPlayingFragment());
        fragmentMap.put(R.id.menu_item_airing_today, new AiringTodayFragment());
        fragmentMap.put(R.id.menu_item_upcoming, new UpcomingFragment());
        bottom_nav_main.setOnNavigationItemSelectedListener(this);
        bottom_nav_main.setSelectedItemId(R.id.menu_item_now_playing);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = fragmentMap.get(item.getItemId());
        assert fragment != null;
        switch (item.getItemId()) {
            case R.id.menu_item_now_playing:
                setActionBarTitle("Now Playing");
                break;
            case R.id.menu_item_airing_today:
                setActionBarTitle("Airing Today");
                break;
            case R.id.menu_item_upcoming:
                setActionBarTitle("Upcoming");
                break;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_main, fragment)
                .commit();
        return true;
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}