package com.example.animedxdgacor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MangaViewHolder> {

    private final List<MangaItem> mangaList;

    public MangaAdapter(List<MangaItem> mangaList) {
        this.mangaList = mangaList;
    }

    @NonNull
    @Override
    public MangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_manga, parent, false);
        return new MangaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MangaViewHolder holder, int position) {
        MangaItem currentItem = mangaList.get(position);
        holder.mangaCover.setImageResource(currentItem.getImageResource());
        holder.mangaTitle.setText(currentItem.getTitle());
        holder.mangaDesc.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }

    static class MangaViewHolder extends RecyclerView.ViewHolder {
        ImageView mangaCover;
        TextView mangaTitle;

        TextView mangaDesc;

        public MangaViewHolder(@NonNull View itemView) {
            super(itemView);
            mangaCover = itemView.findViewById(R.id.manga_cover_image);
            mangaTitle = itemView.findViewById(R.id.manga_title_text);
            mangaDesc = itemView.findViewById(R.id.manga_description_text);
        }
    }
}
