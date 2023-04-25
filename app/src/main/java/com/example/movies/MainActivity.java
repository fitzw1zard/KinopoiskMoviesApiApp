package com.example.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private MoviesAdapter moviesAdapter;

    private RecyclerView recyclerViewMovies;
    private ProgressBar progressBarLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        moviesAdapter = new MoviesAdapter();
        recyclerViewMovies.setAdapter(moviesAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getMovies().observe(this, movies -> moviesAdapter.setMovies(movies));
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                progressBarLoading.setVisibility(View.VISIBLE);
            } else {
                progressBarLoading.setVisibility(View.GONE);
            }
        });
        moviesAdapter.setOnReachEndListener(() -> viewModel.loadMovies());
        moviesAdapter.setOnMovieClickListener((movie) -> {
            Intent intent = MovieDetailActivity.newIntent(MainActivity.this, movie);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemFav) {
            Intent intent = FavMovieActivity.newIntent(this);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        progressBarLoading = findViewById(R.id.progressBarLoading);
    }


}