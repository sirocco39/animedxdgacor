package com.example.animedxdgacor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.animedxdgacor.databinding.FragmentListBinding;

import java.util.ArrayList;

import adapter.AnimeAdapter;
import model.Anime;

public class ListFragment extends Fragment {

    // Gunakan ViewBinding untuk Fragment
    private FragmentListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate layout menggunakan ViewBinding
        binding = FragmentListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Panggil logika RecyclerView di sini
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        binding.rvAnime.setHasFixedSize(true);
        binding.rvAnime.setLayoutManager(new LinearLayoutManager(getContext()));

        // Ambil data dari Repository
        ArrayList<Anime> listAnime = com.example.animedxdgacor.AnimeRepository.getDummyData();

        // Buat adapter dan set
        AnimeAdapter animeAdapter = new AnimeAdapter(listAnime);
        binding.rvAnime.setAdapter(animeAdapter);

        // Set click listener untuk navigasi ke DetailActivity
        animeAdapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(getActivity(), DetailAnimeActivity.class);
            intent.putExtra(DetailAnimeActivity.EXTRA_ANIME, data);
            startActivity(intent);
        });
    }

    // Penting untuk membersihkan binding saat view dihancurkan untuk menghindari memory leak
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}