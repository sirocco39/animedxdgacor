package com.example.animedxdgacor;

import model.Anime;

public class recomAnimeItem {
    private final Anime anime;

    public recomAnimeItem(Anime anime) {
        this.anime = anime;
    }

    public int getImageResource() {
        return anime.getGambar();
    }

    public String getTitle() {
        return anime.getJudul();
    }

    public Anime getAnime() {
        return anime;
    }
}