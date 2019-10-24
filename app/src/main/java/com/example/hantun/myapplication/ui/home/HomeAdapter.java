package com.example.hantun.myapplication.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hantun.myapplication.AppConstants;
import com.example.hantun.myapplication.R;
import com.example.hantun.myapplication.data.remote.modelVO.MovieTypeVO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends PagedListAdapter<MovieTypeVO, HomeAdapter.MovieTypeViewHolder> {
    public HomeAdapter() {
        super(MOVIE_COMPARATOR);
    }

    private static final DiffUtil.ItemCallback<MovieTypeVO> MOVIE_COMPARATOR = new DiffUtil.ItemCallback<MovieTypeVO>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieTypeVO oldItem, @NonNull MovieTypeVO newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MovieTypeVO oldItem, @NonNull MovieTypeVO newItem) {
            return oldItem == newItem;
        }
    };

    @NonNull
    @Override
    public MovieTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_type, parent, false);
        return new MovieTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTypeViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    public class MovieTypeViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_movie;

        public MovieTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            img_movie = itemView.findViewById(R.id.typeMovie);
        }

        public void bind(MovieTypeVO item) {
            if (item.getPosterPath() != null) {
                Picasso.get()
                        .load(AppConstants.TMDB_IMAGE_URL + item.getPosterPath())
                        .into(img_movie);

            }
        }
    }
}
