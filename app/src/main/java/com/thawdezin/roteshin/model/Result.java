package com.thawdezin.roteshin.model;

import androidx.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result {

	@SerializedName("title")
	private String title;

	@SerializedName("genre_ids")
	private List<Integer> genreIds;

	@SerializedName("poster_path")
	private String posterPath;


	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setGenreIds(List<Integer> genreIds){
		this.genreIds = genreIds;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
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
				"Result{" +
						",title = '" + title + '\'' +
						",genre_ids = '" + genreIds + '\'' +
						",poster_path = '" + posterPath + '\'' +
						"}";
	}
}