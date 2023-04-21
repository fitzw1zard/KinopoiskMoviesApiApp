package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TrailerResponse implements Serializable {

    @SerializedName("videos")
    private TrailersList trailersLists;



    public TrailerResponse(TrailersList trailersLists) {
        this.trailersLists = trailersLists;
    }

    public TrailersList getTrailersLists() {
        return trailersLists;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "videos=" + trailersLists +
                '}';
    }
}
