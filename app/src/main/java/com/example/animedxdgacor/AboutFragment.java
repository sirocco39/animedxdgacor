package com.example.animedxdgacor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView; 
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment {

    private TextView welcomeTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        
        
        welcomeTextView = view.findViewById(R.id.welcome_text1);

        
        Bundle bundle = getArguments();

        
        if (bundle != null) {
            String username = bundle.getString("USERNAME_KEY");
            if (username != null && !username.isEmpty()) {
                
                welcomeTextView.setText("Welcome, " + username);
            }
        }

        return view;
    }
}