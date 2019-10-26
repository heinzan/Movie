package com.example.hantun.myapplication.ui.detail;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.hantun.myapplication.R;
import com.example.hantun.myapplication.ui.base.BaseActivity;

public class MovieDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
/*
            // Create a new Fragment to be placed in the activity layout
            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            movieDetailFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragmentsContainer' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, movieDetailFragment).commit();*/

        }
    }


