package com.example.assignment_2_omdb.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_2_omdb.ItemClickListener;
import com.example.assignment_2_omdb.MyAdapter;
import com.example.assignment_2_omdb.R;
import com.example.assignment_2_omdb.databinding.ActivityMainBinding;
import com.example.assignment_2_omdb.model.MovieModel;
import com.example.assignment_2_omdb.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    ActivityMainBinding binding;
    MovieViewModel movieViewModel;

    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);


        adapter = new MyAdapter(this, new ArrayList<>());
        adapter.setClickListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);


        movieViewModel.getMovieData().observe(this, movies -> {

            if (movies != null && !movies.isEmpty()) {
                adapter.items.clear();
                adapter.items.addAll(movies);
                adapter.notifyDataSetChanged();
            }
        });


        // Takes user input and sends to the API
        binding.searchButton.setOnClickListener(v -> {
            String movieTitle = binding.inputText.getText().toString().trim();
            if (!movieTitle.isEmpty()) {
                // Print message to screen to show that the user input works
                Toast.makeText(MainActivity.this, movieTitle, Toast.LENGTH_SHORT).show();
                // Call the ViewModel to get movie details based on the movie title
                movieViewModel.getMovieDeets(movieTitle);
            }
            else {
                Toast.makeText(MainActivity.this, "Please enter a movie title", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onClick(View v, int pos) {
        // Get the selected movie from the adapter
        MovieModel selectedMovie = adapter.items.get(pos);


        movieViewModel.getMovieDeets2(selectedMovie.getTitle());

        movieViewModel.getMovieData().observe(this, movies -> {
            if (movies != null && !movies.isEmpty()) {

                MovieModel updatedMovie = movies.get(0);


                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra("movieTitle", updatedMovie.getTitle());
                intent.putExtra("movieRating", updatedMovie.getRating());
                intent.putExtra("movieYear", updatedMovie.getYear());
                intent.putExtra("movieType", updatedMovie.getMovieType());
                intent.putExtra("moviePosterUrl", updatedMovie.getPosterUrl());
                intent.putExtra("movieId", updatedMovie.getMovieId());
                intent.putExtra("moviePlot", updatedMovie.getPlot());


                startActivity(intent);
            } else {
                Log.d("MovieDetails", "Movie data is null or empty.");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        String movieTitle = binding.inputText.getText().toString().trim();


        if (!movieTitle.isEmpty()) {
            movieViewModel.getMovieDeets(movieTitle);
        }
    }
}