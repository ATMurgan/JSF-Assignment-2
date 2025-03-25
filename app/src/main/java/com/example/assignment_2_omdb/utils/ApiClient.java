package com.example.assignment_2_omdb.utils;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiClient {

    private static final String BASE_URL = "http://omdbapi/";
    private static final String API_KEY = "insert api key here";
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static void SearchMovies(String query, Callback callback){
        HttpUrl url = HttpUrl.parse(BASE_URL)
                .newBuilder().addQueryParameter("s", query)
                .addQueryParameter("apikey", API_KEY).build();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(callback);
    }

    public static void getMovieDetails(String imdbId, Callback callback){
        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder().addQueryParameter("apikey",API_KEY).addQueryParameter("i",imdbId).build();

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(callback);
    }



}
