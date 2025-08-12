package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

import java.util.List;

// Import class binding yang otomatis digenerate dari item_anime.xml
import com.example.animedxdgacor.databinding.ItemAnimeBinding;// Ganti dengan package Anda

import model.Anime;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private final List<Anime> animeList;
    private OnItemClickCallback onItemClickCallback;

    public AnimeAdapter(List<Anime> animeList) {
        this.animeList = animeList;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Menggunakan ViewBinding untuk inflate layout
        ItemAnimeBinding binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AnimeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        Anime anime = animeList.get(position);
        holder.bind(anime);

        holder.itemView.setOnClickListener(v -> {
            onItemClickCallback.onItemClicked(animeList.get(holder.getAdapterPosition()));
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    // ViewHolder menggunakan ViewBinding
    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        private final ItemAnimeBinding binding;

        public AnimeViewHolder(ItemAnimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Anime anime) {
            binding.tvTitle.setText(anime.getJudul() + " (" + anime.getTahun() + ")");
            binding.tvGenre.setText(anime.getGenre());
            binding.tvSynopsis.setText(anime.getSinopsis());

            Glide.with(itemView.getContext())
                    .load(anime.getGambar())
                    .into(binding.ivPoster);
        }
    }

    // Interface untuk click
    public interface OnItemClickCallback {
        void onItemClicked(Anime data);
    }
}