package com.thawdezin.roteshin.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.thawdezin.roteshin.R;
import com.thawdezin.roteshin.model.Genres;

import com.thawdezin.roteshin.model.MovieResult;
import com.thawdezin.roteshin.model.Result;
import com.thawdezin.roteshin.rest.RestClient;
import com.thawdezin.roteshin.rest.RetrofitCallbackHelper;
import com.thawdezin.roteshin.ui.activities.base.BaseActivity;
import com.thawdezin.roteshin.ui.adapters.MovieRecyclerAdapter;
import com.thawdezin.roteshin.utils.InMemoryStore;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;


public class MainActivity extends BaseActivity {

    private String TAG = "Main Activity";

    NestedScrollView contentMain;
    ConstraintLayout contentLoading;
    ConstraintLayout contentError;

    RecyclerView recyclerViewNowShowing;
    RecyclerView recyclerViewPopular;
    RecyclerView recyclerViewUpcoming;

    MovieRecyclerAdapter recyclerAdapterNowShowing;
    MovieRecyclerAdapter recyclerAdapterPopular;
    MovieRecyclerAdapter recyclerAdapterUpcoming;

    MaterialTextView tvGenres;
    MaterialTextView tvItemTitle;
    ImageView ivItem;

    int vGone = View.GONE;
    int vVisible = View.VISIBLE;



    public static String API_KEY = "afd84ed60249491a627b9fb517b38ae0";
    public static String LANGUAGE = "en-US";
    public static int PAGE = 1;

    List<Result> movieList = new ArrayList<>();

    List<Result> nowPlayingMovieList = new ArrayList<>();
    List<Result> popularMovieList = new ArrayList<>();
    List<Result> upcomingMovieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(false);

        bindViews();
        recyclerViewPrepare();
        fetchAll();
        //fetchGenre();

    }

    private void fetchUpcoming() {

        final Call<MovieResult> getNowPlaying = RestClient.getMovieEndPoint().getUpcoming(API_KEY,LANGUAGE, PAGE);

        RestClient.enqueue(this, getNowPlaying, new RetrofitCallbackHelper<MovieResult>() {

            @Override
            protected void onSuccess(@NonNull MovieResult data, int responseCode) {

                upcomingMovieList = data.getResults();
                recyclerAdapterUpcoming.setMovieList(upcomingMovieList);

            }
            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                viewError();
            }
            @Override
            protected void onComplete() {
                //fetchAll();
                viewMain();
            }
        });
    }

    private void fetchPopular() {

        final Call<MovieResult> getPopular = RestClient.getMovieEndPoint().getPopular(API_KEY,LANGUAGE, PAGE);

        RestClient.enqueue(this, getPopular, new RetrofitCallbackHelper<MovieResult>() {

            @Override
            protected void onSuccess(@NonNull MovieResult data, int responseCode) {

                popularMovieList = data.getResults();
                recyclerAdapterPopular.setMovieList(popularMovieList);

            }
            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                viewError();
            }
            @Override
            protected void onComplete() {
                viewMain();
                //fetchAll();
            }
        });
    }

    private void fetchNowPlaying() {

        final Call<MovieResult> getNowPlaying = RestClient.getMovieEndPoint().getNowPlaying(API_KEY,LANGUAGE, PAGE);

        RestClient.enqueue(this, getNowPlaying, new RetrofitCallbackHelper<MovieResult>() {

            @Override
            protected void onSuccess(@NonNull MovieResult data, int responseCode) {

                nowPlayingMovieList = data.getResults();
                Log.e("Total Pages",String.valueOf(data.getTotalPages()));
                recyclerAdapterNowShowing.setMovieList(nowPlayingMovieList);

            }
            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                viewError();
            }
            @Override
            protected void onComplete() {
                viewMain();
                //fetchAll();
            }
        });

    }

    private void fetchGenre(){
        final Call<Genres> getGenres = RestClient.getGenreEndPoint().getGenresList(API_KEY,LANGUAGE, PAGE);

        RestClient.enqueue(this, getGenres, new RetrofitCallbackHelper<Genres>() {
            @Override
            protected void onSuccess(@NonNull Genres data, int responseCode) {
                InMemoryStore.getInstance().setGenresList(data);
            }

            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                t.printStackTrace();
            }

            @Override
            protected void onComplete() {

            }
        });
    }

    private void recyclerViewPrepare() {
//      LinearLayout linearLayout = new LinearLayout(this);
        recyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNowShowing.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerAdapterNowShowing = new MovieRecyclerAdapter(getApplicationContext(),movieList);
        recyclerAdapterPopular = new MovieRecyclerAdapter(getApplicationContext(),movieList);
        recyclerAdapterUpcoming = new MovieRecyclerAdapter(getApplicationContext(),movieList);

        recyclerViewNowShowing.setAdapter(recyclerAdapterNowShowing);
        recyclerViewPopular.setAdapter(recyclerAdapterPopular);
        recyclerViewUpcoming.setAdapter(recyclerAdapterUpcoming);

    }

    private void bindViews() {
        contentMain = findViewById(R.id.contentMain);
        contentLoading = findViewById(R.id.contentLoading);
        contentError = findViewById(R.id.contentError);

        recyclerViewNowShowing = findViewById(R.id.rvNowShowing);
        recyclerViewPopular = findViewById(R.id.rvPopularMovie);
        recyclerViewUpcoming = findViewById(R.id.rvUpcomingMovie);

        tvGenres = findViewById(R.id.tvMoiveItemGenre);
        tvItemTitle = findViewById(R.id.tvItemTitle);
        ivItem = findViewById(R.id.ivMovieItem);

        viewLoading();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.search_movie) {
            //
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void viewLoading(){
        contentLoading.setVisibility(vVisible);
        contentMain.setVisibility(vGone);
        contentError.setVisibility(vGone);
    }
    private void viewError(){
        contentError.setVisibility(vVisible);
        contentLoading.setVisibility(vGone);
        contentMain.setVisibility(vGone);
    }
    private void viewMain(){
        contentMain.setVisibility(vVisible);
        contentLoading.setVisibility(vGone);
        contentError.setVisibility(vGone);
    }

    private void finalStepForShowingUI() {
        viewMain();
    }

    private void fetchAll(){
        Genres genres = InMemoryStore.getInstance().getGenresList();
        if(genres == null){
            fetchGenre();
        }

        if(popularMovieList.isEmpty() && nowPlayingMovieList.isEmpty() && upcomingMovieList.isEmpty()){
            fetchNowPlaying();
            fetchPopular();
            fetchUpcoming();
        }else{
            finalStepForShowingUI();
        }
    }

}
