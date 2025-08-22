package com.example.animedxdgacor; 

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View; 
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView; 
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.animedxdgacor.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView usernameErrorText; 
    private TextView passwordErrorText; 
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        
        usernameEditText = findViewById(R.id.edit_text_username);
        passwordEditText = findViewById(R.id.edit_text_password);
        usernameErrorText = findViewById(R.id.text_error_username); 
        passwordErrorText = findViewById(R.id.text_error_password); 
        loginButton = findViewById(R.id.btn_login);

        loginButton.setOnClickListener(view -> {
            if (validateInput()) {
                loginUser();
            }
        });
    }

    private boolean validateInput() {
        
        usernameErrorText.setVisibility(View.GONE);
        passwordErrorText.setVisibility(View.GONE);

        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        boolean isValid = true;

        
        if (TextUtils.isEmpty(username)) {
            
            usernameErrorText.setText("Username tidak boleh kosong."); 
            usernameErrorText.setVisibility(View.VISIBLE);
            isValid = false;
        } else if (username.length() < 5 || username.length() > 10) {
            
            usernameErrorText.setText("Username harus terdiri dari 5-10 karakter."); 
            usernameErrorText.setVisibility(View.VISIBLE);
            isValid = false;
        }

        
        if (TextUtils.isEmpty(password)) {
            
            passwordErrorText.setText("Password tidak boleh kosong.");
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