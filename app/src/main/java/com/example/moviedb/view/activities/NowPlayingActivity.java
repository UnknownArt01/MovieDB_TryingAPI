package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.moviedb.Model.NowPlaying;
import com.example.moviedb.R;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.viewmodel.MovieVM;

public class NowPlayingActivity extends AppCompatActivity {
    private RecyclerView rv_nowplaying;
    private MovieVM viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        rv_nowplaying = findViewById(R.id.rv_nowplaying);
        viewmodel = new ViewModelProvider(NowPlayingActivity.this).get(MovieVM.class);
        viewmodel.getNowPlaying();
        viewmodel.getResultGetNowPlaying().observe(NowPlayingActivity.this, showNowPlaying);

    }

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            rv_nowplaying.setLayoutManager(new LinearLayoutManager(NowPlayingActivity.this));
            NowPlayingAdapter adapter = new NowPlayingAdapter(NowPlayingActivity.this);
            adapter.setListPlaying(nowPlaying.getResults());
            rv_nowplaying.setAdapter(adapter);
        }
    };
}