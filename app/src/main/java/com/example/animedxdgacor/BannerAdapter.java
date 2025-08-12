package com.example.animedxdgacor;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    private final List<Integer> bannerImages;

    public BannerAdapter(List<Integer> bannerImages) {
        this.bannerImages = bannerImages;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Mengubah inflate(R.drawable.item_banner, ...) menjadi inflate(R.layout.item_banner, ...)
        @SuppressLint("ResourceType") View view = LayoutInflater.from(parent.getContext())
                .inflate(R.drawable.item_banner, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        // Menggunakan operator modulo untuk membuat carousel "infinite"
        // Mengambil gambar dari daftar asli berdasarkan posisi yang dilingkari
        int actualPosition = position % bannerImages.size();
        holder.imageView.setImageResource(bannerImages.get(actualPosition));
    }

    @Override
    public int getItemCount() {
        // Mengembalikan nilai yang sangat besar untuk memberikan ilusi loop tak terbatas
        return Integer.MAX_VALUE;
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.banner_image);
        }
    }
}







//package com.example.animedxdgacor;
//
//import android.annotation.SuppressLint;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
//
//    private final List<Integer> bannerImages;
//
//    public BannerAdapter(List<Integer> bannerImages) {
//        this.bannerImages = bannerImages;
//    }
//
//    @NonNull
//    @Override
//    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        @SuppressLint("ResourceType") View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.drawable.item_banner, parent, false);
//        return new BannerViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
//        holder.imageView.setImageResource(bannerImages.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return bannerImages.size();
//    }
//
//    static class BannerViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//
//        public BannerViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.banner_image);
//        }
//    }
//}
