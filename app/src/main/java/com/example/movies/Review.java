package com.example.movies;

import static java.text.DateFormat.getDateInstance;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Review implements Serializable {

    @SerializedName("type")
    private String type;
    @SerializedName("review")
    private String text;
    @SerializedName("date")
    private String date;
    @SerializedName("author")
    private String author;

    public Review(String type, String text, String date, String author) {
        this.type = type;
        this.text = text;
        this.date = date;
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getDate() throws ParseException {

        SimpleDateFormat inputFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        Date inputDate = inputFormat.parse(date);

        SimpleDateFormat outputFormat =
                new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        return outputFormat.format(inputDate);

    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Review{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
