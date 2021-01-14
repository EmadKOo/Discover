package com.example.discover.ui.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.discover.R;
import com.example.discover.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding mainBinding;
    private ViewPagerAdapter mViewPagerAdapter;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainBinding.buttomNavigation.setOnNavigationItemSelectedListener(this);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mainBinding.viewPager.setAdapter(mViewPagerAdapter);

        mainBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mainBinding.buttomNavigation.getMenu().findItem(R.id.page_1).setChecked(true);
                        break;
                    case 1:
                        mainBinding.buttomNavigation.getMenu().findItem(R.id.page_2).setChecked(true);
                        break;
                    case 2:
                        mainBinding.buttomNavigation.getMenu().findItem(R.id.page_3).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.page_1:
                mainBinding.viewPager.setCurrentItem(0);
                break;
            case R.id.page_2:
                mainBinding.viewPager.setCurrentItem(1);
                break;
            case R.id.page_3:
                mainBinding.viewPager.setCurrentItem(2);
                break;
        }
        return true;
    }
}