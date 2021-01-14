package com.example.discover.ui.MainActivity.Fragments.Trending;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.discover.R;
import com.example.discover.databinding.TopGrossingItemBinding;
import com.example.discover.helper.Fonts;
import com.example.discover.pojo.articleroot.Article;
import com.example.discover.ui.DisplayNewsActivity.DisplayNewsActivity;

import java.util.ArrayList;

public class TrendingCountryAdapter extends RecyclerView.Adapter<TrendingCountryAdapter.MyViewHolder> {

    ArrayList<Article> articleArrayList;
    Context context;
    Intent intent;
    Fonts fonts;


    public TrendingCountryAdapter(ArrayList<Article> categories, Context context) {
        this.articleArrayList = categories;
        this.context = context;
        fonts = new Fonts(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TopGrossingItemBinding topGrossingItemBinding = TopGrossingItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup, false);
        return new MyViewHolder(topGrossingItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        holder.itemBinding.setArticle(articleArrayList.get(i));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, DisplayNewsActivity.class);
                intent.putExtra("article", articleArrayList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TopGrossingItemBinding itemBinding;

        public MyViewHolder(@NonNull final TopGrossingItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
            itemBinding.setFonts(fonts);
        }
    }

}