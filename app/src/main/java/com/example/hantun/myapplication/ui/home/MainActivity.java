package com.example.hantun.myapplication.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.hantun.myapplication.R;
import com.example.hantun.myapplication.data.remote.modelVO.MovieTypeVO;
import com.example.hantun.myapplication.ui.base.BaseActivity;
import com.example.hantun.myapplication.viewmodels.MainActivityViewModel;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

public class MainActivity extends BaseActivity {
    private SwipyRefreshLayout homeSwipe;
    private RecyclerView mRecyclerView;
    private HomeAdapter mHomeAdapter;
    private MainActivityViewModel mMainActivityViewModel;
    private List<MovieTypeVO> movieList;
    private int page =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rvMovieType);
        homeSwipe = findViewById(R.id.home_refresh);
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.fetchMovieType(page);
        mMainActivityViewModel.getMovieType().observe(this, new Observer<List<MovieTypeVO>>() {
            @Override
            public void onChanged(List<MovieTypeVO> movieTypeVOS) {
                movieList = movieTypeVOS;
                initRecyclerView();
                mHomeAdapter.notifyDataSetChanged();

            }
        });

        homeSwipe.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                page++;
                mMainActivityViewModel.fetchMovieType(page);
                mHomeAdapter.appendMovies(mMainActivityViewModel.getMovieType().getValue());
                mHomeAdapter.notifyDataSetChanged();
                homeSwipe.setRefreshing(false);
            }
        });


    }

    private void initRecyclerView() {
        mHomeAdapter = new HomeAdapter(movieList , this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this , 3));
        mRecyclerView.setAdapter(mHomeAdapter);

    }
}
