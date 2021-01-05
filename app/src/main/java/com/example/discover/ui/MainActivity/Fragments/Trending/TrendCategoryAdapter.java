package com.example.discover.ui.MainActivity.Fragments.Trending;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.comix.rounded.RoundedCornerImageView;
import com.example.discover.R;
import com.example.discover.Tools.Fonts;
import com.example.discover.pojo.Category;
import com.example.discover.ui.SourceActivity.SourceActivity;

import java.util.ArrayList;

public class TrendCategoryAdapter extends RecyclerView.Adapter<TrendCategoryAdapter.MyViewHolder> {

    ArrayList<Category> categories;
    Context context;
    Intent intent;
    Fonts fonts;


    public TrendCategoryAdapter(ArrayList<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
        fonts = new Fonts(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.trend_categories_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        Glide.with(context)
                .load(categories.get(i).getCategoryImage())
                .placeholder(R.drawable.loading)
                .into(holder.imgTrendCategoryItem);

        holder.titleTrendCategoryItem.setText(categories.get(i).getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, SourceActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("category", categories.get(i).getCategoryName().toLowerCase());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedCornerImageView imgTrendCategoryItem;
        TextView titleTrendCategoryItem;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            titleTrendCategoryItem = itemView.findViewById(R.id.titleTrendCategoryItem);
            imgTrendCategoryItem = itemView.findViewById(R.id.imgTrendCategoryItem);
            titleTrendCategoryItem.setTypeface(fonts.getMontserratAlternates());
        }
    }

}