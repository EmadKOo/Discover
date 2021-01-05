package com.example.discover.ui.MainActivity.Fragments.SourcesFragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.discover.R;
import com.example.discover.Tools.Fonts;
import com.example.discover.Tools.MySharedPreferences;
import com.example.discover.pojo.Sources.Source;
import com.example.discover.ui.UserCountryActivity.UserCountryActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends Fragment {
    View rootView;
    ImageView userSettings;
    RecyclerView sourcesRecyclerView;
    TextView noResourcesTV, sourcesTV, worldWide;
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
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_sources, container, false);
        fonts = new Fonts(getContext());
        mySharedPreferences = new MySharedPreferences(getActivity());
        initViews();
        initViewModel();
        getAllSources();
        return rootView;
    }

    private void initViews(){
        worldWide = rootView.findViewById(R.id.worldWide);
        sourcesTV = rootView.findViewById(R.id.sourcesTV);
        userSettings = rootView.findViewById(R.id.userSettings);
        noResourcesTV = rootView.findViewById(R.id.noResourcesTV);
        userSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getActivity(), UserCountryActivity.class));
            }
        });

        worldWide.setTypeface(fonts.getMontserratAlternates());
        sourcesTV.setTypeface(fonts.getAudioWide());
        noResourcesTV.setTypeface(fonts.getMontserratAlternates());

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
                    noResourcesTV.setVisibility(View.VISIBLE);
                else
                    noResourcesTV.setVisibility(View.GONE);
            }
        });
    }
}