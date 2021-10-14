package com.example.moviedb.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.Model.Movies;
import com.example.moviedb.Model.NowPlaying;
import com.example.moviedb.helper.Const;
import com.example.moviedb.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepo {

    private static  MovieRepo repo;
    private  MovieRepo(){}
    public static MovieRepo getInstance(){
        if (repo == null){
            repo = new MovieRepo();
        }
        return repo;
    }

    public MutableLiveData<Movies> getMovieData(String movieid){
        final  MutableLiveData<Movies> result = new MutableLiveData<>();

        ApiService.endPoint().getMovieById(movieid, Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<NowPlaying> getNowPlayingData(){
        final  MutableLiveData<NowPlaying> result = new MutableLiveData<>();

        ApiService.endPoint().getNowPlaying(Const.API_KEY).enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {

            }
        });

        return result;
    }




}
