package com.example.discover.ui.MainActivity.Fragments.Trending;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.discover.Retrofit.NewsClient;
import com.example.discover.pojo.Category;
import com.example.discover.pojo.articleroot.Article;
import com.example.discover.pojo.articleroot.WorldWide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingViewModel extends ViewModel {
    MutableLiveData categoriesLiveData = new MutableLiveData();
    MutableLiveData<ArrayList<Article>> trendingUponUserLiveData = new MutableLiveData();
    private ArrayList<Category> categories = new ArrayList<>();

    void getCategories(){
        categories.clear();
        categories.add(new Category("Business", "https://image.freepik.com/free-vector/flat-3d-isometric-realty-estate-financial-accounting-bookkeeping-business-concept_126523-1602.jpg"));
        categories.add(new Category("Entertainment", "https://image.freepik.com/free-vector/characters-people-holding-multimedia-icons-illustration_53876-35228.jpg"));
        categories.add(new Category("Health", "https://image.freepik.com/free-vector/illustration-happy-healthy-family_53876-37126.jpg"));
        categories.add(new Category("Science", "https://image.freepik.com/free-vector/flat-design-science-concept-with-microscope_23-2148527588.jpg"));
        categories.add(new Category("Sports", "https://image.freepik.com/free-vector/drawn-woman-meditating-home_23-2148809747.jpg"));
        categories.add(new Category("Technology", "https://global-uploads.webflow.com/5bcb5ee81fb2091a2ec550c7/5dedd7b85049677d981db611_5c65f4ae2f71d67d0ee9f032_hero-image.png"));

        categoriesLiveData.setValue(categories);
    }


    void getTrendingUponUser(String country){
        NewsClient.getINSTANCE().getTrendingOfUserCountry(country).enqueue(new Callback<WorldWide>() {
            @Override
            public void onResponse(Call<WorldWide> call, Response<WorldWide> response) {
                trendingUponUserLiveData.setValue(response.body().articles);
            }

            @Override
            public void onFailure(Call<WorldWide> call, Throwable t) {

            }
        });
    }
}
