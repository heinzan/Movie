package com.example.hantun.myapplication.data.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.hantun.myapplication.AppConstants;
import com.example.hantun.myapplication.data.remote.api.MovieApiService;
import com.example.hantun.myapplication.data.remote.api.RetrofitBuilder;
import com.example.hantun.myapplication.data.remote.modelVO.MovieApiResponseVO;
import com.example.hantun.myapplication.data.remote.modelVO.MovieTypeVO;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieTypeDataSource extends PageKeyedDataSource<Long , MovieTypeVO> {
    public static int PAGE_SIZE = 20;
    public static long FIRST_PAGE = 1;
    MovieApiService apiService = RetrofitBuilder.buildService(MovieApiService.class);
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, MovieTypeVO> callback) {
        Call<MovieApiResponseVO> call = apiService.getMoviesType(AppConstants.TYPE_POPULAR , FIRST_PAGE);
        call.enqueue(new Callback<MovieApiResponseVO>() {
            @Override
            public void onResponse(Call<MovieApiResponseVO> call, Response<MovieApiResponseVO> response) {

                MovieApiResponseVO movieApiResponseVO = response.body();
                if(movieApiResponseVO != null){
                    List<MovieTypeVO> movieTypeVOS = movieApiResponseVO.getResults();
                    callback.onResult(movieTypeVOS , FIRST_PAGE , FIRST_PAGE+1);
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponseVO> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, MovieTypeVO> callback) {
      /*  Call<MovieApiResponseVO> call = apiService.getMoviesType(AppConstants.TYPE_POPULAR ,params.key);
        call.enqueue(new Callback<MovieApiResponseVO>() {
            @Override
            public void onResponse(Call<MovieApiResponseVO> call, Response<MovieApiResponseVO> response) {
                MovieApiResponseVO movieApiResponseVO = response.body();
                if(movieApiResponseVO != null){
                    List<MovieTypeVO> movieTypeVOS = movieApiResponseVO.getResults();
                    long key = (params.key > 1) ? (params.key -1) : 0;

                    callback.onResult(movieTypeVOS , key);
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponseVO> call, Throwable t) {

            }
        });*/
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, MovieTypeVO> callback) {

        Call<MovieApiResponseVO> call = apiService.getMoviesType(AppConstants.TYPE_POPULAR , params.key);
        call.enqueue(new Callback<MovieApiResponseVO>() {
            @Override
            public void onResponse(Call<MovieApiResponseVO> call, Response<MovieApiResponseVO> response) {
                MovieApiResponseVO movieApiResponseVO = response.body();
                if(movieApiResponseVO !=null){
                    List<MovieTypeVO> movieTypeVOS = movieApiResponseVO.getResults();
                    callback.onResult(movieTypeVOS , params.key +1);
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponseVO> call, Throwable t) {

            }
        });
    }
}
