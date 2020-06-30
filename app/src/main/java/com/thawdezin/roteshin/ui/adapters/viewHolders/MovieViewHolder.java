package com.thawdezin.roteshin.ui.adapters.viewHolders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textview.MaterialTextView;
import com.thawdezin.roteshin.R;
import com.thawdezin.roteshin.app.GlideApp;
import com.thawdezin.roteshin.app.GlideRequests;
import com.thawdezin.roteshin.model.Movie;
import com.thawdezin.roteshin.utils.InMemoryStore;

import java.util.List;

/**
 * Created by Thaw De Zin on June 30, 2020
 */
public final class MovieViewHolder extends RecyclerView.ViewHolder {

    @NonNull final private GlideRequests glideRequests;

    private final MaterialTextView tvTitleNowShow;
    private final MaterialTextView tvGenresNowShow;
    private final ImageView ivItemNowShow;

    public MovieViewHolder(@NonNull View itemView,@NonNull GlideRequests glideRequests) {
        super(itemView);
        tvTitleNowShow = itemView.findViewById(R.id.tvItemTitle);
        tvGenresNowShow = itemView.findViewById(R.id.tvMoiveItemGenre);
        ivItemNowShow = itemView.findViewById(R.id.ivMovieItem);

        this.glideRequests = glideRequests;
    }

    public void bindMovie(Movie movie){
        tvTitleNowShow.setText(movie.getTitle());
        tvGenresNowShow.setText(InMemoryStore.getGenresLabel(movie.getGenreIds()));
        glideRequests
                .load(movie.getUrlThumbnailUrl())
                .error(R.drawable.place_holder)
                .into(ivItemNowShow);
    }
}
