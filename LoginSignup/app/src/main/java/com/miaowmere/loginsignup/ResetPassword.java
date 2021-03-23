package com.miaowmere.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private EditText resetEmail;
    private Button btnReset;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        resetEmail = findViewById(R.id.email);
        btnReset = findViewById(R.id.resetPassword);
        auth =FirebaseAuth.getInstance();

        // action untuk reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
                Intent intent = new Intent(ResetPassword.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

            private void resetPassword() {
                String email = resetEmail.getText().toString();

                if (email.isEmpty()) {
                    resetEmail.setError("Email harus diisi");
                    resetEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    resetEmail.setError("Email yang dimasukkan tidak valid");
                    resetEmail.requestFocus();
                    return;
                }
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ResetPassword.this, "Berhasil, cek email anda untuk mendapatkan link reset password", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ResetPassword.this, "Gagal, terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    public void to_login_page(View view) {
        Intent intent = new Intent(ResetPassword.this, LoginActivity.class);
        startActivity(intent);
    }
}