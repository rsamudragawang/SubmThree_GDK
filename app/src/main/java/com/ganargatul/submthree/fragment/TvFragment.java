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
import com.ganargatul.submthree.viewmodel.TvViewModel;

import java.util.ArrayList;

import static com.ganargatul.submthree.DetailActivity.EXTRA_DETAIL;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment implements MovieTvAdapter.OnItemClickListener {

    MovieTvAdapter mMovieAdapter;
    TvViewModel mTvViewModel;
    ProgressBar mProgressBar;


    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tv, container, false);

        mProgressBar = v.findViewById(R.id.progress_tv);
        mProgressBar.setVisibility(View.VISIBLE);

        mMovieAdapter = new MovieTvAdapter(getContext());
        mMovieAdapter.setOnItemClickListener(TvFragment.this);
        mMovieAdapter.notifyDataSetChanged();

        mTvViewModel = ViewModelProviders.of(getActivity()).get(TvViewModel.class);
        mTvViewModel.getTv().observe(TvFragment.this,getTv);
        mTvViewModel.getAPI();

        RecyclerView mRecyclerView = v.findViewById(R.id.tv_container);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mMovieAdapter);

        return v;
    }

    private Observer<ArrayList<MovieTvItems>> getTv = new Observer<ArrayList<MovieTvItems>>() {
        @Override
        public void onChanged(@Nullable ArrayList<MovieTvItems> movieItems) {
            if(movieItems != null){
                mMovieAdapter.setmMovieTvItems(movieItems);
                mProgressBar.setVisibility(View.GONE);
            }
        }
    };


    @Override
    public void onItemClick(int Position) {
        MovieTvItems movieTvItems = new MovieTvItems(mTvViewModel.listMovieItems.get(Position).getPhoto(),mTvViewModel.listMovieItems.get(Position).getTitle(),mTvViewModel.listMovieItems.get(Position).getOverview());

        Intent detail = new Intent(getContext(), DetailActivity.class);

        detail.putExtra(EXTRA_DETAIL,movieTvItems);
        startActivity(detail);
    }
}
