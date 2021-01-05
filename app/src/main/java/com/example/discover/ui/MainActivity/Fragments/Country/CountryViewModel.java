package com.example.discover.ui.MainActivity.Fragments.Country;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.discover.Retrofit.LocationClient;
import com.example.discover.pojo.Location;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryViewModel extends ViewModel {
    MutableLiveData<Location> locationMutableLiveData = new MutableLiveData();


    public void getLocation(){
        LocationClient.getINSTANCE().getLocation().enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                locationMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {

            }
        });
    }
}
