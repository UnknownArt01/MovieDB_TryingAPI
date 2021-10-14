package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.Model.Movies;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.viewmodel.MovieVM;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private MovieVM vm;
    private Button button_hit_main;
    private TextView txt_show_main;
    private TextInputLayout til_movie_id;
    private ImageView img_poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        click();


    }

    public void initView(){
        vm = new ViewModelProvider(MainActivity.this).get(MovieVM.class);

        button_hit_main = findViewById(R.id.button_hit_main);
        txt_show_main = findViewById(R.id.txt_show_main);
        til_movie_id = findViewById(R.id.til_movie_id);
        img_poster = findViewById(R.id.img_poster);
  }
    public void click(){
        button_hit_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = til_movie_id.getEditText().getText().toString().trim();
                if (id.isEmpty()){
                    til_movie_id.setError("Please Fill Movie ID Field");
                }else {
                    til_movie_id.setError(null);
                    vm.getMovieById(id);
                    vm.getResultGetMovieByID().observe(MainActivity.this, showResultMovie);

                }
            }
        });
    }
    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if (movies == null){
                txt_show_main.setText("Movie ID is not Available in Database");

            }else{
                String title = movies.getTitle();
                String img_path = Const.IMG_URL + movies.getPoster_path().toString();
                Glide.with(MainActivity.this).load(img_path).into(img_poster);
                txt_show_main.setText(title);
            }

        }
    };
}