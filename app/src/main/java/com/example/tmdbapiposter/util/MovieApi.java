package com.example.tmdbapiposter.util;

import com.example.tmdbapiposter.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    // Searching for movie
    // Sample request link for Lucy movie
    //https://api.themoviedb.org/3/search/movie?api_key=ce2216bc2dee5f31578745a53ad56da8&query=Lucy


    @GET("/{page}/search/movie/")
    Call<MovieSearchResponse> searchMovie(
            @Path("page") String page,
            @Query("api_key") String key,
            @Query("query") String query

    );
}
