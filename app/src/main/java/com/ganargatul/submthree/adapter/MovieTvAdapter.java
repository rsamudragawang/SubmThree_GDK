package com.ganargatul.submthree.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ganargatul.submthree.MovieTvItems;
import com.ganargatul.submthree.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MovieTvAdapter extends RecyclerView.Adapter<MovieTvAdapter.MovieViewHolder> {

    ArrayList<MovieTvItems> mMovieTvItems = new ArrayList<>();
    Context mContext;
    OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int Position);
    }

    public void setOnItemClickListener(OnItemClickListener mListener){
        this.mListener = mListener;
    }

    public MovieTvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmMovieTvItems(ArrayList<MovieTvItems> movieTvItems) {
        mMovieTvItems.clear();
        this.mMovieTvItems.addAll(movieTvItems);
        notifyDataSetChanged();
    }

    public void addMovieTvItems(final MovieTvItems movieTvItems){
        mMovieTvItems.add(movieTvItems);
        notifyDataSetChanged();
    }
    public void cleardata(){
        mMovieTvItems.clear();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v = LayoutInflater.from(mContext).inflate(R.layout.movietv_items,viewGroup,false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        MovieTvItems movieTvItems = mMovieTvItems.get(i);
        Log.d("adapter", movieTvItems.getTitle());
        movieViewHolder.mTitle.setText(movieTvItems.getTitle());
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w500"+ movieTvItems.getPhoto()).into(movieViewHolder.mPoster);
    }

    @Override
    public int getItemCount() {
        return mMovieTvItems.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        CircleImageView mPoster;
        TextView mTitle;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mPoster = itemView.findViewById(R.id.image_items);
            mTitle = itemView.findViewById(R.id.title_items);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener !=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
