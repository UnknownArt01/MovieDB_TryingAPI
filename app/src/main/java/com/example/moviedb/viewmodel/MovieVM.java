package com.example.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.Model.Movies;
import com.example.moviedb.Model.NowPlaying;
import com.example.moviedb.Model.Popular;
import com.example.moviedb.Model.Upcoming;
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
    public void getNowPlaying(int page){
        resultGetNowPlaying = repo.getNowPlayingData(page);
    }
    public LiveData<NowPlaying> getResultGetNowPlaying(){
        return resultGetNowPlaying;
    }
    //== end of VM get Now Playing

    //== Begin of VM get Upcoming
    private MutableLiveData<Upcoming> resultGetUpcoming = new MutableLiveData<>();
    public void getUpcoming(int page){
        resultGetUpcoming = repo.getUpcomingData(page);
    }
    public LiveData<Upcoming> getResultGetUpcoming(){
        return resultGetUpcoming;
    }
    //== end of VM get Upcoming

    //== Begin of VM get Popular
    private MutableLiveData<Popular> resultGetPopular = new MutableLiveData<>();
    public void getPopular(int page){
        resultGetPopular = repo.getPopularData(page);
    }
    public LiveData<Popular> getResultGetPopular(){
        return resultGetPopular;
    }
    //== end of VM get Popular



}
