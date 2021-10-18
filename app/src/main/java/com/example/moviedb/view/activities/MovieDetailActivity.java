package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView txt_details_title, txt_details_rating, txt_relase, txt_overview;
    private ImageView img_details_poster;
    private String movie_id, poster, title, relase_date, overview, rating ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initview();

        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        poster = intent.getStringExtra("poster");
        title = intent.getStringExtra("title");
        relase_date = intent.getStringExtra("relase_date");
        overview = intent.getStringExtra("overview");
        rating = intent.getStringExtra("rating");

        String img_path = Const.IMG_URL + poster;

        txt_details_title.setText(title);
        txt_details_rating.setText(rating);
        txt_relase.setText(relase_date);
        txt_overview.setText(overview);
        Glide.with(MovieDetailActivity.this).load(img_path).into(img_details_poster);

    }

    public void initview(){
        txt_details_title = findViewById(R.id.txt_details_title);
        txt_details_rating = findViewById(R.id.txt_details_rating);
        img_details_poster = findViewById(R.id.img_details_poster);
        txt_relase = findViewById(R.id.txt_relase);
        txt_overview = findViewById(R.id.txt_overview);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}