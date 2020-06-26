package com.thawdezin.roteshin.ui.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.thawdezin.roteshin.R;
import com.thawdezin.roteshin.model.Genre;
import com.thawdezin.roteshin.model.Genres;
import com.thawdezin.roteshin.model.Result;
import com.thawdezin.roteshin.utils.InMemoryStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thaw De Zin on June 22, 2020
 */
public class MovieRecyclerAdapter  extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {

    Context context;
    List<Result> movieList;

    public MovieRecyclerAdapter(Context context, List<Result> movieList) {
        this.context = context;
        this.movieList = movieList;

    }

    @NonNull
    @Override
    public MovieRecyclerAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new MovieRecyclerAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerAdapter.MovieViewHolder holder, int position) {
        Result result = movieList.get(position);
        holder.tvTitleNowShow.setText(result.getTitle());
        holder.tvGenresNowShow.setText(getGenresLabel(result.getGenreIds()));
        Glide.with(holder.ivItemNowShow.getContext())
                .load(result.getUrlThumbnailUrl())
                .error(R.drawable.place_holder)
                .into(holder.ivItemNowShow);
    }

    private String getGenresLabel(List<Integer> genreIds) {
        Genres g = InMemoryStore.getInstance().getGenresList();
        List<Genre> allGenres =  g.getGenres();
        List<String> movieGenres = new ArrayList<>();
        for (Integer genreId : genreIds) {
            for (Genre genre : allGenres) {
                if (genre.getId().equals(genreId)) {
                    movieGenres.add(genre.getName());
                    break;
                }
            }
        }
        return TextUtils.join(", ", movieGenres);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(List<Result> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView tvTitleNowShow;
        MaterialTextView tvGenresNowShow;
        ImageView ivItemNowShow;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleNowShow = itemView.findViewById(R.id.tvItemTitle);
            tvGenresNowShow = itemView.findViewById(R.id.tvMoiveItemGenre);
            ivItemNowShow = itemView.findViewById(R.id.ivMovieItem);
        }
    }
}
