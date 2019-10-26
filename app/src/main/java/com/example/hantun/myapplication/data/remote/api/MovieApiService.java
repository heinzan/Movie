package com.example.hantun.myapplication.data.remote.api;

import com.example.hantun.myapplication.data.remote.modelVO.MovieApiResponseVO;
import com.example.hantun.myapplication.data.remote.modelVO.MovieDetailVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {

    @GET("movie/{type}")
    Call<MovieApiResponseVO> getMoviesType(
            @Path("type") String type ,
            @Query("page") long page);

    @GET("movie/{movieId}")
    Call<MovieDetailVO> getMovieDetail(
      @Path("movieId") int movieId
    );
}
