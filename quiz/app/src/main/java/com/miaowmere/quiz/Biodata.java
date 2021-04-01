package com.miaowmere.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Biodata extends AppCompatActivity implements View.OnClickListener {

    private Button btnExit, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        btnBack = findViewById(R.id.btnBack);
        btnExit = findViewById(R.id.btnExit);

        btnBack.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                Intent intent = new Intent(Biodata.this, MainActivity.class);
                startActivity(intent);
                return;
            case R.id.btnExit:
                MainActivity.logout(this);
                return;
        }
    }

    @Override
    public void onBackPressed() {
        MainActivity.logout(Biodata.this);
    }
}