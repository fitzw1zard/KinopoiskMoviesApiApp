package com.example.movies;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private final String TAG = "ReviewAdapter";

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);

        holder.textViewReviewerName.setText(review.getAuthor());
        try {
            holder.textViewReviewDate.setText(review.getDate());
        } catch (ParseException e) {
            Log.d(TAG, e.toString());
        }
        holder.textViewReview.setText(review.getText());

        String type = review.getType();
        int colorResID;

        if (type.equals("Позитивный")) {
            colorResID = android.R.color.holo_green_dark;
        } else if (type.equals("Негативный")) {
            colorResID = android.R.color.holo_red_light;
        } else {
            colorResID = android.R.color.holo_orange_light;
        }

        holder.cardViewReview.setCardBackgroundColor(ContextCompat
                .getColor(holder.itemView.getContext(), colorResID));

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewReviewerName;
        private final TextView textViewReviewDate;
        private final TextView textViewReview;
        private final CardView cardViewReview;


        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewReviewerName = itemView.findViewById(R.id.textViewReviewerName);
            textViewReviewDate = itemView.findViewById(R.id.textViewReviewDate);
            textViewReview = itemView.findViewById(R.id.textViewReview);
            cardViewReview = itemView.findViewById(R.id.cardViewReview);
        }
    }
}
