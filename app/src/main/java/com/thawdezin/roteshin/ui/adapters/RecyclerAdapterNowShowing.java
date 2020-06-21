package com.thawdezin.roteshin.ui.adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.thawdezin.roteshin.R;
import com.thawdezin.roteshin.model.MovieResult;
import com.thawdezin.roteshin.model.Result;

import java.util.List;

/**
 * Created by Thaw De Zin on June 22, 2020
 */
public class RecyclerAdapterNowShowing extends RecyclerView.Adapter<RecyclerAdapterNowShowing.NowShowingViewHolder> {

    Context context;
    List<Result> movieList;
    Result result;

    public RecyclerAdapterNowShowing(Context context, List<Result> movieList) {
        this.context = context;
        this.movieList = movieList;

    }

    @NonNull
    @Override
    public RecyclerAdapterNowShowing.NowShowingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new NowShowingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterNowShowing.NowShowingViewHolder holder, int position) {
        Result result = movieList.get(position);
        holder.tvTitleNowShow.setText(result.getTitle());
        Log.e(">>>>",result.getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class NowShowingViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView tvTitleNowShow;
        MaterialTextView tvGenresNowShow;
        ImageView ivItemNowShow;
        public NowShowingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleNowShow = itemView.findViewById(R.id.tvItemTitle);
            tvGenresNowShow = itemView.findViewById(R.id.tvMoiveItemGenre);
            ivItemNowShow = itemView.findViewById(R.id.ivMovieItem);
        }
    }
}
