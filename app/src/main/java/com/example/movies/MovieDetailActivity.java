package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

@SuppressWarnings("deprecation")
public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailActivity";
    private static final String EXTRA_MOVIE = "movie";

    private MovieDetailViewModel viewModel;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private RecyclerView recyclerViewTrailers;
    private RecyclerView recyclerViewReviews;
    private ImageView imageViewStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initViews();

        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        trailerAdapter = new TrailerAdapter();
        recyclerViewTrailers.setAdapter(trailerAdapter);
        recyclerViewTrailers.setLayoutManager(new LinearLayoutManager(this));

        reviewAdapter = new ReviewAdapter();
        recyclerViewReviews.setAdapter(reviewAdapter);
        recyclerViewReviews.setLayoutManager(new LinearLayoutManager(this));


        viewModel
                .getTrailers()
                .observe(this, trailers -> trailerAdapter.setTrailers(trailers));

        viewModel
                .getReviews()
                .observe(this, reviews -> reviewAdapter.setReviews(reviews));

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPoster);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        viewModel.loadTrailers(movie.getId());
        viewModel.loadReviews(movie.getId());

        Drawable starOff = ContextCompat.getDrawable(
                MovieDetailActivity.this,
                android.R.drawable.star_big_off
        );
        Drawable starOn = ContextCompat.getDrawable(
                MovieDetailActivity.this,
                android.R.drawable.star_big_on
        );

        viewModel.getFavMovie(movie.getId()).observe(this, favMovie -> {
                    if (favMovie == null) {

                        imageViewStar.setImageDrawable(starOff);
                        imageViewStar.setOnClickListener(v -> viewModel.insertMovie(movie));
                    } else {

                        imageViewStar.setImageDrawable(starOn);
                        imageViewStar.setOnClickListener(v -> viewModel.removeMovie(movie.getId()));
                    }
                }

        );

        trailerAdapter.setOnPlayTrailerClickListener((trailer) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(trailer.getUrl()));
            startActivity(intent);
        });

    }

    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        imageViewStar = findViewById(R.id.imageViewStar);
    }


    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

}