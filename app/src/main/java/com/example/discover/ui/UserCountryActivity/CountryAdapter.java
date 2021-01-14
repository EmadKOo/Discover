package com.example.discover.ui.UserCountryActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.discover.R;
import com.example.discover.databinding.CountryItemBinding;
import com.example.discover.helper.Fonts;
import com.example.discover.helper.MySharedPreferences;
import com.example.discover.pojo.Country;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    ArrayList<Country> countryArrayList;
    Context context;
    ICountries iCountries;
    MySharedPreferences mySharedPreferences;
    CountryItemBinding countryItemBinding;

    public CountryAdapter(ArrayList<Country> categories, Context context, ICountries iCountries) {
        this.countryArrayList = categories;
        this.context = context;
        this.iCountries = iCountries;
        mySharedPreferences = new MySharedPreferences(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        countryItemBinding = CountryItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        countryItemBinding.setFont(new Fonts(context));
        return new MyViewHolder(countryItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        countryItemBinding.setCountry(countryArrayList.get(i));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySharedPreferences.addToSharedPreferences(countryArrayList.get(i).getExtension(), countryArrayList.get(i).getName());
                iCountries.closeActivity();
            }
        });
    }
    @BindingAdapter("countryImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }
    @Override
    public int getItemCount() {
        return countryArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CountryItemBinding countryItemBinding;
        public MyViewHolder(@NonNull final CountryItemBinding itemView) {
            super(itemView.getRoot());
            countryItemBinding = itemView;

        }
    }

}