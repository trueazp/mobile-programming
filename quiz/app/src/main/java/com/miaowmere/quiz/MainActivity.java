package com.miaowmere.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnHome, btnCall, btnExit;
    private TextView tvBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHome = findViewById(R.id.btnHome);
        btnCall = findViewById(R.id.btnCall);
        btnExit = findViewById(R.id.btnExit);
        tvBio = findViewById(R.id.tvBio);

        btnHome.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        tvBio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHome:
                Uri uri = Uri.parse("geo:-3.6483479564494115, 119.55716857429742");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                intent1.setPackage("com.google.android.apps.maps");
                startActivity(intent1);
                return;
            case R.id.btnCall:
                Uri number = Uri.parse("sms:+6282194837349");
                Intent intent2 = new Intent(Intent.ACTION_VIEW, number);
                intent2.putExtra("sms_body", "miaow");
                startActivity(intent2);
                return;
            case R.id.btnExit:
                logout(this);
                return;
            case R.id.tvBio:
                Intent intent3 = new Intent(MainActivity.this, Biodata.class);
                startActivity(intent3);
                return;
        }
    }

    @Override
    public void onBackPressed() {
        logout(MainActivity.this);
    }

    static void logout(final Activity act) {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                act.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
