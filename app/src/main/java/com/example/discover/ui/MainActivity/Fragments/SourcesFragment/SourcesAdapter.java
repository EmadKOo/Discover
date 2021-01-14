package com.example.discover.ui.MainActivity.Fragments.SourcesFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discover.databinding.SourceItemBinding;
import com.example.discover.helper.CountryShortcut;
import com.example.discover.helper.Fonts;
import com.example.discover.pojo.Country;
import com.example.discover.pojo.sources.Source;
import com.example.discover.ui.SourceActivity.SourceActivity;

import java.util.ArrayList;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.MyViewHolder> {

    ArrayList<Source> sourceArrayList;
    Context context;
    CountryShortcut countryShortcut;
    ArrayList<Country> countries;
    Fonts fonts;
    Intent intent;
    Source currentSource;
    public SourcesAdapter(ArrayList<Source> categories, Context context) {
        this.sourceArrayList = categories;
        this.context = context;
        countryShortcut = new CountryShortcut();
        countries = countryShortcut.getCountries();
        fonts = new Fonts(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        SourceItemBinding sourceItemBinding = SourceItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new MyViewHolder(sourceItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, SourceActivity.class);
                intent.putExtra("type", 0);
                intent.putExtra("currentSource", sourceArrayList.get(i));
                context.startActivity(intent);
            }
        });
        currentSource = sourceArrayList.get(i);
        for (int x = 0; x < countries.size(); x++) {
            if (countries.get(x).getExtension().trim().equals(sourceArrayList.get(i).getCountry().trim().toLowerCase())){
                currentSource.setCountry(countries.get(x).getName());
            }
        }
        holder.sourceItemBinding.setSource(currentSource);
    }

    @Override
    public int getItemCount() {
        return sourceArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SourceItemBinding sourceItemBinding;

        public MyViewHolder(@NonNull final SourceItemBinding itemView) {
            super(itemView.getRoot());
            sourceItemBinding = itemView;
            sourceItemBinding.setFont(fonts);
    }
    }

}