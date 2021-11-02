package com.example.moviedb.retrofit;

import com.example.moviedb.Model.Movies;
import com.example.moviedb.Model.NowPlaying;
import com.example.moviedb.Model.Popular;
import com.example.moviedb.Model.Upcoming;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("movie/{movie_id}")
    Call<Movies> getMovieById(
            @Path("movie_id") String movieid,
            @Query("api_key") String apikey
    );

    @GET("movie/now_playing")
    Call<NowPlaying> getNowPlaying(
            @Query("api_key") String apikey,
            @Query("page") int page
    );

    @GET("movie/upcoming")
    Call<Upcoming> getUpcoming(
            @Query("api_key") String apikey,
            @Query("page") int page
    );
    @GET("movie/popular")
    Call<Popular> getPopular(
            @Query("api_key") String apikey,
            @Query("page") int page
    );
}
