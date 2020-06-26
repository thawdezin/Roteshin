package com.thawdezin.roteshin.model;

import androidx.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Thaw De Zin on June 19, 2020
 */

public class Genres {

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

}