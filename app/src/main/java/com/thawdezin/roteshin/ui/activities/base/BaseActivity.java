package com.thawdezin.roteshin.ui.activities.base;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.thawdezin.roteshin.R;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Nullable
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * <pre>
     *     Setup toolbar
     * </pre>
     *
     * @param isChild Flag to indicate if this activity is called by another activity
     */
    protected void setupToolbar(boolean isChild) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        @Nullable final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(isChild);
        }
    }

    /**
     * Sets toolbar title
     *
     * @param text Toolbar Title
     */
    protected void setupToolbarText(String text) {
        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(text);
        }
    }

    /**
     * Sets toolbar title
     *
     * @param textStringRes Toolbar Title String Resource Identifier
     */
    protected void setupToolbarText(@StringRes int textStringRes) {
        setupToolbarText(getString(textStringRes));
    }

    /**
     * Update Toolbar color at runtime
     *
     * @param color Toolbar color resource ID
     */
    protected void setupToolbarBgColor(@ColorRes int color) {
        if (toolbar != null) {
            toolbar.setBackgroundColor(ContextCompat.getColor(this, color));
        }
    }


    protected void changeUpIndicatorColor(@ColorRes int color) {
        if (toolbar != null && toolbar.getNavigationIcon() != null) {
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
        }
    }



    /**
     * <pre>
     *     To be implemented by child class ,
     *     in order to provide layout xml ID for current activity
     * </pre>
     *
     * @return Layout Resource ID for current activity
     */
    @LayoutRes
    protected abstract int getLayoutResource();


}