package com.example.discover.Retrofit;

import com.example.discover.pojo.Location;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationClient {
    private String BASEURL = "http://ip-api.com/";
    private ApiInterface apiInterface;
    private static LocationClient INSTANCE;

    Location location;

    public LocationClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static LocationClient getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new LocationClient();
        return INSTANCE;
    }

    public Call<Location> getLocation(){
        return apiInterface.getMyLocation();
    }
}