package com.thawdezin.roteshin.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.thawdezin.roteshin.R;
import com.thawdezin.roteshin.model.Result;


import java.util.List;

/**
 * Created by Thaw De Zin on June 22, 2020
 */

public class RecyclerAdapterPopular extends RecyclerView.Adapter<RecyclerAdapterPopular.PopularViewHolder> {

    Context context;
    List<Result> movieList;
    Result result;

    public RecyclerAdapterPopular(Context context, List<Result> movieList) {
        this.context = context;
        this.movieList = movieList;

    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new RecyclerAdapterPopular.PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterPopular.PopularViewHolder holder, int position) {
        Result result = movieList.get(position);
        holder.tvTitleNowShow.setText(result.getTitle());
        //Glide.with(itemView.getContext()).load(downloadUri).into(blogImageView);
        Glide.with(holder.ivItemNowShow.getContext())
                .load(result.getUrlThumbnailUrl())
                .into(holder.ivItemNowShow);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(List<Result> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView tvTitleNowShow;
        MaterialTextView tvGenresNowShow;
        ImageView ivItemNowShow;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleNowShow = itemView.findViewById(R.id.tvItemTitle);
            tvGenresNowShow = itemView.findViewById(R.id.tvMoiveItemGenre);
            ivItemNowShow = itemView.findViewById(R.id.ivMovieItem);
        }
    }
}
