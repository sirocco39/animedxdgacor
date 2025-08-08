package com.example.animedxdgacor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView; // Tambahkan import untuk TextView

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    // Deklarasikan variabel untuk TextView
    private TextView welcomeTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate (mengembangkan) layout untuk fragment ini
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inisialisasi TextView dari layout
        // Pastikan Anda memiliki TextView dengan ID text_view_welcome di fragment_home.xml
        welcomeTextView = view.findViewById(R.id.text_view_welcome);

        // Ambil 'Bundle' (paket data) yang dikirim dari MainActivity
        Bundle bundle = getArguments();

        // Cek apakah bundle tidak kosong
        if (bundle != null) {
            // Ambil data String dengan kunci "USERNAME_KEY"
            String username = bundle.getString("USERNAME_KEY");

            // Cek apakah username berhasil diambil
            if (username != null && !username.isEmpty()) {
                // Atur teks di TextView
                welcomeTextView.setText("Welcome, " + username);
            }
        }

        // Kembalikan View yang sudah diatur
        return view;
    }
}