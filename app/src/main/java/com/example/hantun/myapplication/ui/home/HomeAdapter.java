package com.example.hantun.myapplication.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hantun.myapplication.AppConstants;
import com.example.hantun.myapplication.R;
import com.example.hantun.myapplication.data.remote.modelVO.MovieTypeVO;
import com.example.hantun.myapplication.ui.detail.MovieDetailActivity;
import com.example.hantun.myapplication.ui.detail.MovieDetailFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends PagedListAdapter<MovieTypeVO, HomeAdapter.MovieTypeViewHolder> {

    public List<MovieTypeVO> mMovieList;
    public Context mContext;
    public HomeAdapter(List<MovieTypeVO> movieList , Context context) {
        super(MOVIE_COMPARATOR);
        mMovieList = movieList;
        mContext = context;
    }

    private static final DiffUtil.ItemCallback<MovieTypeVO> MOVIE_COMPARATOR = new DiffUtil.ItemCallback<MovieTypeVO>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieTypeVO oldItem, @NonNull MovieTypeVO newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MovieTypeVO oldItem, @NonNull MovieTypeVO newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public MovieTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_type, parent, false);
        return new MovieTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTypeViewHolder holder, final int position) {
        holder.bind(getItem(position));

        holder.img_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                MovieDetailFragment detailsFragment = new MovieDetailFragment();
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.fragmentContainer, detailsFragment);
                transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();
            }
        });
    }

    public class MovieTypeViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_movie;

        public MovieTypeViewHolder(@NonNull final View itemView) {
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
