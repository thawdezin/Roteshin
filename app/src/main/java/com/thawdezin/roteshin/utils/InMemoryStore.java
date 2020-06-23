package com.thawdezin.roteshin.utils;

import com.thawdezin.roteshin.model.Genres;

/**
 * Created by Thaw De Zin on June 18, 2020
 */
public class InMemoryStore {

    private static final InMemoryStore ourInstance = new InMemoryStore();

    private Genres genresList;

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
}
