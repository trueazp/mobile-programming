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
import com.miaowmere.finalproject_h071191035.data.models.FavoriteTvShow;
import com.miaowmere.finalproject_h071191035.ui.adapters.clicklistener.OnItemClickListener;
import com.miaowmere.finalproject_h071191035.utilities.ImageSize;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavoriteTvShowAdapter extends RecyclerView.Adapter<FavoriteTvShowAdapter.ViewHolder> {

    private List<FavoriteTvShow> tvShowList;
    private OnItemClickListener clickListener;

    public FavoriteTvShowAdapter(List<FavoriteTvShow> tvShows) {
        this.tvShowList = tvShows;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavoriteTvShowAdapter.ViewHolder holder, int position) {
        holder.onBindItemView(tvShowList.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        FavoriteTvShow favoriteTvShow;
        ImageView ivPoster;
        TextView tvName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_poster_favorite);
            tvName = itemView.findViewById(R.id.tv_title_favorite);
            itemView.setOnClickListener(this);
        }

        public void onBindItemView(FavoriteTvShow favoriteTvShow) {
            this.favoriteTvShow = favoriteTvShow;
            String image = favoriteTvShow.getPosterPath(ImageSize.W154);
            String title = favoriteTvShow.getTitle();
            Glide.with(itemView.getContext()).load(image).into(ivPoster);
            tvName.setText(title);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(favoriteTvShow);
        }
    }
}
