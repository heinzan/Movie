package com.example.hantun.myapplication.data.remote;

import com.example.hantun.myapplication.data.remote.modelVO.MovieTypeVO;

import java.util.List;

public interface OnGetMoviesCallback {
    void onSuccess(int page ,List<MovieTypeVO> movies);

    void onError();
}
