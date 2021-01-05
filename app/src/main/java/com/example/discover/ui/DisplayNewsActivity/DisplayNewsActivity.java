package com.example.discover.ui.DisplayNewsActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.comix.rounded.RoundedCornerImageView;
import com.example.discover.R;
import com.example.discover.Tools.Fonts;
import com.example.discover.pojo.WorldWide.Article;

public class DisplayNewsActivity extends AppCompatActivity {

    TextView newsTitleTV, newsSourceTV, firstLetterOfDiscription, restOfDiscriptionNewsTV, authorNewsTV;
    RoundedCornerImageView newsImage;
    Article currentArticle;
    ImageView backNews, newsLink;
    Fonts fonts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news);

        currentArticle = (Article) getIntent().getSerializableExtra("article");
        fonts = new Fonts(this);
        initViews();
        handleViews();
    }


    private void initViews(){
        newsTitleTV = findViewById(R.id.newsTitleTV);
        newsSourceTV = findViewById(R.id.newsSourceTV);
        firstLetterOfDiscription = findViewById(R.id.firstLetterOfDiscription);
        restOfDiscriptionNewsTV = findViewById(R.id.restOfDiscriptionNewsTV);
        authorNewsTV = findViewById(R.id.authorNewsTV);
        newsImage = findViewById(R.id.newsImage);
        newsLink = findViewById(R.id.newsLink);
        backNews = findViewById(R.id.backNews);

        newsTitleTV.setTypeface(fonts.getNewsCycle());
        newsSourceTV.setTypeface(fonts.getMontserratAlternates());
        firstLetterOfDiscription.setTypeface(fonts.getAudioWide());
        restOfDiscriptionNewsTV.setTypeface(fonts.getNewsCycle());
        authorNewsTV.setTypeface(fonts.getMontserratAlternates());
    }

    private void handleViews(){
        newsTitleTV.setText(currentArticle.getTitle());
        newsSourceTV.setText("#"+currentArticle.getSource().getName());
        try {
            firstLetterOfDiscription.setText(String.valueOf(currentArticle.getContent().charAt(0)).toUpperCase());
             restOfDiscriptionNewsTV.setText(currentArticle.getContent().substring(1));
        }catch (Exception e){
            firstLetterOfDiscription.setText("");
            restOfDiscriptionNewsTV.setText(currentArticle.getContent());
        }
        authorNewsTV.setText(currentArticle.getAuthor());
        Glide.with(this).load(currentArticle.getUrlToImage()).into(newsImage);

        backNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(currentArticle.getUrl())));
            }
        });
    }
}