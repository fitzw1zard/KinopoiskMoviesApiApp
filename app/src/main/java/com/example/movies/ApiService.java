package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie?sortField=votes.kp&sortType=-1&limit=30&typeNumber=1&rating.kp=7-10")
    @Headers({"accept: application/json", "X-API-KEY: GK842BQ-WT0MHDW-P5HGC6J-S37D830"})
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie/{id}")
    @Headers({"accept: application/json", "X-API-KEY: GK842BQ-WT0MHDW-P5HGC6J-S37D830"})
    Single<TrailerResponse> loadTrailers(@Path("id") int id);

    @GET("review?page=1&limit=10'")
    @Headers({"accept: application/json", "X-API-KEY: GK842BQ-WT0MHDW-P5HGC6J-S37D830"})
    Single<ReviewResponse> loadReviews(@Query("movieId") int id);

}
