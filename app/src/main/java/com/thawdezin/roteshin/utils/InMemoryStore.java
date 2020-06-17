package com.thawdezin.roteshin.utils;

import com.thawdezin.roteshin.model.GenresList;

/**
 * Created by Thaw De Zin on June 18, 2020
 */
public class InMemoryStore {

    private static final InMemoryStore ourInstance = new InMemoryStore();

    private GenresList genresList;

    private InMemoryStore() {
    }

    public static InMemoryStore getInstance() {
        return ourInstance;
    }

    public GenresList getGenresList() {
        return genresList;
    }

    public void setGenresList(GenresList genresList) {
        this.genresList = genresList;
    }
}
