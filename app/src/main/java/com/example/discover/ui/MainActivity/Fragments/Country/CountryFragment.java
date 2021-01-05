package com.example.discover.ui.MainActivity.Fragments.Country;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.discover.R;
import com.example.discover.Tools.Fonts;
import com.example.discover.Tools.MySharedPreferences;
import com.example.discover.pojo.Location;
import com.example.discover.ui.UserCountryActivity.UserCountryActivity;
import com.google.android.material.tabs.TabLayout;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryFragment extends Fragment {
    View root;
    TabLayout tablayoutHomeFragment;
    ViewPager viewpagerHomeFragment;
    ImageView userSettings;
    TextView countryTV, discoverTV;
    EditText searchET;
    Fonts fonts;

    MySharedPreferences mySharedPreferences;
    public CountryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_country, container, false);
        fonts = new Fonts(getActivity());
        mySharedPreferences = new MySharedPreferences(getActivity());
        initViews();
        handlePager();
        return root;
    }

    private void initViews(){
        countryTV = root.findViewById(R.id.countryTV);
        discoverTV = root.findViewById(R.id.discoverTV);
        searchET = root.findViewById(R.id.searchET);
        userSettings = root.findViewById(R.id.userSettings);
        tablayoutHomeFragment = root.findViewById(R.id.tablayoutHomeFragment);
        viewpagerHomeFragment = root.findViewById(R.id.viewpagerHomeFragment);
        userSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getActivity(), UserCountryActivity.class));
            }
        });
        countryTV.setText(mySharedPreferences.getCountryNameFromSharedPref());

        countryTV.setTypeface(fonts.getMontserratAlternates());
        discoverTV.setTypeface(fonts.getAudioWide());
        searchET.setTypeface(fonts.getMontserratAlternates());
    }

    private void handlePager(){
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
        viewpagerHomeFragment.setAdapter(adapter);
        tablayoutHomeFragment.setupWithViewPager(viewpagerHomeFragment);
   }


//    private void initNEWS(){
//        countryTV.setText("EGYPT");
//        mySharedPreferences = new MySharedPreferences(getActivity());
//        mySharedPreferences.addToSharedPreferences("eg");
//    }


}
