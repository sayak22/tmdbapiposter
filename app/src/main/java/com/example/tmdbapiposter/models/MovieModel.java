package com.example.tmdbapiposter.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {

    private String poster_path;

    //constructor
    public MovieModel(String poster_path) {
        this.poster_path = poster_path;
    }

    //getters
    protected MovieModel(Parcel in) {
        poster_path = in.readString();
    }

    //parcelable methods
    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getPoster_path() {
        return poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(poster_path);
    }
}
