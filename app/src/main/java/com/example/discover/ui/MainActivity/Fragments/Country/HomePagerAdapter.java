package com.example.discover.ui.MainActivity.Fragments.Country;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.discover.ui.MainActivity.Fragments.Country.Categories.BusinessFragment;
import com.example.discover.ui.MainActivity.Fragments.Country.Categories.EntertainmentFragment;
import com.example.discover.ui.MainActivity.Fragments.Country.Categories.HealthFragment;
import com.example.discover.ui.MainActivity.Fragments.Country.Categories.ScienceFragment;
import com.example.discover.ui.MainActivity.Fragments.Country.Categories.SportsFragment;
import com.example.discover.ui.MainActivity.Fragments.Country.Categories.TechnologyFragment;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

     public HomePagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BusinessFragment();
            case 1:
                return new EntertainmentFragment();
            case 2:
                return new HealthFragment();
            case 3:
                return new ScienceFragment();
            case 4:
                return new SportsFragment();
            case 5:
                return new TechnologyFragment();
        }
        return null;
    }
    //business entertainment general health science sports technology

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Business";
            case 1:
                return "Entertainment";
            case 2:
                return "Health";
            case 3:
                return "Science";
            case 4:
                return "Sports";
            case 5:
                return "Technology";
        }
        return null;
    }
    @Override
    public int getCount() {
        return 6;
    }
}