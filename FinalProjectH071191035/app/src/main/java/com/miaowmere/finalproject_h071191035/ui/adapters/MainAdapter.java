package com.miaowmere.finalproject_h071191035.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miaowmere.finalproject_h071191035.ImageSize;
import com.miaowmere.finalproject_h071191035.R;

import com.miaowmere.finalproject_h071191035.data.models.Movie;
import com.miaowmere.finalproject_h071191035.data.models.TvShow;
import com.miaowmere.finalproject_h071191035.ui.adapters.clicklistener.OnItemClickListener;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Movie> movies;
    private List<TvShow> tvShows;
    private OnItemClickListener clickListener;

    public MainAdapter(List<Movie> movies, List<TvShow> tvShows) {
        this.movies = movies;
        this.tvShows = tvShows;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (movies != null) {
            holder.onBindItemView(movies.get(position));
        } else {
            holder.onBindItemView(tvShows.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : tvShows.size();
    }

    public void appendList(List<Movie> movieList, List<TvShow> tvShowList) {
        if (movies != null) {
            movies.addAll(movieList);
        } else {
            tvShows.addAll(tvShowList);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Movie movie;
        TvShow tvShow;
        ImageView ivPoster;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_poster_grid);
            tvName = itemView.findViewById(R.id.tv_title_grid);
            itemView.setOnClickListener(this);
        }

        public void onBindItemView(Movie movie) {
            this.movie = movie;
            String image = movie.getPosterPath(ImageSize.W154);
            String title = movie.getTitle();
            Glide.with(itemView.getContext()).load(image).into(ivPoster);
            tvName.setText(title);
        }

        public void onBindItemView(TvShow tvShow) {
            this.tvShow = tvShow;
            String image = tvShow.getPosterPath(ImageSize.W154);
            String title = tvShow.getName();
            Glide.with(itemView.getContext()).load(image).into(ivPoster);
            tvName.setText(title);
        }

        @Override
        public void onClick(View v) {
            if (movie != null) {
                clickListener.onClick(movie);
            } else {
                clickListener.onClick(tvShow);
            }
        }
    }
}
