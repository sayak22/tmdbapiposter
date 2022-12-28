package com.example.tmdbapiposter.util;

import com.example.tmdbapiposter.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    // Searching for movie
    // Sample request link for Lucy
    //https://api.themoviedb.org/3/search/movie?api_key=ce2216bc2dee5f31578745a53ad56da8&query=Lucy

    // The three parameters which you were passing , one of them was actually path and remaining both were query params ( represented by ? and further &)
    // First one was page, which is not a query parameter but path actually which needed to be dynamic.
    // Check in bracket of @GET(to see how dynamic path is passed).

    @GET("/{page}/search/movie/")
    Call<MovieSearchResponse> searchMovie(
            @Path("page") String page,
            @Query("api_key") String key,
            @Query("query") String query

    );
}
