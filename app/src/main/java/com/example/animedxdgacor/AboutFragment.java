package com.example.animedxdgacor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView; // Tambahkan import untuk TextView
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment {

    // Deklarasikan variabel untuk TextView
    private TextView welcomeTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Mendapatkan referensi ke layout fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // Inisialisasi TextView dari layout fragment_about.xml
        // Pastikan ID ini sesuai dengan ID TextView di file XML Anda
        welcomeTextView = view.findViewById(R.id.welcome_text1);

        // Ambil Bundle yang dikirim dari MainActivity
        Bundle bundle = getArguments();

        // Cek apakah Bundle ada dan berisi data
        if (bundle != null) {
            String username = bundle.getString("USERNAME_KEY");
            if (username != null && !username.isEmpty()) {
                // Ubah teks TextView dengan nama pengguna yang diterima
                welcomeTextView.setText("Welcome, " + username);
            }
        }

        return view;
    }
}