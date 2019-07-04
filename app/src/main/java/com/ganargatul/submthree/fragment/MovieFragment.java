package com.ganargatul.submthree.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ganargatul.submthree.DetailActivity;
import com.ganargatul.submthree.MovieTvItems;
import com.ganargatul.submthree.R;
import com.ganargatul.submthree.adapter.MovieTvAdapter;
import com.ganargatul.submthree.viewmodel.MovieViewModel;

import java.util.ArrayList;

import static com.ganargatul.submthree.DetailActivity.EXTRA_DETAIL;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieTvAdapter.OnItemClickListener {
    MovieTvAdapter mMovieTvAdapter;
    MovieViewModel mMovieViewModel;
    ProgressBar mProgressBar;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_movie, container, false);

        mProgressBar = v.findViewById(R.id.progress_movie);
        mProgressBar.setVisibility(View.VISIBLE);

        mMovieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        mMovieViewModel.getMovie().observe(MovieFragment.this,getMovie);
        mMovieViewModel.getAPI();

        mMovieTvAdapter = new MovieTvAdapter(getContext());
        mMovieTvAdapter.setOnItemClickListener(MovieFragment.this);
        mMovieTvAdapter.notifyDataSetChanged();


        RecyclerView mRecyclerView = v.findViewById(R.id.movie_container);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mMovieTvAdapter);

        return v;
    }

    private Observer<ArrayList<MovieTvItems>> getMovie = new Observer<ArrayList<MovieTvItems>>() {
        @Override
        public void onChanged(@Nullable ArrayList<MovieTvItems> movieTvItems) {
            if(movieTvItems !=null){
                mMovieTvAdapter.setmMovieTvItems(movieTvItems);
                mProgressBar.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void onItemClick(int Position) {
        MovieTvItems movieTv_items = new MovieTvItems(mMovieViewModel.listMovieTvItems.get(Position).getPhoto(),mMovieViewModel.listMovieTvItems.get(Position).getTitle(),mMovieViewModel.listMovieTvItems.get(Position).getOverview());

        Intent detail = new Intent(getContext(), DetailActivity.class);

        detail.putExtra(EXTRA_DETAIL,movieTv_items);
        startActivity(detail);
    }
}
