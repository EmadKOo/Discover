package com.example.discover.ui.MainActivity.Fragments.Country.Categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discover.R;
import com.example.discover.databinding.FragmentHealthBinding;
import com.example.discover.helper.MySharedPreferences;
import com.example.discover.pojo.articleroot.Article;
import com.example.discover.ui.MainActivity.Fragments.Trending.TrendingCountryAdapter;

import java.util.ArrayList;

public class HealthFragment extends Fragment {

    FragmentHealthBinding healthBinding;
    ItemViewModel itemViewModel;
    ArrayList<Article> articleArrayList = new ArrayList<>();
    TrendingCountryAdapter trendingCountryAdapter;
    MySharedPreferences mySharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        healthBinding  = FragmentHealthBinding.inflate(inflater, container, false);
        mySharedPreferences = new MySharedPreferences(getActivity());
        initViewModel();
        initObserver();
        return healthBinding.getRoot();
    }

    void initRecyclerView(){
        healthBinding.recyclerItem.setLayoutManager(new LinearLayoutManager(getContext()));
        trendingCountryAdapter = new TrendingCountryAdapter(articleArrayList, getActivity());
        healthBinding.recyclerItem.setAdapter(trendingCountryAdapter);
    }

    void initViewModel(){
        itemViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);
    }

    void initObserver(){
        itemViewModel.getArticles(mySharedPreferences.getCountryFromSharedPref(),"health");
        itemViewModel.articlesLiveData.observe(getActivity(), new Observer() {
            @Override
            public void onChanged(Object o) {
                articleArrayList = (ArrayList<Article>) o;
                initRecyclerView();
            }
        });
    }

}
