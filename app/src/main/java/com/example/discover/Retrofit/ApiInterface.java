package com.example.discover.Retrofit;

import com.example.discover.pojo.Location;
import com.example.discover.pojo.Sources.Root;
import com.example.discover.pojo.WorldWide.WorldWide;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //http://ip-api.com/json
    @GET("json")
    Call<Location> getMyLocation();

    //https://newsapi.org/v2/top-headlines?country=us&apiKey=f6117a26eb70433b91c323fa3ac07e56
    @GET("v2/top-headlines")
    Call<WorldWide> getTrendingUserCountry(@Query("country") String country, @Query("apiKey") String apiKey);


    //https://newsapi.org/v2/top-headlines?country=de&category=business&apiKey=f6117a26eb70433b91c323fa3ac07e56
    @GET("v2/top-headlines")
    Call<WorldWide> getCategoryInsideCountry(@Query("country") String country, @Query("category") String category ,@Query("apiKey") String apiKey);

    // get all sources
    //https://newsapi.org/v2/sources?apiKey=f6117a26eb70433b91c323fa3ac07e56
    @GET("v2/sources")
    Call<Root> getAllSources(@Query("country") String country , @Query("apiKey") String apiKey);

    // get all news from specific source
    //https://newsapi.org/v2/everything?sources=abc-news&apiKey=f6117a26eb70433b91c323fa3ac07e56
    @GET("v2/everything")
    Call<WorldWide> getAllNewsFromSpecificSource(@Query("sources") String sources, @Query("apiKey") String apiKey);

    // get only one Category
    //https://newsapi.org/v2/everything?q=business&apiKey=f6117a26eb70433b91c323fa3ac07e56
    @GET("v2/everything")
    Call<WorldWide> getAllNewsOfOneCategory(@Query("q") String q, @Query("apiKey") String apiKey);
}
