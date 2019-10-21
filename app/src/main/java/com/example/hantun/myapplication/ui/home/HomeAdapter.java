package com.example.hantun.myapplication.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hantun.myapplication.AppConstants;
import com.example.hantun.myapplication.R;
import com.example.hantun.myapplication.data.remote.modelVO.MovieTypeVO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MovieTypeViewHolder> {

    private List<MovieTypeVO> movieTypeList;
    private Context mContext;

    public HomeAdapter(List<MovieTypeVO> movieList, Context context) {

        movieTypeList = movieList;
        mContext = context;
    }


    @NonNull
    @Override
    public MovieTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_type, parent, false);
        return new MovieTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTypeViewHolder holder, int position) {
        Picasso.get()
                .load(AppConstants.TMDB_IMAGE_URL + movieTypeList.get(position)
                        .getPosterPath()).into(holder.img_movie);

    }

    public void appendMovies(List<MovieTypeVO> moviesToAppend) {
        movieTypeList.addAll(moviesToAppend);
    }

    @Override
    public int getItemCount() {
        return movieTypeList.size();
    }

    public class MovieTypeViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_movie;

        public MovieTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            img_movie = itemView.findViewById(R.id.typeMovie);
        }
    }
}
