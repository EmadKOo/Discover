package com.example.discover.ui.MainActivity.Fragments.SourcesFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discover.R;
import com.example.discover.Tools.CountryShortcut;
import com.example.discover.Tools.Fonts;
import com.example.discover.pojo.Country;
import com.example.discover.pojo.Sources.Source;
import com.example.discover.ui.SourceActivity.SourceActivity;

import java.util.ArrayList;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.MyViewHolder> {

    ArrayList<Source> sourceArrayList;
    Context context;
    CountryShortcut countryShortcut;
    ArrayList<Country> countries;
    Fonts fonts;
    Intent intent;
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
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.source_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        holder.sourceName.setText(sourceArrayList.get(i).getName());
        holder.sourceCategory.setText(sourceArrayList.get(i).getCategory());
        holder.sourceDiscription.setText(sourceArrayList.get(i).getDescription());
        holder.sourceCountry.setText(sourceArrayList.get(i).getCountry());
        for (int x = 0; x < countries.size(); x++) {
            if (countries.get(x).getExtension().trim().equals(sourceArrayList.get(i).getCountry().trim().toLowerCase())){
                holder.sourceCountry.setText(countries.get(x).getName());
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, SourceActivity.class);
                intent.putExtra("type", 0);
                intent.putExtra("currentSource", sourceArrayList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sourceArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sourceName, sourceCountry, sourceCategory,  sourceDiscription;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            sourceName = itemView.findViewById(R.id.sourceName);
            sourceCountry = itemView.findViewById(R.id.sourceCountry);
            sourceCategory = itemView.findViewById(R.id.sourceCategory);
            sourceDiscription = itemView.findViewById(R.id.sourceDiscription);

            sourceName.setTypeface(fonts.getMontserratAlternates());
            sourceCategory.setTypeface(fonts.getAudioWide());
            sourceDiscription.setTypeface(fonts.getNewsCycle());
        }
    }

}