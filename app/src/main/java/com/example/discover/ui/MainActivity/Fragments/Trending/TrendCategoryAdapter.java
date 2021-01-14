package com.example.discover.ui.MainActivity.Fragments.Trending;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.comix.rounded.RoundedCornerImageView;
import com.example.discover.R;
import com.example.discover.databinding.TrendCategoriesItemBinding;
import com.example.discover.helper.Fonts;
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
        TrendCategoriesItemBinding trendCategoriesItemBinding = TrendCategoriesItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup,false);
        return new MyViewHolder(trendCategoriesItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        holder.categoriesItemBinding.setCategory(categories.get(i));
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

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).placeholder(R.drawable.loading).into(view);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TrendCategoriesItemBinding categoriesItemBinding;
        public MyViewHolder(@NonNull final TrendCategoriesItemBinding itemView) {
            super(itemView.getRoot());
            categoriesItemBinding = itemView;
            categoriesItemBinding.titleTrendCategoryItem.setTypeface(fonts.getMontserratAlternates());
        }
    }

}