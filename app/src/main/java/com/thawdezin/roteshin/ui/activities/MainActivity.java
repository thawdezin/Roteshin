package com.thawdezin.roteshin.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.thawdezin.roteshin.R;
import com.thawdezin.roteshin.model.Genres;

import com.thawdezin.roteshin.model.Movie;
import com.thawdezin.roteshin.model.MovieResult;
import com.thawdezin.roteshin.rest.RestClient;
import com.thawdezin.roteshin.rest.RetrofitCallbackHelper;
import com.thawdezin.roteshin.ui.activities.base.BaseActivity;
import com.thawdezin.roteshin.ui.adapters.MovieRecyclerAdapter;
import com.thawdezin.roteshin.utils.InMemoryStore;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;

public final class MainActivity extends BaseActivity {

    private String TAG = "Main Activity";

    private NestedScrollView contentMain;
    private ConstraintLayout contentLoading;
    private ConstraintLayout contentError;
    private RecyclerView recyclerViewNowShowing;
    private RecyclerView recyclerViewPopular;
    private RecyclerView recyclerViewUpcoming;
    private MovieRecyclerAdapter recyclerAdapterNowShowing;
    private MovieRecyclerAdapter recyclerAdapterPopular;
    private MovieRecyclerAdapter recyclerAdapterUpcoming;
    private MaterialButton retryBtn;
    private MaterialTextView tvLoadingLabel;

    private MaterialTextView tvNowShowingCount;
    private MaterialTextView tvUpcomingCount;
    private MaterialTextView tvPopularCount;

    private MaterialButton btnSeeAllNowShowing;
    private MaterialButton btnSeeAllPopular;
    private MaterialButton btnSeeAllUpcoming;


    private String API_KEY = "afd84ed60249491a627b9fb517b38ae0";
    private String LANGUAGE = "en-US";
    private int PAGE = 1;

    private List<Movie> nowPlayingMovieList = new ArrayList<>();
    private List<Movie> popularMovieList = new ArrayList<>();
    private List<Movie> upcomingMovieList = new ArrayList<>();

    private boolean isLoadingShowing = false;

    private Runnable retryRunnable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(false);

