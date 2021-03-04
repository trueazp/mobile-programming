package com.example.tugas2_h071191035;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void to_page_article(View view) {
        Intent intent = new Intent(MainActivity.this, page_article.class);
        startActivity(intent);
        Toast.makeText(this, "Article Page", Toast.LENGTH_SHORT).show();
    }

    public void to_page_biodata(View view) {
        Intent intent = new Intent(MainActivity.this, page_biodata.class);
        startActivity(intent);
        Toast.makeText(this, "Biodata Page", Toast.LENGTH_SHORT).show();
    }
}