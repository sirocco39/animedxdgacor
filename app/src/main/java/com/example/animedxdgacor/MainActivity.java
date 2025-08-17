package com.example.animedxdgacor;

import android.content.Intent;
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

        // Ambil data username HANYA SATU KALI di awal onCreate()
        String username = getIntent().getStringExtra("EXTRA_USERNAME");

        // Periksa data username dan muat fragment awal yang sesuai
        if (username != null && !username.isEmpty()) {
            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("USERNAME_KEY", username);
            homeFragment.setArguments(bundle);
            loadFragment(homeFragment);
        } else {
            // Jika tidak ada data username, muat HomeFragment standar
            loadFragment(new HomeFragment());
        }
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        // Listener untuk item di Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_list) {
                selectedFragment = new ListFragment();
            } else if (itemId == R.id.nav_home) {
                // Gunakan kembali variabel username yang sudah ada
                HomeFragment homeFragment = new HomeFragment();
                if (username != null && !username.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("USERNAME_KEY", username);
                    homeFragment.setArguments(bundle);
                }
                selectedFragment = homeFragment;

            } else if (itemId == R.id.nav_about) {
                // Gunakan kembali variabel username yang sudah ada
                AboutFragment aboutFragment = new AboutFragment();
                if (username != null && !username.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("USERNAME_KEY", username);
                    aboutFragment.setArguments(bundle);
                }
                selectedFragment = aboutFragment;
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
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
//                Toast.makeText(MainActivity.this, "Logout berhasil", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            // Menambahkan 'flags' PENTING untuk membersihkan histori halaman
            // Ini mencegah pengguna menekan tombol 'back' dan kembali ke MainActivity setelah logout
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // Pindah ke halaman login
                startActivity(intent);

                popupWindow.dismiss(); // Menutup popup
                // Di sini Anda bisa menambahkan logika logout sesungguhnya (misal: kembali ke halaman login)
            });

            // Tampilkan popup di dekat menuButton
            popupWindow.showAsDropDown(menuButton, -100, 40);
        });
    }

    // Fungsi untuk memuat atau mengganti fragment di container
    void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}