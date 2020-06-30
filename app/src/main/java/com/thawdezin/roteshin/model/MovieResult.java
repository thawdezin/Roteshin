package com.thawdezin.roteshin.model;

import androidx.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public final class MovieResult{

	@SerializedName("results")
	private List<Movie> movies;

	public List<Movie> getMovies(){
		return movies;
	}

	@NonNull
	@Override
	public String toString(){
		return "MovieResult{" + ",movies = '" + movies + '\'' + "}";
	}
}