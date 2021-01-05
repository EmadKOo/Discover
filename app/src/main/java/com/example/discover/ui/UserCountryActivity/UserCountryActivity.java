package com.example.discover.ui.UserCountryActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.discover.R;
import com.example.discover.pojo.Country;
import com.example.discover.ui.MainActivity.MainActivity;

import java.util.ArrayList;

public class UserCountryActivity extends AppCompatActivity implements ICountries{

    CountryAdapter countryAdapter;
    RecyclerView countriesRecyclerView;
    CountriesViewModel countriesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_country);
        initProvider();
        initCountries();
    }

    private void initViews(ArrayList<Country> countries){
        countriesRecyclerView = findViewById(R.id.countriesRecyclerView);
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countryAdapter = new CountryAdapter(countries, this, this);
        countriesRecyclerView.setAdapter(countryAdapter);
    }

    private void initProvider(){
        countriesViewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
    }

    private void initCountries(){
        countriesViewModel.getCountries();
        countriesViewModel.countriesListLiveData.observe(this, new Observer<ArrayList<Country>>() {
            @Override
            public void onChanged(ArrayList<Country> countries) {
                initViews(countries);
            }
        });
    }

    @Override
    public void closeActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
