package com.ganargatul.submthree.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ganargatul.submthree.MovieTvItems;
import com.ganargatul.submthree.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TvViewModel extends AndroidViewModel {
    RequestQueue mRequestQueue;
    private MutableLiveData<ArrayList<MovieTvItems>> mMovieTvItems = new MutableLiveData<>();
    String url;
    public ArrayList<MovieTvItems> listMovieItems = new ArrayList<>();
    public TvViewModel(@NonNull Application application) {
        super(application);
        mRequestQueue = Volley.newRequestQueue(application);
        url = application.getResources().getString(R.string.api_tv);
    }
    public void getAPI() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    int length = jsonArray.length();
                    for(int i = 0;i<length;i++){
                        JSONObject result = jsonArray.getJSONObject(i);
                        String title = result.getString("name");
                        String photo = result.getString("poster_path");
                        String overview = result.getString("overview");
                        Log.d("title",title);
                        MovieTvItems tvItems =new MovieTvItems(photo,title,overview);

                        listMovieItems.add(tvItems);

                    }

                    mMovieTvItems.postValue(listMovieItems);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        mRequestQueue.add(request);
    }
    public LiveData<ArrayList<MovieTvItems>> getTv(){

        return mMovieTvItems;
    }
}
