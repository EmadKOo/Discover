package com.example.discover.ui.SourceActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.discover.R;
import com.example.discover.Tools.Fonts;
import com.example.discover.pojo.Category;
import com.example.discover.pojo.Sources.Source;
import com.example.discover.pojo.WorldWide.Article;
import com.example.discover.ui.MainActivity.Fragments.Trending.TrendingCountryAdapter;

import java.util.ArrayList;

public class SourceActivity extends AppCompatActivity {

    int type ; // if 0 user comes from sources fragment if 1 user comes from trending fragment
    
    TextView sourcesTV;
    ImageView goToSource, backSources;
    RecyclerView sourcesAcRecyclerView;
    TrendingCountryAdapter adapter;
    SourceActivityViewModel sourceActivityViewModel;
    ArrayList<Article> articleArrayList = new ArrayList<>();
    Source currentSource = new Source();
    Fonts fonts;
    String currentCategory;
    private static final String TAG = "SourceActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        fonts =new Fonts(this);
        initViews();
        type = getIntent().getIntExtra("type", 0);
        if (type==0){
            currentSource = (Source) getIntent().getSerializableExtra("currentSource");
            initViewsSources();
            initViewModel();
            initSources();
        }else if (type==1){
            currentCategory = getIntent().getStringExtra("category");
            initViewsTrend();
            initViewModel();
            initCategories();
        }
    }

    private void initViews(){
        sourcesTV = findViewById(R.id.sourcesTV);
        goToSource = findViewById(R.id.goToSource);
        backSources = findViewById(R.id.backSources);
        backSources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sourcesTV.setTypeface(fonts.getMontserratAlternates());
    }


    private void initViewsSources(){
        sourcesTV.setText("#"+ currentSource.getName());
        goToSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(currentSource.getUrl())));
            }
        });
    }

    private void initRecyclerView(){
        sourcesAcRecyclerView = findViewById(R.id.sourcesAcRecyclerView);
        sourcesAcRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrendingCountryAdapter(articleArrayList, this);
        sourcesAcRecyclerView.setAdapter(adapter);
    }

    private void initViewModel(){
        sourceActivityViewModel = ViewModelProviders.of(this).get(SourceActivityViewModel.class);
    }

    private void initSources(){
        sourceActivityViewModel.getAllNewsFromSource(currentSource.getId());
        sourceActivityViewModel.newsFromSourceLiveData.observe(this, new Observer<ArrayList<Article>>() {
            @Override
            public void onChanged(ArrayList<Article> articles) {
                articleArrayList = articles;
                Log.d(TAG, "onChanged: " + articles.size());
                initRecyclerView();
            }
        });
    }

    private void initViewsTrend(){
        goToSource.setVisibility(View.GONE);
        sourcesTV.setText("#"+ String.valueOf(currentCategory.charAt(0)).toUpperCase() + currentCategory.substring(1));
    }

    private void initCategories(){
        sourceActivityViewModel.getAllNewsOfOneCategory(currentCategory);
        sourceActivityViewModel.newsOfOneCategory.observe(this, new Observer<ArrayList<Article>>() {
            @Override
            public void onChanged(ArrayList<Article> articles) {
                articleArrayList = articles;
                initRecyclerView();
            }
        });
    }
}
