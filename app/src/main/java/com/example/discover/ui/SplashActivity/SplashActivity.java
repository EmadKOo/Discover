package com.example.discover.ui.SplashActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.discover.R;
import com.example.discover.databinding.ActivitySplashBinding;
import com.example.discover.helper.DialogHelper;
import com.example.discover.helper.Fonts;
import com.example.discover.helper.NetworkUtils;
import com.example.discover.ui.MainActivity.MainActivity;

public class SplashActivity extends AppCompatActivity implements ISplash{

    ActivitySplashBinding splashBinding;
    DialogHelper dialogHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        splashBinding.setFont(new Fonts(this));
        if (NetworkUtils.isNetworkConnected(this)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else {
            dialogHelper = new DialogHelper(this, this);
            dialogHelper.dialogInternet();
        }
    }

    @Override
    public void noInternetAction(boolean a) {
        if (a){
            startActivity(new Intent(SplashActivity.this, SplashActivity.class));
            finish();
        }else {
            finish();
        }
    }
}
