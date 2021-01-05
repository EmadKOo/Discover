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
import com.example.discover.pojo.WorldWide.Article;
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
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.top_grossing_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        Glide.with(context).load(articleArrayList.get(i).getUrlToImage()).into(holder.imageItemGrossingItem);
        holder.datePostedGrossingItem.setText(articleArrayList.get(i).getPublishedAt());
        holder.itemGrossingDesc.setText(articleArrayList.get(i).getDescription());
        holder.itemTitleGrossing.setText(articleArrayList.get(i).getTitle());
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitleGrossing, itemGrossingDesc, datePostedGrossingItem;
        RoundedCornerImageView imageItemGrossingItem;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageItemGrossingItem = itemView.findViewById(R.id.imageItemGrossingItem);
            itemTitleGrossing = itemView.findViewById(R.id.itemTitleGrossing);
            itemGrossingDesc = itemView.findViewById(R.id.itemGrossingDesc);
            datePostedGrossingItem = itemView.findViewById(R.id.datePostedGrossingItem);

            itemTitleGrossing.setTypeface(fonts.getNewsCycle());
            itemGrossingDesc.setTypeface(fonts.getNewsCycle());
            datePostedGrossingItem.setTypeface(fonts.getNewsCycle());
        }
    }

}