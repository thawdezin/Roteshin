package com.thawdezin.roteshin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Thaw De Zin on June 18, 2020
 */
//    public String getPosterThumbnailUrl(){
//        return "http://image.tmdb.org/t/p/w500"+getPosterPath();
//    }
//}

public class Movie {

    @SerializedName("results")
    @Expose
    private List<MovieItem> results = null;

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

//    @SerializedName("dates")
//    @Expose
//    private Dates dates;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public List<MovieItem> getResults() {
        return results;
    }

    public void setResults(List<MovieItem> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

//    public Dates getDates() {
//        return dates;
//    }
//
//    public void setDates(Dates dates) {
//        this.dates = dates;
//    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "MovieResult{" +
                "results=" + results +
                ", page=" + page +
                ", totalResults=" + totalResults +
//                ", dates=" + dates +
                ", totalPages=" + totalPages +
                '}';
    }
}