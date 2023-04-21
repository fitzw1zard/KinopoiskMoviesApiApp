package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Rating implements Serializable {

    @SerializedName("kp")
    private double kpRating;

    private double decimalRating(double kpRating) {
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble(df.format(kpRating));
    }

    public Rating(double kpRating) {
        this.kpRating = kpRating;
    }

    public double getKpRating() {
        return decimalRating(kpRating);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kp='" + kpRating + '\'' +
                '}';
    }
}
