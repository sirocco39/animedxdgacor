package com.example.animedxdgacor;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        menuButton = findViewById(R.id.menu_button);

        // 1. Terima data username dari LoginActivity
        String username = getIntent().getStringExtra("EXTRA_USERNAME");

        // 2. Cek apakah ada username yang dikirim
        if (username != null && !username.isEmpty()) {
            // Jika ada, siapkan HomeFragment dan kirim data ke sana
            HomeFragment homeFragment = new HomeFragment();

            // Buat 'paket' (Bundle) untuk menampung data
            Bundle bundle = new Bundle();
            bundle.putString("USERNAME_KEY", username); // Masukkan username ke paket

            // 'Tempelkan' paket data ke instance HomeFragment
            homeFragment.setArguments(bundle);

            // Muat HomeFragment yang sudah berisi data username
            loadFragment(homeFragment);
            bottomNavigationView.setSelectedItemId(R.id.nav_home);

        } else if (savedInstanceState == null) {
            // Jika tidak ada data login (misal: buka aplikasi biasa),
            // tampilkan HomeFragment versi standar.
            loadFragment(new HomeFragment());
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        }


        // Listener untuk item di Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_list) {
                selectedFragment = new ListFragment();
            } else if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.nav_about) {
                selectedFragment = new AboutFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true; // Mengindikasikan bahwa event telah ditangani
            }

            return false;
        });

        // Listener untuk Hamburger Menu (menu pojok kanan atas)
        menuButton.setOnClickListener(view -> {
            // Inflate layout popup_logout
            View popupView = getLayoutInflater().inflate(R.layout.popup_logout, null);

            // Ubah warna hamburger jadi oranye saat popup muncul
            menuButton.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.orange));

            // Buat popup window
            PopupWindow popupWindow = new PopupWindow(popupView,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    true); // 'true' agar bisa ditutup dengan menekan luar area popup

            // Saat popup ditutup, kembalikan warna hamburger ke hitam
            popupWindow.setOnDismissListener(() ->
                    menuButton.setColorFilter(ContextCompat.getColor(MainActivity.this, android.R.color.black))
            );

            // Temukan tombol logout di dalam layout popup
            TextView btnLogout = popupView.findViewById(R.id.btn_logout);

            // Aksi saat tombol logout di dalam popup diklik
            btnLogout.setOnClickListener(v -> {
                Toast.makeText(MainActivity.this, "Logout berhasil", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss(); // Menutup popup
                // Di sini Anda bisa menambahkan logika logout sesungguhnya (misal: kembali ke halaman login)
            });

            // Tampilkan popup di dekat menuButton
            popupWindow.showAsDropDown(menuButton, -100, 40);
        });
    }

    // Fungsi untuk memuat atau mengganti fragment di container
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}