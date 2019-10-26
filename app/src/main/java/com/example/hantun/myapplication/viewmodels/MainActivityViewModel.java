package com.example.hantun.myapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.hantun.myapplication.data.paging.MovieTypeDataSource;
import com.example.hantun.myapplication.data.paging.MovieTypeDataSourceFactorty;
import com.example.hantun.myapplication.data.remote.modelVO.MovieTypeVO;

public class MainActivityViewModel extends ViewModel {
    public LiveData<PagedList<MovieTypeVO>> movieTypeList ;
    private MutableLiveData<MovieTypeDataSource> movieTypeDataSource = new MutableLiveData<>();

    public MainActivityViewModel() {
        init();
    }
    private void init(){
        MovieTypeDataSourceFactorty movieTypeDataSourceFactorty = new MovieTypeDataSourceFactorty();
        movieTypeDataSource = movieTypeDataSourceFactorty.movieTypeDataSourceMutableLiveData;

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(MovieTypeDataSource.PAGE_SIZE)
                .build();

        movieTypeList = new LivePagedListBuilder<>(movieTypeDataSourceFactorty , config).build();

    }





}
