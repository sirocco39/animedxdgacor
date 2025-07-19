package com.example.animedxdgacor; // Ganti dengan nama package Anda

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View; // Import View
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView; // Import TextView
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.animedxdgacor.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView usernameErrorText; // Deklarasi TextView error
    private TextView passwordErrorText; // Deklarasi TextView error
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi komponen UI
        usernameEditText = findViewById(R.id.edit_text_username);
        passwordEditText = findViewById(R.id.edit_text_password);
        usernameErrorText = findViewById(R.id.text_error_username); // Inisialisasi TextView error
        passwordErrorText = findViewById(R.id.text_error_password); // Inisialisasi TextView error
        loginButton = findViewById(R.id.btn_login);

        loginButton.setOnClickListener(view -> {
            if (validateInput()) {
                loginUser();
            }
        });
    }

    private boolean validateInput() {
        // Selalu sembunyikan pesan error di awal validasi
        usernameErrorText.setVisibility(View.GONE);
        passwordErrorText.setVisibility(View.GONE);

        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        boolean isValid = true;

        if (TextUtils.isEmpty(username)) {
            // Jika kosong, tampilkan TextView error untuk username
            usernameErrorText.setVisibility(View.VISIBLE);
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            // Jika kosong, tampilkan TextView error untuk password
            passwordErrorText.setVisibility(View.VISIBLE);
            isValid = false;
        }

        return isValid;
    }

    private void loginUser() {
        String username = usernameEditText.getText().toString().trim();

        Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("EXTRA_USERNAME", username);
        startActivity(intent);
        finish();
    }
}