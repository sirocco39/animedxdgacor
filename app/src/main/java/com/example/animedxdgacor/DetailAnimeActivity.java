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

        // ... kode untuk setup Toolbar dan ambil data anime tetap sama ...
        setSupportActionBar(binding.toolbarDetail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Anime anime = getIntent().getParcelableExtra(EXTRA_ANIME);
        if (anime != null) {
            populateAnimeDetails(anime);
        }

        // --- TAMBAHAN BARU: Memberi fungsi pada tombol REVIEW utama ---
        binding.btnReview.setOnClickListener(v -> {
            showReviewDialog();
        });
    }

    // --- FUNGSI BARU UNTUK MENAMPILKAN DIALOG ---
    private void showReviewDialog() {
        // Membuat builder untuk dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Mengambil layout inflater untuk mengubah XML menjadi View object
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_review, null);
        builder.setView(dialogView);

        // Membuat object dialog
        AlertDialog dialog = builder.create();

        // Mengambil referensi view dari layout dialog
        TextInputLayout tilReviewLayout = dialogView.findViewById(R.id.til_review_layout); // <-- PENTING
        TextInputEditText etReviewInput = dialogView.findViewById(R.id.et_review_input);
        Button btnClose = dialogView.findViewById(R.id.btn_close);
        Button btnSubmit = dialogView.findViewById(R.id.btn_submit);

        // Memberi fungsi pada tombol Close
        btnClose.setOnClickListener(v -> {
            dialog.dismiss(); // Menutup dialog
        });

        // Memberi fungsi pada tombol Submit
        btnSubmit.setOnClickListener(v -> {
            String reviewText = etReviewInput.getText().toString().trim();

            // Validasi: Cek apakah input kosong
            if (reviewText.isEmpty()) {
                // ==========================================================
                // GANTI BAGIAN INI
                // Hapus atau beri comment: etReviewInput.setError("...");
                // Gunakan yang ini:
                tilReviewLayout.setError("Review must not be empty!");
                // ==========================================================

            } else {
                // Jangan lupa hapus errornya juga dari tilReviewLayout
                tilReviewLayout.setError(null);

                Toast.makeText(this, "Review Submitted: " + reviewText, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        // PENTING: Agar sudut dialog membulat, set background window-nya transparan
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        // Menampilkan dialog ke layar
        dialog.show();
    }

    private void populateAnimeDetails(Anime anime) {
        // ... kode ini tidak berubah ...
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