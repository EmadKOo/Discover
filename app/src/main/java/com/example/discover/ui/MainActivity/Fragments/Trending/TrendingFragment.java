package com.example.discover.ui.MainActivity.Fragments.Trending;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.discover.R;
import com.example.discover.Tools.Fonts;
import com.example.discover.Tools.MySharedPreferences;
import com.example.discover.pojo.Category;
import com.example.discover.pojo.WorldWide.Article;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingFragment extends Fragment {
    //views
    View root;
    TextView todayDate, discoverTV, countryTV, topFeedsTV, topGrossingTV;
    RecyclerView trendingCategoriesRecyclerView, trendingRecyclerView;

    Fonts fonts;
    TrendingViewModel trendingViewModel;

    ArrayList<Article> articleList = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();

    TrendingCountryAdapter trendingCountryAdapter;
    TrendCategoryAdapter trendCategoryAdapter;
    MySharedPreferences mySharedPreferences;
//business entertainment general health science sports technology
private static final String TAG = "TrendingFragment";
    public TrendingFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_trending, container, false);
        mySharedPreferences = new MySharedPreferences(getActivity());
        fonts = new Fonts(getActivity());
        initViews();
        initViewModel();
        initCategoriesObserver();
        initTrendingCountryObserver();
        return root;
    }
    private String getDateNow(){
        Date myDate = new Date();
        return new SimpleDateFormat("dd-MM").format(myDate);
    }

    private void initViews(){
        discoverTV = root.findViewById(R.id.discoverTV);
        countryTV = root.findViewById(R.id.countryTV);
        topFeedsTV = root.findViewById(R.id.topFeedsTV);
        topGrossingTV = root.findViewById(R.id.topGrossingTV);
        todayDate = root.findViewById(R.id.todayDate);
        todayDate.setText(getDateNow());

        discoverTV.setTypeface(fonts.getAudioWide());
        countryTV.setTypeface(fonts.getMontserratAlternates());
        topFeedsTV.setTypeface(fonts.getNewsCycle());
        topGrossingTV.setTypeface(fonts.getNewsCycle());
        todayDate.setTypeface(fonts.getAudioWide());
    }
    private void initViewModel(){
        trendingViewModel = ViewModelProviders.of(getActivity()).get(TrendingViewModel.class);
    }

    private void initCategoriesObserver(){
        trendingViewModel.getCategories();
        trendingViewModel.categoriesLiveData.observe(getActivity(), new Observer() {
            @Override
            public void onChanged(Object o) {
                categories = (ArrayList<Category>) o;
                initCategoriesRecyclerView();
            }
        });
    }

    private void initTrendingCountryObserver(){
        trendingViewModel.getTrendingUponUser(mySharedPreferences.getCountryFromSharedPref());
        trendingViewModel.trendingUponUserLiveData.observe(getActivity(), new Observer<ArrayList<Article>>() {
            @Override
            public void onChanged(ArrayList<Article> articles) {
                articleList = articles;
                initTrendingCountryRecyclerView();
            }
        });
    }

    private void initCategoriesRecyclerView(){
        trendingCategoriesRecyclerView = root.findViewById(R.id.trendingCategoriesRecyclerView);
        trendingCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        trendCategoryAdapter = new TrendCategoryAdapter(categories, getActivity());
        trendingCategoriesRecyclerView.setAdapter(trendCategoryAdapter);
    }

    private void initTrendingCountryRecyclerView(){
        trendingRecyclerView = root.findViewById(R.id.trendingRecyclerView);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        trendingCountryAdapter = new TrendingCountryAdapter(articleList, getActivity());
        trendingRecyclerView.setAdapter(trendingCountryAdapter);
    }
}
