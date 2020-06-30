package com.thawdezin.roteshin.model;

import androidx.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public final class Movie {

	@SerializedName("title")
	private String title;

	@SerializedName("genre_ids")
	private List<Integer> genreIds;

	@SerializedName("poster_path")
	private String posterPath;

	public String getTitle(){
		return title;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public String getUrlThumbnailUrl(){
		return "https://image.tmdb.org/t/p/w500" + getPosterPath();
	}

	@NonNull
	@Override
	public String toString(){
		return
				"Movie{" +
						",title = '" + title + '\'' +
						",genre_ids = '" + genreIds + '\'' +
						",poster_path = '" + posterPath + '\'' +
						"}";
	}
}