package com.example.discover.ui.MainActivity.Fragments.Trending;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.discover.R;
import com.example.discover.helper.Fonts;
import com.example.discover.helper.MySharedPreferences;
import com.example.discover.databinding.FragmentTrendingBinding;
import com.example.discover.pojo.Category;
import com.example.discover.pojo.articleroot.Article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingFragment extends Fragment {
    //views
    View root;

    TrendingViewModel trendingViewModel;
    Fonts fonts;
    ArrayList<Article> articleList = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();

    FragmentTrendingBinding trendingBinding;
    TrendingCountryAdapter trendingCountryAdapter;
    TrendCategoryAdapter trendCategoryAdapter;
    MySharedPreferences mySharedPreferences;
private static final String TAG = "TrendingFragment";
    public TrendingFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        trendingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_trending, container,false);
        root = trendingBinding.getRoot();
        mySharedPreferences = new MySharedPreferences(getActivity());
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
        trendingBinding.dateLayoutInclude.setCurrentDate(getDateNow());
        fonts = new Fonts(getActivity());
        trendingBinding.setFonts(fonts);
//        trendingBinding.dateLayoutInclude.todayDate.setText(getDateNow());
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
        trendingBinding.trendingCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        trendCategoryAdapter = new TrendCategoryAdapter(categories, getActivity());
        trendingBinding.trendingCategoriesRecyclerView.setAdapter(trendCategoryAdapter);
    }

    private void initTrendingCountryRecyclerView(){
        trendingBinding.trendingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        trendingCountryAdapter = new TrendingCountryAdapter(articleList, getActivity());
        trendingBinding.trendingRecyclerView.setAdapter(trendingCountryAdapter);
    }
}
