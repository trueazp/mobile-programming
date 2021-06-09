package com.miaowmere.finalproject_h071191035.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miaowmere.finalproject_h071191035.R;
import com.miaowmere.finalproject_h071191035.data.models.Cast;
import com.miaowmere.finalproject_h071191035.utilities.ImageSize;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private List<Cast> casts;
    private Context context;

    public CastAdapter(List<Cast> casts, Context context) {
        this.casts = casts;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cast_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CastAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(casts.get(position).getName());
        holder.tvCharacter.setText(casts.get(position).getCharacter());
        String image = casts.get(position).getProfilePath(ImageSize.W342);
        Glide.with(holder.itemView.getContext()).load(image).into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        TextView tvName, tvCharacter;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_cast_pp);
            tvName = itemView.findViewById(R.id.tv_cast_name);
            tvCharacter = itemView.findViewById(R.id.tv_cast_character);
        }
    }
}
