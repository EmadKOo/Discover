package com.example.discover.ui.SplashActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.discover.R;
import com.example.discover.Tools.DialogHelper;
import com.example.discover.Tools.Fonts;
import com.example.discover.Tools.NetworkUtils;
import com.example.discover.ui.MainActivity.MainActivity;

public class SplashActivity extends AppCompatActivity implements ISplash{

    Fonts fonts;
    TextView updatesDiscoverTV, discoverTv;
    DialogHelper dialogHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        fonts = new Fonts(this);
        initViews();
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

    private void initViews(){
        updatesDiscoverTV = findViewById(R.id.updatesDiscoverTV);
        discoverTv = findViewById(R.id.discoverTV);

        updatesDiscoverTV.setTypeface(fonts.getAudioWide());
        discoverTv.setTypeface(fonts.getAudioWide());

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
