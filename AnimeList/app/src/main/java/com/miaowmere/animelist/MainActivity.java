package com.miaowmere.animelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAnime;
    private List<Anime> animes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewAnime = findViewById(R.id.rv_anime);
        recyclerViewAnime.setHasFixedSize(true);
        animes.addAll(AnimeDatasource.getListData());
        recyclerViewAnime.setLayoutManager(new LinearLayoutManager(this));
        AnimeRecyclerAdapter adapter = new AnimeRecyclerAdapter(animes);
        recyclerViewAnime.setAdapter(adapter);
    }

    public void toDetails(View view) {
        Intent intent = new Intent(MainActivity.this, Details.class);
        startActivity(intent);
    }
}