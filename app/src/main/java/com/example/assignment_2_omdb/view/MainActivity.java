package com.example.assignment_2_omdb.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_2_omdb.R;
import com.example.assignment_2_omdb.databinding.ActivityMainBinding;
import com.example.assignment_2_omdb.model.MovieModel;
import com.example.assignment_2_omdb.viewmodel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}