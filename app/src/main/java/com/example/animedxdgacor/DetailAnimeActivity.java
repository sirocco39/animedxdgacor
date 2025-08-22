package com.example.animedxdgacor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.animedxdgacor.databinding.ActivityDetailAnimeBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import model.Anime;

public class DetailAnimeActivity extends AppCompatActivity {

    public static final String EXTRA_ANIME = "extra_anime";
    private ActivityDetailAnimeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailAnimeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbarDetail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Anime anime = getIntent().getParcelableExtra(EXTRA_ANIME);
        if (anime != null) {
            populateAnimeDetails(anime);
        }
        binding.btnReview.setOnClickListener(v -> {
            showReviewDialog();
        });
    }
    private void showReviewDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_review, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        TextInputLayout tilReviewLayout = dialogView.findViewById(R.id.til_review_layout); 
        TextInputEditText etReviewInput = dialogView.findViewById(R.id.et_review_input);
        Button btnClose = dialogView.findViewById(R.id.btn_close);
        Button btnSubmit = dialogView.findViewById(R.id.btn_submit);
        btnClose.setOnClickListener(v -> {
            dialog.dismiss(); 
        });
        btnSubmit.setOnClickListener(v -> {
            String reviewText = etReviewInput.getText().toString().trim();

            
            if (reviewText.isEmpty()) {
                
                
                
                
                tilReviewLayout.setError("Review must not be empty!");
                

            } else {
                
                tilReviewLayout.setError(null);

                Toast.makeText(this, "Review Submitted: " + reviewText, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        
        dialog.show();
    }

    private void populateAnimeDetails(Anime anime) {
        
        Glide.with(this).load(anime.getGambar()).into(binding.ivDetailPoster);
        binding.tvDetailTitle.setText(anime.getJudul());
        binding.tvDetailRating.setText("Rating : " + anime.getRating());
        binding.tvDetailGenre.setText(anime.getGenre());
        binding.tvDetailEpisodes.setText("TV " + anime.getJumlahEpisode() + " Eps");
        binding.tvDetailSynopsis.setText(anime.getSinopsis());
        binding.tvDetailYear.setText(String.valueOf(anime.getTahun()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}