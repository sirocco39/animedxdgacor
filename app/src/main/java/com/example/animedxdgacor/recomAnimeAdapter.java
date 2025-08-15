package com.example.animedxdgacor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recomAnimeAdapter extends RecyclerView.Adapter<recomAnimeAdapter.AnimeViewHolder> {

    private final List<recomAnimeItem> animeList;
    private OnItemClickCallback onItemClickCallback;

    public recomAnimeAdapter(List<recomAnimeItem> animeList) {
        this.animeList = animeList;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recom_anime, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        recomAnimeItem currentItem = animeList.get(position);
        holder.animeCover.setImageResource(currentItem.getImageResource());
        holder.animeTitle.setText(currentItem.getTitle());

        holder.itemView.setOnClickListener(v -> {
            onItemClickCallback.onItemClicked(animeList.get(holder.getAdapterPosition()));
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    static class AnimeViewHolder extends RecyclerView.ViewHolder {
        ImageView animeCover;
        TextView animeTitle;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            animeCover = itemView.findViewById(R.id.anime_cover_image);
            animeTitle = itemView.findViewById(R.id.anime_title_text);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(recomAnimeItem data);
    }
}