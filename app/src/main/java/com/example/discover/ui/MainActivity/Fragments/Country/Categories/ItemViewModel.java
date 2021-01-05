package com.example.discover.ui.MainActivity.Fragments.Country.Categories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.discover.Retrofit.NewsClient;
import com.example.discover.pojo.WorldWide.Article;
import com.example.discover.pojo.WorldWide.WorldWide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemViewModel extends ViewModel {
    MutableLiveData articlesLiveData = new MutableLiveData();
    ArrayList<Article> articleArrayList = new ArrayList<>();
    private static final String TAG = "ItemViewModel";


    void getArticles(String country, String category){
        NewsClient.getINSTANCE().getCategoryInsideCountry(country, category).enqueue(new Callback<WorldWide>() {
            @Override
            public void onResponse(Call<WorldWide> call, Response<WorldWide> response) {
                Log.d(TAG, "onResponse: " + response);
                articleArrayList = response.body().articles;
                articlesLiveData.setValue(articleArrayList);
            }

            @Override
            public void onFailure(Call<WorldWide> call, Throwable t) {

            }
        });
    }

}
