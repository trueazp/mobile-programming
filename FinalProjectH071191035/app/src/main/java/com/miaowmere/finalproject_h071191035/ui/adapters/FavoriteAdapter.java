package com.miaowmere.finalproject_h071191035.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miaowmere.finalproject_h071191035.R;

import com.miaowmere.finalproject_h071191035.data.models.Favorite;
import com.miaowmere.finalproject_h071191035.ui.adapters.clicklistener.OnFavoriteClickListener;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<Favorite> favoriteList;
    private OnFavoriteClickListener onFavoriteClickListener;

    public FavoriteAdapter(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public void setOnFavoriteClickListener(OnFavoriteClickListener onFavoriteClickListener) {
        this.onFavoriteClickListener = onFavoriteClickListener;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBindItem(favoriteList.get(position));
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Favorite favorite;
        ImageView ivPoster;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_poster_grid);
            tvName = itemView.findViewById(R.id.tv_title_grid);
            itemView.setOnClickListener(this);
        }

        public void onBindItem(Favorite favorite) {
            this.favorite = favorite;
            Glide.with(itemView.getContext()).load(favorite.getPosterPath()).into(ivPoster);
            tvName.setText(favorite.getName());
        }

        @Override
        public void onClick(View v) {
            onFavoriteClickListener.onClick(favorite);
        }
    }
}
