package com.example.tmdbapiposter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tmdbapiposter.models.MovieModel;
import com.example.tmdbapiposter.request.Servicey;
import com.example.tmdbapiposter.response.MovieSearchResponse;
import com.example.tmdbapiposter.util.Credentials;
import com.example.tmdbapiposter.util.MovieApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class MovieListActivity extends AppCompatActivity {

    Button btn;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= findViewById(R.id.button);
        name = findViewById(R.id.movieName);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRetrofitResponse();
            }
        });

    }

    public void GetRetrofitResponse(){
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieSearchResponse> responseCall= movieApi
                .searchMovie(
                        Credentials.API_KEY,
                        "Lucy",
                        "1"
                );
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code()==200){
                    Log.v("Tag","the response" + response.body().toString());

                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());

                    for(MovieModel movie: movies)
                        Log.v("Tag","the poster path" + movie.getPoster_path());

                }
                else{
                    try {
                        Log.v("Tag","Error" + response.errorBody().string());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }
}