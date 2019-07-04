package com.ganargatul.submthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DETAIL="extra_detail";
    TextView title,overview;
    ImageView poster;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        progressBar = findViewById(R.id.progress_detail);
        progressBar.setVisibility(View.VISIBLE);


        title = findViewById(R.id.title_detail);
        overview = findViewById(R.id.desc_detail);
        poster = findViewById(R.id.image_detail);
        ShowDetail();

    }

    private void ShowDetail() {
        progressBar.setVisibility(View.GONE);
        MovieTvItems movieTvItems = getIntent().getParcelableExtra(EXTRA_DETAIL);
        title.setText(movieTvItems.getTitle());
        overview.setText(movieTvItems.getOverview());
        Picasso.with(this).load("https://image.tmdb.org/t/p/w500"+ movieTvItems.getPhoto()).into(poster);
    }
}