        bindViews();
        recyclerViewPrepare();
        fetchAll();
        buttonClicks();

    }

    private void buttonClicks() {
        retryBtn.setOnClickListener(v -> {
            if (retryRunnable == null) {
                retryRunnable = this::fetchGenre;
            }
            retryRunnable.run();
        });
        btnSeeAllNowShowing.setOnClickListener(v->{});
        btnSeeAllPopular.setOnClickListener(v->{});
        btnSeeAllUpcoming.setOnClickListener(v->{});

    }

    private void fetchUpcoming() {

        if(!isLoadingShowing){
            viewLoading("Loading upcoming movies");
        }

        final Call<MovieResult> getNowPlaying = RestClient.getMovieEndPoint(getApplicationContext()).getUpcoming();

        RestClient.enqueue(this, getNowPlaying, new RetrofitCallbackHelper<MovieResult>() {

            @Override
            protected void onSuccess(@NonNull MovieResult data, int responseCode) {

                String totalMovies =  String.valueOf(data.getMovies().size());
                String totalMoviesString = totalMovies + " " + getResources().getString(R.string.movies);
                tvUpcomingCount.setText(totalMoviesString);
                upcomingMovieList = data.getMovies().subList(0,5);
                recyclerAdapterUpcoming.setMovieList(upcomingMovieList);

            }
            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                viewError();
            }
            @Override
            protected void onComplete() {
                viewMain();
            }
        });
    }

    private void fetchPopular() {
        if(!isLoadingShowing){
            viewLoading("Loading popular movies");
        }

        final Call<MovieResult> getPopular = RestClient.getMovieEndPoint(getApplicationContext()).getPopular();

        RestClient.enqueue(this, getPopular, new RetrofitCallbackHelper<MovieResult>() {

            @Override
            protected void onSuccess(@NonNull MovieResult data, int responseCode) {

                String totalMovies =  String.valueOf(data.getMovies().size());
                String totalMoviesString = totalMovies + " " + getResources().getString(R.string.movies);
                tvPopularCount.setText(totalMoviesString);
                popularMovieList = data.getMovies().subList(0,5);
                recyclerAdapterPopular.setMovieList(popularMovieList);

            }
            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                retryRunnable = MainActivity.this::fetchPopular;
                Log.e(TAG,"Fetch Popular Fail");
            }
            @Override
            protected void onComplete() {
                fetchUpcoming();
            }
        });
    }

    private void fetchNowShowing() {
        if(!isLoadingShowing){
            viewLoading("Loading now playing movies");
        }

        final Call<MovieResult> getNowPlaying = RestClient.getMovieEndPoint(getApplicationContext()).getNowPlaying();

        RestClient.enqueue(this, getNowPlaying, new RetrofitCallbackHelper<MovieResult>() {

            @Override
            protected void onSuccess(@NonNull MovieResult data, int responseCode) {

                String totalMovies =  String.valueOf(data.getMovies().size());
                String totalMoviesString = totalMovies + " " + getResources().getString(R.string.movies);
                tvNowShowingCount.setText(totalMoviesString);
                nowPlayingMovieList = data.getMovies().subList(0,5);
                recyclerAdapterNowShowing.setMovieList(nowPlayingMovieList);

            }
            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
                retryRunnable = MainActivity.this::fetchNowShowing;
                Log.e(TAG,"Fetch NowShowing Fail");
            }
            @Override
            protected void onComplete() {
                fetchPopular();
            }
        });

    }

    private void fetchGenre(){

        if(!isLoadingShowing){
            viewLoading("Loading Genres");
        }

        final Call<Genres> getGenres = RestClient.getMovieEndPoint(getApplicationContext()).getGenresList();

        RestClient.enqueue(this, getGenres, new RetrofitCallbackHelper<Genres>() {
            @Override
            protected void onSuccess(@NonNull Genres data, int responseCode) {
                InMemoryStore.getInstance().setGenresList(data);
            }

            @Override
            protected void onFailure(Throwable t, int responseCode, int resultCode) {
               retryRunnable = MainActivity.this::fetchGenre;
                Log.e(TAG,"Fetch Genres Fail");
            }

            @Override
            protected void onComplete() {
                fetchNowShowing();
            }
        });
    }

    private void recyclerViewPrepare() {
        recyclerAdapterNowShowing = new MovieRecyclerAdapter();
        recyclerAdapterPopular = new MovieRecyclerAdapter();
        recyclerAdapterUpcoming = new MovieRecyclerAdapter();

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

        tvNowShowingCount = findViewById(R.id.tvNowShowingCount);
        tvPopularCount = findViewById(R.id.tvPopularMovieCount);
        tvUpcomingCount = findViewById(R.id.tvUpcomingMovieCount);

        btnSeeAllNowShowing = findViewById(R.id.btnSeeAllNowShowing);
        btnSeeAllPopular = findViewById(R.id.btnSeeAllPopularMovie);
        btnSeeAllUpcoming = findViewById(R.id.btnSeeAllUpcomingMovie);

        retryBtn = findViewById(R.id.btnMainRetry);
        tvLoadingLabel = findViewById(R.id.tvLoading);

        viewLoading("Loading");
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

    private void viewLoading(String loadingLabel){
        tvLoadingLabel.setText(loadingLabel);
        contentLoading.setVisibility(View.VISIBLE);
        contentMain.setVisibility(View.GONE);
        contentError.setVisibility(View.GONE);
    }
    private void viewError(){
        isLoadingShowing = false;
        contentError.setVisibility(View.VISIBLE);
        contentLoading.setVisibility(View.GONE);
        contentMain.setVisibility(View.GONE);
    }
    private void viewMain(){
        contentMain.setVisibility(View.VISIBLE);
        contentLoading.setVisibility(View.GONE);
        contentError.setVisibility(View.GONE);
    }

    private void fetchAll(){
        Genres genres = InMemoryStore.getInstance().getGenresList();
        if(genres == null){
            fetchGenre();
        }else{
            if(nowPlayingMovieList.isEmpty()){
                fetchNowShowing();
            }else if(popularMovieList.isEmpty()){
                fetchPopular();
            }else{
                fetchUpcoming();
            }
        }
    }

}
