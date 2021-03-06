package com.miaowmere.moviecatalogue.adapters;

import com.bumptech.glide.Glide;
import com.miaowmere.moviecatalogue.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miaowmere.moviecatalogue.models.NowPlayingModel;
import com.miaowmere.moviecatalogue.networks.Const;
import com.miaowmere.moviecatalogue.networks.OnItemClickListener;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {
    private final List<NowPlayingModel> nowPlaying;
    private final OnItemClickListener<NowPlayingModel> clickListener;

    public NowPlayingAdapter(List<NowPlayingModel> nowPlaying, OnItemClickListener<NowPlayingModel> clickListener) {
        this.nowPlaying = nowPlaying;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.television_item, parent, false);
        return new NowPlayingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).
                load(Const.IMG_URL_200 + nowPlaying.get(position).getImgUrl())
                .into(holder.ivPoster);
        holder.tvTitle.setText(nowPlaying.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return nowPlaying.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView ivPoster;
        private final TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(nowPlaying.get(getBindingAdapterPosition()));
        }
    }
}
