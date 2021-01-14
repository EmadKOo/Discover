package com.example.discover.ui.MainActivity.Fragments.Country.Categories;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.discover.R;
import com.example.discover.databinding.FragmentBusinessBinding;
import com.example.discover.helper.MySharedPreferences;
import com.example.discover.pojo.articleroot.Article;
import com.example.discover.ui.MainActivity.Fragments.Trending.TrendingCountryAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessFragment extends Fragment {

    FragmentBusinessBinding businessBinding;
    ItemViewModel itemViewModel;
    ArrayList<Article> articleArrayList = new ArrayList<>();
    TrendingCountryAdapter trendingCountryAdapter;
    MySharedPreferences mySharedPreferences;
    private static final String TAG = "BusinessFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        businessBinding = FragmentBusinessBinding.inflate(inflater, container, false);
        mySharedPreferences = new MySharedPreferences(getActivity());
        initViewModel();
        initObserver();
        return businessBinding.getRoot();
    }

    void initRecyclerView(){
        businessBinding.recyclerItem.setLayoutManager(new LinearLayoutManager(getContext()));
        trendingCountryAdapter = new TrendingCountryAdapter(articleArrayList, getActivity());
        businessBinding.recyclerItem.setAdapter(trendingCountryAdapter);
    }

    void initViewModel(){
        itemViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);
    }

    void initObserver(){
        Log.d(TAG, "initObserver: " + mySharedPreferences.getCountryFromSharedPref());
        itemViewModel.getArticles(mySharedPreferences.getCountryFromSharedPref(), "business");
        itemViewModel.articlesLiveData.observe(getActivity(), new Observer() {
            @Override
            public void onChanged(Object o) {
                articleArrayList = (ArrayList<Article>) o;
                initRecyclerView();
            }
        });
    }

}
