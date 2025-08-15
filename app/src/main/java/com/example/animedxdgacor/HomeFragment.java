package com.example.animedxdgacor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import model.Anime;

public class HomeFragment extends Fragment {

    private TextView welcomeTextView;
    private Button newsButton;
    private Button mangaButton;

    // Variabel untuk layout konten
    private LinearLayout newsContentLayout;
    private LinearLayout mangaContentLayout;

    private ViewPager2 bannerViewPager;
    private BannerAdapter bannerAdapter;
    private List<Integer> bannerImages;
    private Handler sliderHandler = new Handler();
    private Runnable sliderRunnable;

    private RecyclerView newsMangaRecyclerView;
    private recomMangaAdapter newsMangaAdapter;
    private List<recomMangaItem> newsMangaList;

    private RecyclerView mangaGridRecyclerView;
    private MangaAdapter mangaGridAdapter;
    private List<MangaItem> mangaGridList;

    private RecyclerView animeRecyclerView;
    private recomAnimeAdapter recomAnimeAdapter;
    private List<recomAnimeItem> animeList;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        welcomeTextView = view.findViewById(R.id.text_view_welcome);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String username = bundle.getString("USERNAME_KEY");
            if (username != null && !username.isEmpty()) {
                welcomeTextView.setText("Welcome, " + username);
            }
        }

        newsButton = view.findViewById(R.id.button_news);
        mangaButton = view.findViewById(R.id.button_manga);
        View viewAllManga = view.findViewById(R.id.news_manga_view_all);
        View viewAllAnime = view.findViewById(R.id.news_anime_view_all);

        newsContentLayout = view.findViewById(R.id.news_content_layout);
        mangaContentLayout = view.findViewById(R.id.manga_content_layout);

        bannerViewPager = view.findViewById(R.id.banner_view_pager);
        setupBannerCarousel();

        newsMangaRecyclerView = view.findViewById(R.id.news_manga_recycler_view);
        setupNewsMangaRecommendationList();

        mangaGridRecyclerView = view.findViewById(R.id.manga_grid_recycler_view);
        setupMangaGridList();

        animeRecyclerView = view.findViewById(R.id.anime_recommendation_recycler_view);
        setupAnimeRecommendationList(view);

        newsButton.setOnClickListener(v -> {
            updateTabButtons(newsButton, mangaButton);
            showNewsContent(); // Tampilkan konten berita
        });

        mangaButton.setOnClickListener(v -> {
            updateTabButtons(mangaButton, newsButton);
            showMangaContent();
        });

        viewAllManga.setOnClickListener(v -> {
            updateTabButtons(mangaButton, newsButton);
            showMangaContent();
        });

        viewAllAnime.setOnClickListener((v ->{
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).loadFragment(new ListFragment());
                ((MainActivity) getActivity()).bottomNavigationView.setSelectedItemId(R.id.nav_list);
            }
        }));

        updateTabButtons(newsButton, mangaButton);
        showNewsContent();

        return view;
    }

    private void setupBannerCarousel() {
        bannerImages = new ArrayList<>();
        bannerImages.add(R.drawable.image_news1);
        bannerImages.add(R.drawable.image_news2);
        bannerImages.add(R.drawable.image_news3);
        bannerImages.add(R.drawable.image_news4);
        bannerImages.add(R.drawable.image_news5);

        bannerAdapter = new BannerAdapter(bannerImages);
        bannerViewPager.setAdapter(bannerAdapter);

        int startPosition = Integer.MAX_VALUE / 2;
        bannerViewPager.setCurrentItem(startPosition - (startPosition % bannerImages.size()), false);

        sliderRunnable = () -> {
            int currentItem = bannerViewPager.getCurrentItem();
            bannerViewPager.setCurrentItem(currentItem + 1, true);
        };

        bannerViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    sliderHandler.removeCallbacks(sliderRunnable);
                } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    sliderHandler.postDelayed(sliderRunnable, 5000);
                }
            }
        });
    }

    private void setupNewsMangaRecommendationList() {
        newsMangaList = new ArrayList<>();

        newsMangaList.add(new recomMangaItem(R.drawable.spy_x_family, "Spy x Family"));
        newsMangaList.add(new recomMangaItem(R.drawable.one_piece, "One Piece"));
        newsMangaList.add(new recomMangaItem(R.drawable.one_punch_man, "One-Punch Man"));
        newsMangaList.add(new recomMangaItem(R.drawable.death_note, "Death Note"));
        newsMangaList.add(new recomMangaItem(R.drawable.the_fable, "The Fable"));

        newsMangaAdapter = new recomMangaAdapter(newsMangaList);
        newsMangaRecyclerView.setAdapter(newsMangaAdapter);
        newsMangaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void setupMangaGridList() {
        mangaGridList = new ArrayList<>();

        mangaGridList.add(new MangaItem(R.drawable.one_piece, "One Piece", "A young man by the name of Monkey D. Luffy is ready to embark on his own adventure, searching for One Piece and striving to become the new King of the Pirates."));
        mangaGridList.add(new MangaItem(R.drawable.slam_dunk, "Slam Dunk", "Hanamichi Sakuragi, a tall, boisterous teenager with flame-red hair and physical strength beyond his years, is eager to put an end to his rejection streak of 50."));
        mangaGridList.add(new MangaItem(R.drawable.spy_x_family, "Spy x Family", "Twilight, or \"Loid Forger,\" quickly adopts the unassuming orphan Anya to play the role of a six-year-old daughter and prospective Eden Academy student."));
        mangaGridList.add(new MangaItem(R.drawable.dungeon_meshi, "Dungeon Meshi", "After the Golden Kingdom is sunk underground by an insane magician, its king emerges, promising all of his treasure to any who defeat the magician, before crumbling to dust."));
        mangaGridList.add(new MangaItem(R.drawable.hunter_x_hunter, "Hunter x Hunter", "Gon Freecss wants to become a Hunter so he can find his father, a man who abandoned him to pursue a life of adventure."));
        mangaGridList.add(new MangaItem(R.drawable.one_punch_man, "One-Punch Man", "After rigorously training for three years, the ordinary Saitama has gained immense strength which allows him to take out anyone and anything with just one punch."));
        mangaGridList.add(new MangaItem(R.drawable.death_note, "Death Note", "Ryuk, a god of death, drops his Death Note into the human world for personal pleasure. In Japan, prodigious high school student Light Yagami stumbles upon it."));
        mangaGridList.add(new MangaItem(R.drawable.pandora_hearts, "Pandora Hearts", "Reminiscent of a broken toy box, the mystical Abyss is a terrifying realm home to monstrous creatures called Chains."));
        mangaGridList.add(new MangaItem(R.drawable.the_fable, "The Fable", "After concluding his latest assignment, the number of people he has recently killed becomes too high, and Fable is ordered by his boss to lay low for a year."));

        mangaGridAdapter = new MangaAdapter(mangaGridList);
        mangaGridRecyclerView.setAdapter(mangaGridAdapter);
        mangaGridRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    private void setupAnimeRecommendationList(View view) {
        RecyclerView rvAnime = view.findViewById(R.id.anime_recommendation_recycler_view);

        rvAnime.setHasFixedSize(true);
        rvAnime.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<Anime> listAnime = com.example.animedxdgacor.AnimeRepository.getDummyData();
        List<recomAnimeItem> recomAnimeList = new ArrayList<>();
        for (Anime anime : listAnime) {
            recomAnimeList.add(new recomAnimeItem(anime));
        }

        recomAnimeAdapter = new recomAnimeAdapter(recomAnimeList);
        rvAnime.setAdapter(recomAnimeAdapter);

        recomAnimeAdapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(getActivity(), DetailAnimeActivity.class);
            intent.putExtra(DetailAnimeActivity.EXTRA_ANIME, data.getAnime());
            startActivity(intent);
        });
    }


    private void showNewsContent() {
        if (newsContentLayout != null) {
            newsContentLayout.setVisibility(View.VISIBLE);
        }
        if (mangaContentLayout != null) {
            mangaContentLayout.setVisibility(View.GONE);
        }
    }

    private void showMangaContent() {
        if (newsContentLayout != null) {
            newsContentLayout.setVisibility(View.GONE);
        }
        if (mangaContentLayout != null) {
            mangaContentLayout.setVisibility(View.VISIBLE);
        }
    }

    private void updateTabButtons(Button activeButton, Button inactiveButton) {
        activeButton.setBackgroundResource(R.drawable.home_rounded_button_orange);
        activeButton.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));

        inactiveButton.setBackgroundResource(R.drawable.home_rounded_button_white);
        inactiveButton.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black));
    }

    @Override
    public void onResume() {
        super.onResume();
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
