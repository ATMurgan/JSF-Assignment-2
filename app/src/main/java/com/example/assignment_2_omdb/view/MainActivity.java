package com.example.assignment_2_omdb.view;

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

        // Initialize the ViewModel
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // Initialize Adapter and set it to the RecyclerView
        adapter = new MyAdapter(this, new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        // Observe movie data from ViewModel
        movieViewModel.getMovieData().observe(this, movies -> {
            // Update RecyclerView when movie data is available
            if (movies != null) {
                adapter.items.clear();
                adapter.items.addAll((Collection<? extends MovieModel>) movies); // try to change later
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

    }
}