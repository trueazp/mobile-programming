package com.miaowmere.animelist_bottomnav;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AnimeRecyclerAdapter extends RecyclerView.Adapter<AnimeRecyclerAdapter.ViewHolder> {
    private List<Anime> animes;
    private OnItemClickListener<Anime> clickListener;

    public void setAnimes(ArrayList<Anime> animes) {
        this.animes = animes;
    }

    public void setClickListener(OnItemClickListener<Anime> clickListener) {
        this.clickListener = clickListener;
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
        holder.onBind(animes.get(position));
    }


    @Override
    public int getItemCount() {
        return animes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Anime anime;
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

        @Override
        public void onClick(View v) {
            clickListener.onClick(anime);
        }

        public void onBind(Anime anime) {
            this.anime = anime;
            ivAnimePoster.setImageResource(anime.getPoster());
            tvAnimeTitle.setText(anime.getTitle());
            tvAnimeGenre.setText(anime.getGenre());
            tvAnimeDescription.setText(anime.getDescription());
        }
    }
}
