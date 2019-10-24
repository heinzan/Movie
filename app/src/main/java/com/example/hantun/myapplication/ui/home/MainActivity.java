package com.example.hantun.myapplication.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hantun.myapplication.R;
import com.example.hantun.myapplication.data.remote.modelVO.MovieTypeVO;
import com.example.hantun.myapplication.ui.base.BaseActivity;
import com.example.hantun.myapplication.viewmodels.MainActivityViewModel;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

public class MainActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private HomeAdapter mHomeAdapter;
    private GridLayoutManager glm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rvMovieType);
        glm = new GridLayoutManager(this, 2);
        mHomeAdapter = new HomeAdapter();
        mRecyclerView.setLayoutManager(glm);
        mRecyclerView.setHasFixedSize(true);
        MainActivityViewModel mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.movieTypeList.observe(this, new Observer<PagedList<MovieTypeVO>>() {
            @Override
            public void onChanged(PagedList<MovieTypeVO> movieTypeVOS) {
                mHomeAdapter.submitList(movieTypeVOS);
            }
        });

        mRecyclerView.setAdapter(mHomeAdapter);
    }
}
