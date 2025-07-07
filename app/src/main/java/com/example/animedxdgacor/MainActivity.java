package com.example.animedxdgacor;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

        // Default Fragment
        loadFragment(new HomeFragment());

        // Bottom Nav listener
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
                return true;
            }

            return false;
        });

        // Hamburger menu
        menuButton.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, menuButton);
            popupMenu.getMenuInflater().inflate(R.menu.drawer_menu, popupMenu.getMenu());

            // Ubah warna hamburger jadi oranye saat ditekan
            menuButton.setColorFilter(getResources().getColor(android.R.color.holo_orange_dark));

            popupMenu.setOnDismissListener(menu -> {
                // Kembalikan warna semula
                menuButton.setColorFilter(getResources().getColor(android.R.color.black));
            });

            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.logout) {
                    Toast.makeText(MainActivity.this, "Logout diklik", Toast.LENGTH_SHORT).show();
                    // Tambahkan aksi logout sesungguhnya di sini
                    return true;
                }
                return false;
            });

            popupMenu.show();
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
