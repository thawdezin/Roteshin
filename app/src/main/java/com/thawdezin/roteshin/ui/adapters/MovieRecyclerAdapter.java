package com.thawdezin.roteshin.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thawdezin.roteshin.R;
import com.thawdezin.roteshin.app.GlideApp;
import com.thawdezin.roteshin.app.GlideRequests;
import com.thawdezin.roteshin.model.Movie;
import com.thawdezin.roteshin.ui.adapters.viewHolders.MovieViewHolder;

import java.util.List;

/**
 * Created by Thaw De Zin on June 22, 2020
 */
public final class MovieRecyclerAdapter  extends RecyclerView.Adapter<MovieViewHolder> {

    List<Movie> movieList;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return new MovieViewHolder(view, GlideApp.with(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bindMovie(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

}
