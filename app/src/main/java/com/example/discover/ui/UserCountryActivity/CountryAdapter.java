package com.example.discover.ui.UserCountryActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.discover.R;
import com.example.discover.Tools.MySharedPreferences;
import com.example.discover.pojo.Country;
import com.example.discover.ui.MainActivity.MainActivity;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    ArrayList<Country> countryArrayList;
    Context context;
    ICountries iCountries;
    int lastChecked =-1;
    MySharedPreferences mySharedPreferences;


    public CountryAdapter(ArrayList<Country> categories, Context context, ICountries iCountries) {
        this.countryArrayList = categories;
        this.context = context;
        this.iCountries = iCountries;
        mySharedPreferences = new MySharedPreferences(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.country_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        Glide.with(context).load(countryArrayList.get(i).getImgURL()).into(holder.countryImageView);
        holder.countryTitle.setText(countryArrayList.get(i).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySharedPreferences.addToSharedPreferences(countryArrayList.get(i).getExtension(), countryArrayList.get(i).getName());
                iCountries.closeActivity();
            }
        });

    }



    @Override
    public int getItemCount() {
        return countryArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox countryCheckBox;
        ImageView countryImageView;
        TextView countryTitle;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            countryCheckBox = itemView.findViewById(R.id.countryCheckBox);
            countryImageView = itemView.findViewById(R.id.countryImageView);
            countryTitle = itemView.findViewById(R.id.countryTitle);
        }
    }

}