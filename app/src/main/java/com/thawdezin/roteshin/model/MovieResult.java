package com.thawdezin.roteshin.model;

import androidx.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieResult{

	@SerializedName("results")
	private List<Result> results;

	public void setResults(List<Result> results){
		this.results = results;
	}

	public List<Result> getResults(){
		return results;
	}


	@NonNull
	@Override
	public String toString(){
		return
				"MovieResult{" +
						",results = '" + results + '\'' +
						"}";
	}
}