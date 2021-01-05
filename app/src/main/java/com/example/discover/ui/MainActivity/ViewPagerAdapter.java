package com.example.discover.ui.MainActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.discover.ui.MainActivity.Fragments.Country.CountryFragment;
import com.example.discover.ui.MainActivity.Fragments.SourcesFragment.SourcesFragment;
import com.example.discover.ui.MainActivity.Fragments.Trending.TrendingFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CountryFragment();
            case 1:
                return new SourcesFragment();
            case 2:
                return new TrendingFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}