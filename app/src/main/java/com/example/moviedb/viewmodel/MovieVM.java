package com.example.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.Model.Movies;
import com.example.moviedb.Model.NowPlaying;
import com.example.moviedb.repositories.MovieRepo;

public class MovieVM extends AndroidViewModel {

    private MovieRepo repo;

    public MovieVM(@NonNull Application application){
        super(application);
        repo = MovieRepo.getInstance();
    }

    //== Begin of VM get movie by ID
    private MutableLiveData<Movies> resultGetMovieByID = new MutableLiveData<>();
    public void getMovieById(String movieid){
        resultGetMovieByID = repo.getMovieData(movieid);
    }
    public LiveData<Movies> getResultGetMovieByID(){
        return resultGetMovieByID;
    }
    //== end of VM get movie by ID

    //== Begin of VM get Now Playing
    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repo.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultGetNowPlaying(){
        return resultGetNowPlaying;
    }
    //== end of VM get Now Playing
}
