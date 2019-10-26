package com.example.hantun.myapplication.data.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.hantun.myapplication.data.remote.modelVO.MovieTypeVO;

public class MovieTypeDataSourceFactorty extends DataSource.Factory<Long , MovieTypeVO> {
    public MutableLiveData<MovieTypeDataSource> movieTypeDataSourceMutableLiveData = new MutableLiveData<>();
    @NonNull
    @Override
    public DataSource<Long, MovieTypeVO> create() {
        MovieTypeDataSource movieTypeDataSource = new MovieTypeDataSource() ;
        movieTypeDataSourceMutableLiveData.postValue(movieTypeDataSource);
        return movieTypeDataSource;
    }
}
