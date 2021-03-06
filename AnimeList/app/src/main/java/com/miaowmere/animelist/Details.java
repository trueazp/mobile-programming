package com.miaowmere.animelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {

    public final static String title = "Title";
    public final static String genre = "Genre";
    public final static String desc = "Description";
    public final static String poster = "Poster";

    private TextView tv_detail_title, tv_detail_genre, tv_detail_desc;
    private ImageView iv_detail_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv_detail_title = findViewById(R.id.tv_detail_title);
        tv_detail_genre = findViewById(R.id.tv_detail_genre);
        tv_detail_desc = findViewById(R.id.tv_detail_desc);
        iv_detail_poster = findViewById(R.id.iv_detail_poster);

        tv_detail_title.setText(getIntent().getStringExtra(title));
        tv_detail_genre.setText(getIntent().getStringExtra(genre));
        tv_detail_desc.setText(getIntent().getStringExtra(desc));
        iv_detail_poster.setImageResource(getIntent().getIntExtra(poster, 0));
    }

    public void toMain(View view) {
        Intent intent = new Intent(Details.this, MainActivity.class);
        startActivity(intent);
    }
}