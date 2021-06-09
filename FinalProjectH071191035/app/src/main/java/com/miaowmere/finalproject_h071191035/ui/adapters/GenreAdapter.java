package com.miaowmere.finalproject_h071191035.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.miaowmere.finalproject_h071191035.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private ArrayList<String> genres;
    private Context context;

    public GenreAdapter(ArrayList<String> genres, Context context) {
        this.genres = genres;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.genre_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull GenreAdapter.ViewHolder holder, int position) {
        final String genre = genres.get(position);
        holder.setGenre(genre);
    }

    @Override
    public int getItemCount() {
        return genres == null ? 0 : genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvGenre;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.tv_genre_rv);
        }

        public void setGenre(String genre) {
            tvGenre.setText(genre);
        }
    }
}
