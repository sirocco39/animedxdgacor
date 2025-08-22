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

    
    private FragmentListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        
        binding = FragmentListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        binding.rvAnime.setHasFixedSize(true);
        binding.rvAnime.setLayoutManager(new LinearLayoutManager(getContext()));

        
        ArrayList<Anime> listAnime = com.example.animedxdgacor.AnimeRepository.getDummyData();

        
        AnimeAdapter animeAdapter = new AnimeAdapter(listAnime);
        binding.rvAnime.setAdapter(animeAdapter);

        
        animeAdapter.setOnItemClickCallback(data -> {
            Intent intent = new Intent(getActivity(), DetailAnimeActivity.class);
            intent.putExtra(DetailAnimeActivity.EXTRA_ANIME, data);
            startActivity(intent);
        });
    }

    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}