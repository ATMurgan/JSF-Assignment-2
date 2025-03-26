package com.example.assignment_2_omdb.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment_2_omdb.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;

public class MovieDetailsActivity extends AppCompatActivity {

    TextView movieTitle, movieYear, movieDescription, movieId, movieType;
    ImageView moviePoster;

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_layout);

        movieTitle = findViewById(R.id.movie_title);
        movieType = findViewById(R.id.movie_type);
        movieId = findViewById(R.id.movie_id);
        movieYear = findViewById(R.id.movie_year);
        moviePoster = findViewById(R.id.movie_image);
        backButton = findViewById(R.id.back_button);
        movieDescription = findViewById(R.id.textView3);

        Intent intent = getIntent();
        String title = getIntent().getStringExtra("movieTitle");
        String type = "Type: " + getIntent().getStringExtra("movieType");
        String year = "Released/Aired: " + getIntent().getStringExtra("movieYear");
        String posterUrl = intent.getStringExtra("moviePosterUrl");
        String id = "Database ID: " +intent.getStringExtra("movieId");
        String plot = getIntent().getStringExtra("moviePlot");


        movieTitle.setText(title);
        movieType.setText(type);
        movieYear.setText(year);
        movieId.setText(id);
        movieDescription.setText(plot);

        Picasso.get().load(posterUrl).into(moviePoster);

        backButton.setOnClickListener(view -> {
            onBackPressed();
        });
        }









    }








