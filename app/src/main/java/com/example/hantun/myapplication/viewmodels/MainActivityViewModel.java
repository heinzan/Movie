package com.example.hantun.myapplication.viewmodels;

import android.icu.util.LocaleData;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hantun.myapplication.data.remote.OnGetMoviesCallback;
import com.example.hantun.myapplication.data.remote.modelVO.MovieApiResponseVO;
import com.example.hantun.myapplication.data.remote.modelVO.MovieTypeVO;
import com.example.hantun.myapplication.data.repository.MoviesRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<MovieTypeVO>> movieTypeList = new MutableLiveData<>();
    private MoviesRepository moviesRepository;

    public void fetchMovieType(int page){
        moviesRepository = MoviesRepository.getInstance();
        moviesRepository.getMovies(page , new OnGetMoviesCallback() {
            @Override
            public void onSuccess(int page, List<MovieTypeVO> movies) {
                movieTypeList.setValue(movies);
            }

            @Override
            public void onError() {
                Log.d("error" , "error");
            }
        });
    }
    public MutableLiveData<List<MovieTypeVO>> getMovieType(){
        return movieTypeList;

    }




}
