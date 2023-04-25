package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class FavMovieActivity extends AppCompatActivity {

    private FavMovieViewModel viewModel;
    private MoviesAdapter moviesAdapter;

    private RecyclerView recyclerViewFavMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_movie);

        initViews();

        moviesAdapter = new MoviesAdapter();
        recyclerViewFavMovies.setAdapter(moviesAdapter);
        recyclerViewFavMovies.setLayoutManager(new GridLayoutManager(this, 2));

        viewModel = new ViewModelProvider(this).get(FavMovieViewModel.class);
        viewModel.getMovies().observe(
                this,
                movies -> moviesAdapter.setMovies(movies)
        );
        moviesAdapter.setOnMovieClickListener(movie -> {
            Intent intent = MovieDetailActivity.newIntent(FavMovieActivity.this, movie);
            startActivity(intent);
        });

    }

    private void initViews() {
        recyclerViewFavMovies = findViewById(R.id.recyclerViewFavMovies);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FavMovieActivity.class);
    }

}