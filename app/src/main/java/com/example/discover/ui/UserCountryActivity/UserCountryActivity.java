package com.example.discover.ui.UserCountryActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.discover.R;
import com.example.discover.databinding.ActivityUserCountryBinding;
import com.example.discover.helper.Fonts;
import com.example.discover.pojo.Country;
import com.example.discover.ui.MainActivity.MainActivity;

import java.util.ArrayList;

public class UserCountryActivity extends AppCompatActivity implements ICountries{

    ActivityUserCountryBinding countryBinding;
    CountryAdapter countryAdapter;
    CountriesViewModel countriesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countryBinding = DataBindingUtil.setContentView(this,R.layout.activity_user_country);
        countryBinding.setFont(new Fonts(this));
        initProvider();
        initCountries();
    }

    private void initViews(ArrayList<Country> countries){
        countryBinding.countriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countryAdapter = new CountryAdapter(countries, this, this);
        countryBinding.countriesRecyclerView.setAdapter(countryAdapter);

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
