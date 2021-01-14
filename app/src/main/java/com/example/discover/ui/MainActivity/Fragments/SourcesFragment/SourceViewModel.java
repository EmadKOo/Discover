package com.example.discover.ui.MainActivity.Fragments.SourcesFragment;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.discover.Retrofit.NewsClient;
import com.example.discover.pojo.sources.Root;
import com.example.discover.pojo.sources.Source;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceViewModel extends ViewModel {
    MutableLiveData<ArrayList<Source>> sourceLiveData = new MutableLiveData();
    private static final String TAG = "SourceViewModel";
    void getSources(String country){
        NewsClient.getINSTANCE().getAllSources(country).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Log.d(TAG, "onResponse: "+ response.body());
                Log.d(TAG, "onResponse: "+ response);
                sourceLiveData.setValue(response.body().sources);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }
}
