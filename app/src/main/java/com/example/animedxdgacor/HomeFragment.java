package com.example.animedxdgacor;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout; // Tambahkan import LinearLayout
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView welcomeTextView;
    private Button newsButton;
    private Button mangaButton;

    // Tambahkan variabel untuk layout konten
    private LinearLayout newsContentLayout;
    private LinearLayout mangaContentLayout;

    // Tambahkan variabel untuk banner carousel
    private ViewPager2 bannerViewPager;
    private BannerAdapter bannerAdapter;
    private List<Integer> bannerImages;
    private Handler sliderHandler = new Handler();
    private Runnable sliderRunnable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inisialisasi TextView
        welcomeTextView = view.findViewById(R.id.text_view_welcome);

        // Ambil Bundle yang dikirim dari MainActivity
        Bundle bundle = getArguments();

        if (bundle != null) {
            String username = bundle.getString("USERNAME_KEY");
            if (username != null && !username.isEmpty()) {
                welcomeTextView.setText("Welcome, " + username);
            }
        }

        // Inisialisasi tombol tab
        newsButton = view.findViewById(R.id.button_news);
        mangaButton = view.findViewById(R.id.button_manga);

        // Inisialisasi layout konten
        newsContentLayout = view.findViewById(R.id.news_content_layout);
        mangaContentLayout = view.findViewById(R.id.manga_content_layout);

        // Inisialisasi banner carousel
        bannerViewPager = view.findViewById(R.id.banner_view_pager);
        setupBannerCarousel();

        // Tambahkan listener untuk tombol tab
        newsButton.setOnClickListener(v -> {
            updateTabButtons(newsButton, mangaButton);
            showNewsContent();
        });

        mangaButton.setOnClickListener(v -> {
            updateTabButtons(mangaButton, newsButton);
            showMangaContent();
        });

        // Tampilan awal
        updateTabButtons(newsButton, mangaButton);
        showNewsContent(); // Tampilkan konten NEWS secara default

        return view;
    }

    private void setupBannerCarousel() {
        // Daftar gambar untuk banner carousel
        bannerImages = new ArrayList<>();
        bannerImages.add(R.drawable.image_news1);
        bannerImages.add(R.drawable.image_news2);
        bannerImages.add(R.drawable.image_news3);
        bannerImages.add(R.drawable.image_news4);
        bannerImages.add(R.drawable.image_news5);

        bannerAdapter = new BannerAdapter(bannerImages);
        bannerViewPager.setAdapter(bannerAdapter);

        // Menentukan posisi awal yang "mirip" dengan item pertama
        int startPosition = Integer.MAX_VALUE / 2;
        bannerViewPager.setCurrentItem(startPosition - (startPosition % bannerImages.size()), false);


        // Mengatur ViewPager2 agar otomatis bergulir
        sliderRunnable = () -> {
            int currentItem = bannerViewPager.getCurrentItem();
            bannerViewPager.setCurrentItem(currentItem + 1, true);
        };

        // Menghentikan gulir otomatis saat user menggeser secara manual
        bannerViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    sliderHandler.removeCallbacks(sliderRunnable);
                } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    // Mulai lagi gulir otomatis setelah 5 detik
                    sliderHandler.postDelayed(sliderRunnable, 5000);
                }
            }
        });
    }

    private void showNewsContent() {
        newsContentLayout.setVisibility(View.VISIBLE);
        mangaContentLayout.setVisibility(View.GONE);
    }

    private void showMangaContent() {
        newsContentLayout.setVisibility(View.GONE);
        mangaContentLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Metode untuk mengubah tampilan tombol tab saat diklik.
     * @param activeButton Tombol yang baru saja diklik dan akan diaktifkan
     * @param inactiveButton Tombol yang sebelumnya aktif dan akan dinonaktifkan
     */
    private void updateTabButtons(Button activeButton, Button inactiveButton) {
        activeButton.setBackgroundResource(R.drawable.home_rounded_button_orange);
        activeButton.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));

        inactiveButton.setBackgroundResource(R.drawable.home_rounded_button_white);
        inactiveButton.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black));
    }

    @Override
    public void onResume() {
        super.onResume();
        // Hanya mulai slider jika konten NEWS yang terlihat
        if (newsContentLayout != null && newsContentLayout.getVisibility() == View.VISIBLE) {
            sliderHandler.postDelayed(sliderRunnable, 5000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }
}






//package com.example.animedxdgacor;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//import androidx.viewpager2.widget.ViewPager2;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HomeFragment extends Fragment {
//
//    private TextView welcomeTextView;
//    private Button newsButton;
//    private Button mangaButton;
//
//    // Tambahkan variabel untuk banner carousel
//    private ViewPager2 bannerViewPager;
//    private BannerAdapter bannerAdapter;
//    private List<Integer> bannerImages;
//    private Handler sliderHandler = new Handler();
//    private Runnable sliderRunnable;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//
//        // Inisialisasi TextView
//        welcomeTextView = view.findViewById(R.id.text_view_welcome);
//
//        // Ambil Bundle yang dikirim dari MainActivity
//        Bundle bundle = getArguments();
//
//        if (bundle != null) {
//            String username = bundle.getString("USERNAME_KEY");
//            if (username != null && !username.isEmpty()) {
//                welcomeTextView.setText("Welcome, " + username);
//            }
//        }
//
//        // Inisialisasi tombol tab
//        newsButton = view.findViewById(R.id.button_news);
//        mangaButton = view.findViewById(R.id.button_manga);
//
//        // Inisialisasi banner carousel
//        bannerViewPager = view.findViewById(R.id.banner_view_pager);
//        setupBannerCarousel();
//
//        // Tambahkan listener untuk tombol tab
//        newsButton.setOnClickListener(v -> {
//            Toast.makeText(getContext(), "NEWS Tab Selected", Toast.LENGTH_SHORT).show();
//            updateTabButtons(newsButton, mangaButton);
//        });
//
//        mangaButton.setOnClickListener(v -> {
//            Toast.makeText(getContext(), "MANGA Tab Selected", Toast.LENGTH_SHORT).show();
//            updateTabButtons(mangaButton, newsButton);
//        });
//
//        // Tampilan awal
//        updateTabButtons(newsButton, mangaButton);
//
//        return view;
//    }
//
//    private void setupBannerCarousel() {
//        // Daftar gambar untuk banner carousel
//        bannerImages = new ArrayList<>();
//        bannerImages.add(R.drawable.image_news1);
//        bannerImages.add(R.drawable.image_news2);
//        bannerImages.add(R.drawable.image_news3);
//        bannerImages.add(R.drawable.image_news4);
//        bannerImages.add(R.drawable.image_news5);
//
//        bannerAdapter = new BannerAdapter(bannerImages);
//        bannerViewPager.setAdapter(bannerAdapter);
//
//        // Menentukan posisi awal yang "mirip" dengan item pertama
//        // Ini adalah kunci agar swipe ke kanan dari awal bisa berfungsi
//        int startPosition = Integer.MAX_VALUE / 2;
//        bannerViewPager.setCurrentItem(startPosition - (startPosition % bannerImages.size()), false);
//
//
//        // Mengatur ViewPager2 agar otomatis bergulir
//        sliderRunnable = () -> {
//            int currentItem = bannerViewPager.getCurrentItem();
//            bannerViewPager.setCurrentItem(currentItem + 1, true);
//        };
//
//        // Menghentikan gulir otomatis saat user menggeser secara manual
//        bannerViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
//                    sliderHandler.removeCallbacks(sliderRunnable);
//                } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
//                    // Mulai lagi gulir otomatis setelah 5 detik
//                    sliderHandler.postDelayed(sliderRunnable, 5000);
//                }
//            }
//        });
//    }
//
//    /**
//     * Metode untuk mengubah tampilan tombol tab saat diklik.
//     * @param activeButton Tombol yang baru saja diklik dan akan diaktifkan
//     * @param inactiveButton Tombol yang sebelumnya aktif dan akan dinonaktifkan
//     */
//    private void updateTabButtons(Button activeButton, Button inactiveButton) {
//        activeButton.setBackgroundResource(R.drawable.home_rounded_button_orange);
//        activeButton.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));
//
//        inactiveButton.setBackgroundResource(R.drawable.home_rounded_button_white);
//        inactiveButton.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black));
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        sliderHandler.postDelayed(sliderRunnable, 5000); // Mulai gulir otomatis saat fragment terlihat
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        sliderHandler.removeCallbacks(sliderRunnable); // Hentikan gulir otomatis saat fragment tidak terlihat
//    }
//}