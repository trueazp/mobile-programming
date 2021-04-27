package com.miaowmere.animelist_bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private Map<Integer, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        fragmentMap = new HashMap<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragmentMap.put(R.id.menu_item_home, new HomeFragment());
        fragmentMap.put(R.id.menu_item_saved, new SavedFragment());
        fragmentMap.put(R.id.menu_item_settings, new SettingsFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menu_item_home);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = fragmentMap.get(item.getItemId());
        assert fragment != null;
        switch (item.getItemId()) {
            case R.id.menu_item_home:
                setActionBarTitle("Home");
                break;
            case R.id.menu_item_saved:
                setActionBarTitle("Saved");
                break;
            case R.id.menu_item_settings:
                setActionBarTitle("Settings");
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
        return true;
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}