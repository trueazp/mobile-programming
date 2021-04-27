package com.miaowmere.animelist_bottomnav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    public final static String details = "ANIME_details";

    private TextView tv_detail_title, tv_detail_genre, tv_detail_desc;
    private ImageView iv_detail_poster;
    private Anime anime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_detail_title = findViewById(R.id.tv_detail_title);
        tv_detail_genre = findViewById(R.id.tv_detail_genre);
        tv_detail_desc = findViewById(R.id.tv_detail_desc);
        iv_detail_poster = findViewById(R.id.iv_detail_poster);
    }

    @Override
    protected void onStart() {
        super.onStart();
        anime = getIntent().getParcelableExtra(details);
        tv_detail_title.setText(anime.getTitle());
        tv_detail_genre.setText(anime.getGenre());
        tv_detail_desc.setText(anime.getDescription());
        iv_detail_poster.setImageResource(anime.getPoster());
        getSupportActionBar().setTitle(anime.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void toMain(View view) {
        Intent intent = new Intent(Details.this, MainActivity.class);
        startActivity(intent);
    }
}