package com.example.discover.ui.MainActivity.Fragments.SourcesFragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.discover.R;
import com.example.discover.databinding.FragmentSourcesBinding;
import com.example.discover.helper.Fonts;
import com.example.discover.helper.MySharedPreferences;
import com.example.discover.pojo.sources.Source;
import com.example.discover.ui.UserCountryActivity.UserCountryActivity;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends Fragment {
    View rootView;
    FragmentSourcesBinding fragmentSourcesBinding;
    ImageView userSettings;
    RecyclerView sourcesRecyclerView;
    ArrayList<Source> sourceArrayList;
    SourcesAdapter adapter;
    Fonts fonts;
    SourceViewModel sourceViewModel;
    MySharedPreferences mySharedPreferences;
    public SourcesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSourcesBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sources, container,false);
        rootView =fragmentSourcesBinding.getRoot();
        fonts = new Fonts(getContext());
        mySharedPreferences = new MySharedPreferences(getActivity());
        fragmentSourcesBinding.setFonts(fonts);

        initViewModel();
        goToUserCountry();
        getAllSources();
        return rootView;
    }


    public void goToUserCountry(){
        fragmentSourcesBinding.userSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getActivity(), UserCountryActivity.class));
            }
        });
    }
    private void initRecyclerView(){
        sourcesRecyclerView = rootView.findViewById(R.id.sourcesRecyclerView);
        sourcesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SourcesAdapter(sourceArrayList, getActivity());
        sourcesRecyclerView.setAdapter(adapter);
    }

    private void initViewModel(){
        sourceViewModel = ViewModelProviders.of(getActivity()).get(SourceViewModel.class);
    }

    private void getAllSources(){
        sourceViewModel.getSources(mySharedPreferences.getCountryFromSharedPref());
        sourceViewModel.sourceLiveData.observe(getActivity(), new Observer<ArrayList<Source>>() {
            @Override
            public void onChanged(ArrayList<Source> sources) {
                sourceArrayList = sources;
                initRecyclerView();
                if (sourceArrayList.size()==0)
                    fragmentSourcesBinding.noResourcesTV.setVisibility(View.VISIBLE);
                else
                    fragmentSourcesBinding.noResourcesTV.setVisibility(View.GONE);
            }
        });
    }
}