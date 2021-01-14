package com.example.discover.ui.DisplayNewsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.comix.rounded.RoundedCornerImageView;
import com.example.discover.R;
import com.example.discover.databinding.ActivityDisplayNewsBinding;
import com.example.discover.helper.Fonts;
import com.example.discover.pojo.articleroot.Article;

public class DisplayNewsActivity extends AppCompatActivity {

    ActivityDisplayNewsBinding newsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsBinding = DataBindingUtil.setContentView(this, R.layout.activity_display_news);
        newsBinding.setArticle((Article) getIntent().getSerializableExtra("article"));
        newsBinding.setFont(new Fonts(this));
        handleViews();
    }




    private void handleViews(){
        newsBinding.backNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newsBinding.newsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(((Article) getIntent().getSerializableExtra("article")).getUrl())));
            }
        });
    }
}