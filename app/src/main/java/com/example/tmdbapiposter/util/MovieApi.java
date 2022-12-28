package com.example.tmdbapiposter.util;

import com.example.tmdbapiposter.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    // Searching for movie
    // Sample request link for Lucy
    //https://api.themoviedb.org/3/search/movie?api_key=ce2216bc2dee5f31578745a53ad56da8&query=Lucy

    @GET
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );
}
