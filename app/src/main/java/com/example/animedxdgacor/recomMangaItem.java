package com.example.animedxdgacor;

public class recomMangaItem {
    private final int imageResource;
    private final String title;

    public recomMangaItem(int imageResource, String title) {
        this.imageResource = imageResource;
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }
}
