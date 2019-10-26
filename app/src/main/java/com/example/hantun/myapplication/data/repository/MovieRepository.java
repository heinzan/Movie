package com.example.hantun.myapplication.data.repository;

import com.example.hantun.myapplication.data.remote.api.MovieApiService;
import com.example.hantun.myapplication.data.remote.api.RetrofitBuilder;
import com.example.hantun.myapplication.data.remote.modelVO.MovieDetailVO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private MovieApiService movieApiService;
    private static MovieRepository repository;

    private MovieRepository(MovieApiService api) {
        movieApiService = api;
    }

    public static MovieRepository getInstance() {
        if (repository == null) {
            MovieApiService  movieApiService = RetrofitBuilder.buildService(MovieApiService.class);

            repository = new MovieRepository(movieApiService);
        }

        return repository;
    }
    public void getMovieDetail(int movieId){
        movieApiService.getMovieDetail(movieId).enqueue(new Callback<MovieDetailVO>() {
            @Override
            public void onResponse(Call<MovieDetailVO> call, Response<MovieDetailVO> response) {
                MovieDetailVO movieDetailVO = response.body();
            }

            @Override
            public void onFailure(Call<MovieDetailVO> call, Throwable t) {

            }
        });
    }


}
