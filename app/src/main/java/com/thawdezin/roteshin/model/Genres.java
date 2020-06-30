package com.thawdezin.roteshin.model;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thawdezin.roteshin.utils.InMemoryStore;

/**
 * Created by Thaw De Zin on June 19, 2020
 */

public final class Genres {

    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @NonNull
    @Override
    public String toString() {
        return "Genres{" +
                "genres=" + genres +
                '}';
    }

    private String getGenresLabel(List<Integer> genreIds) {
        List<Genre> allGenres =  InMemoryStore.getInstance().getGenresList().getGenres();
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
}