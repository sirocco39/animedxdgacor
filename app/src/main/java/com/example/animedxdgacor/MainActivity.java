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

        
        String username = getIntent().getStringExtra("EXTRA_USERNAME");

        
        if (username != null && !username.isEmpty()) {
            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("USERNAME_KEY", username);
            homeFragment.setArguments(bundle);
            loadFragment(homeFragment);
        } else {
            
            loadFragment(new HomeFragment());
        }
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_list) {
                selectedFragment = new ListFragment();
            } else if (itemId == R.id.nav_home) {
                
                HomeFragment homeFragment = new HomeFragment();
                if (username != null && !username.isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("USERNAME_KEY", username);
                    homeFragment.setArguments(bundle);
                }
                selectedFragment = homeFragment;

            } else if (itemId == R.id.nav_about) {
                
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

        
        menuButton.setOnClickListener(view -> {
            
            View popupView = getLayoutInflater().inflate(R.layout.popup_logout, null);

            
            menuButton.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.orange));

            
            PopupWindow popupWindow = new PopupWindow(popupView,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    true); 

            
            popupWindow.setOnDismissListener(() ->
                    menuButton.setColorFilter(ContextCompat.getColor(MainActivity.this, android.R.color.black))
            );

            
            TextView btnLogout = popupView.findViewById(R.id.btn_logout);

            
            btnLogout.setOnClickListener(v -> {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            
            
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            
                startActivity(intent);

                popupWindow.dismiss(); 
                
            });

            
            popupWindow.showAsDropDown(menuButton, -100, 40);
        });
    }

    
    void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}