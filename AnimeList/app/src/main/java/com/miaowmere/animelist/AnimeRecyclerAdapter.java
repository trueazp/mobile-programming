package com.miaowmere.animelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimeRecyclerAdapter extends RecyclerView.Adapter<AnimeRecyclerAdapter.ViewHolder> {
    private List<Anime> animes;

    public AnimeRecyclerAdapter(List<Anime> models) {
        this.animes = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Anime anime = animes.get(position);
        holder.ivAnimePoster.setBackgroundResource(anime.getPoster());
        holder.tvAnimeTitle.setText(anime.getTitle());
        holder.tvAnimeGenre.setText(anime.getGenre());
        holder.tvAnimeDescription.setText(anime.getDescription());
    }


    @Override
    public int getItemCount() {
        return animes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAnimePoster;
        TextView tvAnimeTitle;
        TextView tvAnimeGenre;
        TextView tvAnimeDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAnimePoster = itemView.findViewById(R.id.iv_anime_poster);
            tvAnimeTitle = itemView.findViewById(R.id.tv_anime_title);
            tvAnimeGenre = itemView.findViewById(R.id.tv_anime_genre);
            tvAnimeDescription = itemView.findViewById(R.id.tv_anime_desc);
        }
    }
}
