package com.example.discover.Retrofit;

import com.example.discover.pojo.sources.Root;
import com.example.discover.pojo.articleroot.WorldWide;

import java.util.ArrayList;
import java.util.concurrent.Future;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsClient {
    private String BASEURL = "https://newsapi.org/";
    private ApiInterface apiInterface;
    private static NewsClient INSTANCE;
   // String API = "f6117a26eb70433b91c323fa3ac07e56";
    String API = "ecb2ced43dfd461897edd5a30a1f5449";

    public NewsClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static NewsClient getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new NewsClient();
        return INSTANCE;
    }

    public Call<WorldWide> getTrendingOfUserCountry(String country){
        return apiInterface.getTrendingUserCountry(country, API);
    }

    public Call<WorldWide> getCategoryInsideCountry(String country, String category){
        return apiInterface.getCategoryInsideCountry(country, category, API);
    }

    public Call<Root> getAllSources(String country){
        return apiInterface.getAllSources(country, API);
    }

    public Call<WorldWide> getAllNewsFromSpecificSource(String source){
        return apiInterface.getAllNewsFromSpecificSource(source, API);
    }

    public Call<WorldWide> getAllNewsOfOneCategory(String category){
        return apiInterface.getAllNewsOfOneCategory(category, API);
    }
}
