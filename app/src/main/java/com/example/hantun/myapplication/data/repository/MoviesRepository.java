package com.example.hantun.myapplication.data.repository;

import android.util.Log;

import com.example.hantun.myapplication.AppConstants;
import com.example.hantun.myapplication.data.remote.OnGetMoviesCallback;
import com.example.hantun.myapplication.data.remote.api.MovieApiService;
import com.example.hantun.myapplication.data.remote.interceptor.RequestInterceptor;
import com.example.hantun.myapplication.data.remote.modelVO.MovieApiResponseVO;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {
    private static MoviesRepository repository;
    private MovieApiService api;

    private MoviesRepository(MovieApiService api) {
        this.api = api;
    }
    public static MoviesRepository getInstance() {
        if (repository == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).
                    addNetworkInterceptor(new RequestInterceptor()).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository = new MoviesRepository(retrofit.create(MovieApiService.class));
        }

        return repository;
    }

    public void getMovies(int page , final OnGetMoviesCallback callback){
        api.getMoviesType(AppConstants.TYPE_POPULAR , page).enqueue(new Callback<MovieApiResponseVO>() {
            @Override
            public void onResponse(Call<MovieApiResponseVO> call, Response<MovieApiResponseVO> response) {
                if (response.isSuccessful()) {
                    MovieApiResponseVO moviesResponse = response.body();
                    if (moviesResponse != null && moviesResponse.getResults() != null) {
                        callback.onSuccess(moviesResponse.getPage() , moviesResponse.getResults());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponseVO> call, Throwable t) {
                Log.d("Failure" , ""+t.getMessage()) ;
            }
        });
    }
}
