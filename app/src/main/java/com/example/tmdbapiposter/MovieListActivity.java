package com.example.tmdbapiposter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tmdbapiposter.models.MovieModel;
import com.example.tmdbapiposter.request.Servicey;
import com.example.tmdbapiposter.response.MovieSearchResponse;
import com.example.tmdbapiposter.util.Credentials;
import com.example.tmdbapiposter.util.MovieApi;
import com.squareup.picasso.Picasso;

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
    ImageView img;
    String extname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= findViewById(R.id.button);
        name = findViewById(R.id.movieName);
        img = findViewById(R.id.image);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        extname = name.getText().toString();
                GetRetrofitResponse();
            }
        });

    }

    public void GetRetrofitResponse(){

        // Check changes in Manifest and res/xml/network_security_config files. These files were added just because API was not allowing my network, nothing to worry about !

        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieSearchResponse> responseCall= movieApi
                .searchMovie(
                        "3",
                        Credentials.API_KEY,
                        extname

                );
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code()==200){
//                    Toast.makeText(MovieListActivity.this, "the response :: " + response.body().toString(), Toast.LENGTH_SHORT).show();
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    if(movies.isEmpty()) {
                        Toast.makeText(MovieListActivity.this, "Error :: " + response.message(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String posterPath = movies.get(0).getPoster_path();

                    // Using
                    Picasso
                            .get()
                            .load("https://image.tmdb.org/t/p/w500/"+posterPath)
                            .into(img);

                }
                else{
                    try {
                        Toast.makeText(MovieListActivity.this, "Error :: " + response.message(), Toast.LENGTH_SHORT).show();
                        Log.v("Tag","Error" + response.message());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Toast.makeText(MovieListActivity.this, "Error :: " + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.e("ERROR" , t.getMessage().toString());

            }
        });
    }
}