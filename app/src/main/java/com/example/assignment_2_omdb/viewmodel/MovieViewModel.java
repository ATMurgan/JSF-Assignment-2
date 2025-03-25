package com.example.assignment_2_omdb.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment_2_omdb.model.MovieModel;
import com.example.assignment_2_omdb.utils.ApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieViewModel extends ViewModel {
    private final MutableLiveData<MovieModel> movieData = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<MovieModel> getMovieData(){
        return movieData;
    }

    public void getMovieDeets(String movieTitle){
        String apiKey = "insert apikey here";
        String url = "https://www.omdbapi.com/?apikey="+apiKey+"&t="+movieTitle;

        ApiClient.get(url, new Callback(){

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("MovieViewModel", "Request failed: " + e.getMessage());
                errorMessage.postValue("Failed to fetch movie details. Please try again.");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e("ApiClient", "Request failed");
                    return;
                }
                String responseBody = response.body().string();
                try {
                    JSONObject jsonResponse = new JSONObject(responseBody);

                    // Check if the response contains an error
                    if (jsonResponse.has("Error")) {
                        errorMessage.postValue(jsonResponse.getString("Error"));
                        return;
                    }

                    // Parse data from the response
                    String title = jsonResponse.getString("Title");
                    String year = jsonResponse.getString("Year");
                    String posterUrl = jsonResponse.getString("Poster");
                    String studio = jsonResponse.getString("Studio");


                    String movieDescription = jsonResponse.optString("Plot", "No description available.");

                    // Create a MovieModel object and populate it with the data
                    MovieModel movie = new MovieModel();
                    movie.setTitle(title);
                    movie.setYear(year);
                    movie.setStudio(studio);
                    movie.setPosterUrl(posterUrl);
                    movie.setDescription(movieDescription);

                    movie.setDescription(movieDescription);

                    // Update LiveData with the fetched movie data
                    movieData.postValue(movie);

                } catch (JSONException e) {
                    // Handle any JSON parsing errors
                    e.printStackTrace();
                    errorMessage.postValue("Error parsing movie data. Please try again.");
                }

            }

        });
    }


}
