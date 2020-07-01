package com.thawdezin.roteshin.utils;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.thawdezin.roteshin.model.Genre;
import com.thawdezin.roteshin.model.Genres;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thaw De Zin on June 18, 2020
 */
public final class InMemoryStore {

    private static final InMemoryStore ourInstance = new InMemoryStore();

    private Genres genresList;

    @NonNull
    private final List<Genre> genres = new ArrayList<>();

    @NonNull
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(@NonNull List<Genre> genres){
        this.genres.clear();
        this.genres.addAll(genres);
    }

    private InMemoryStore() {
    }

    public static InMemoryStore getInstance() {
        return ourInstance;
    }

    public Genres getGenresList() {
        return genresList;
    }

    public void setGenresList(Genres genresList) {
        this.genresList = genresList;
    }

    public static String getGenresLabel(List<Integer> genreIds) {
        List<Genre> allGenres = InMemoryStore.getInstance().getGenresList().getGenres();
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
