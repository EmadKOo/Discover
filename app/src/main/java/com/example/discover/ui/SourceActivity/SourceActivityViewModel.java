package com.example.discover.ui.SourceActivity;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.discover.Retrofit.NewsClient;
import com.example.discover.pojo.WorldWide.Article;
import com.example.discover.pojo.WorldWide.WorldWide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceActivityViewModel extends ViewModel {
    MutableLiveData<ArrayList<Article>> newsFromSourceLiveData = new MutableLiveData<>();
    MutableLiveData<ArrayList<Article>> newsOfOneCategory = new MutableLiveData<>();


    void getAllNewsFromSource(String source){
        NewsClient.getINSTANCE().getAllNewsFromSpecificSource(source).enqueue(new Callback<WorldWide>() {
            @Override
            public void onResponse(Call<WorldWide> call, Response<WorldWide> response) {
                newsFromSourceLiveData.setValue(response.body().articles);
            }

            @Override
            public void onFailure(Call<WorldWide> call, Throwable t) {

            }
        });
    }

    // getAllCategory
    void getAllNewsOfOneCategory(String category){
        NewsClient.getINSTANCE().getAllNewsOfOneCategory(category).enqueue(new Callback<WorldWide>() {
            @Override
            public void onResponse(Call<WorldWide> call, Response<WorldWide> response) {
                newsOfOneCategory.setValue(response.body().articles);
            }

            @Override
            public void onFailure(Call<WorldWide> call, Throwable t) {

            }
        });
    }

}
