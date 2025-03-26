package com.example.assignment_2_omdb.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment_2_omdb.model.MovieModel;
import com.example.assignment_2_omdb.utils.ApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieViewModel extends ViewModel {
    private final MutableLiveData<List<MovieModel>> movieData = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<List<MovieModel>> getMovieData(){
        return movieData;
    }

    public void getMovieDeets(String movieTitle){
        String apiKey = "10335575";
        // Changed api request from t= to s=
        String url = "https://www.omdbapi.com/?apikey="+apiKey+"&s="+movieTitle+"&plot=full";

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

                    JSONArray searchResults = jsonResponse.getJSONArray("Search");
                    List<MovieModel> movies = new ArrayList<>();

                    for (int i = 0; i < searchResults.length(); i++) {
                        JSONObject movieJson = searchResults.getJSONObject(i);

                        // Create a MovieModel object and populate it with the data
                        MovieModel movie = new MovieModel();
                        movie.setTitle(movieJson.getString("Title"));
                        movie.setMovieId(movieJson.getString("imdbID"));
                        movie.setYear(movieJson.getString("Year"));
                        movie.setPosterUrl(movieJson.getString("Poster"));
                        movie.setMovieType(movieJson.getString("Type"));

                        movies.add(movie);
                    }

                    // Update LiveData with the fetched movie data
                    movieData.postValue(movies);

                } catch (JSONException e) {
                    // Handle any JSON parsing errors
                    e.printStackTrace();
                    errorMessage.postValue("Error parsing movie data. Please try again.");
                }

            }

        });
    }
    public void getMovieDeets2(String movieTitle) {
        String apiKey = "10335575";
        // Updated API request to use t= for specific movie title
        String url = "https://www.omdbapi.com/?t=" + movieTitle + "&apikey=" + apiKey + "&plot=full";

        ApiClient.get(url, new Callback() {

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

                    // Create a MovieModel object and populate it with the data
                    MovieModel movie = new MovieModel();
                    movie.setTitle(jsonResponse.getString("Title"));
                    movie.setMovieId(jsonResponse.getString("imdbID"));
                    movie.setYear(jsonResponse.getString("Year"));
                    movie.setPosterUrl(jsonResponse.getString("Poster"));
                    movie.setMovieType(jsonResponse.getString("Type"));
                    movie.setPlot(jsonResponse.getString("Plot"));

                    // Wrap movie into a list and post to LiveData
                    List<MovieModel> movieList = new ArrayList<>();
                    movieList.add(movie);

                    // Update LiveData with the fetched movie details
                    movieData.postValue(movieList);

                } catch (JSONException e) {
                    // Handle any JSON parsing errors
                    e.printStackTrace();
                    errorMessage.postValue("Error parsing movie data. Please try again.");
                }
            }

        });
    }

}




