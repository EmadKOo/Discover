package com.example.discover.ui.MainActivity.Fragments.Country;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.discover.R;
import com.example.discover.databinding.FragmentCountryBinding;
import com.example.discover.helper.Fonts;
import com.example.discover.helper.MySharedPreferences;
import com.example.discover.ui.UserCountryActivity.UserCountryActivity;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryFragment extends Fragment {
    Fonts fonts;
    FragmentCountryBinding fragmentCountryBinding;

    MySharedPreferences mySharedPreferences;
    public CountryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCountryBinding = FragmentCountryBinding.inflate(inflater,container,false);
        fonts = new Fonts(getActivity());
        mySharedPreferences = new MySharedPreferences(getActivity());
        fragmentCountryBinding.setFont(fonts);
        handlePager();
        handleAction();
        return fragmentCountryBinding.getRoot();
    }


    private void handlePager(){
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager());
        fragmentCountryBinding.viewpagerHomeFragment.setAdapter(adapter);
        fragmentCountryBinding.tablayoutHomeFragment.setupWithViewPager(fragmentCountryBinding.viewpagerHomeFragment);
   }

   private void handleAction(){
        fragmentCountryBinding.userSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserCountryActivity.class));
            }
        });


   }
}
