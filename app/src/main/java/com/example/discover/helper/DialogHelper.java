package com.example.discover.helper;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.discover.R;
import com.example.discover.ui.SplashActivity.ISplash;


public class DialogHelper {
    Context context;
    ProgressDialog progressDialog;
    Fonts fonts;
    Intent intent;
    ISplash iSplash;

    public DialogHelper(Context context) {
        this.context = context;
        fonts = new Fonts(context);
    }

    public DialogHelper(Context context, ISplash iSplash) {
        this.context = context;
        this.iSplash = iSplash;
    }

    public void showProgressDialog(){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("please wait ....");
        progressDialog.show();
    }

    public void dialogInternet(){
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_internet);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);

        Button tryLater = dialog.findViewById(R.id.tryLater);
        Button retry = dialog.findViewById(R.id.retry);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSplash.noInternetAction(true);
            }
        });

        tryLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSplash.noInternetAction(false);
            }
        });


        dialog.show();
    }
    public void dismissDialog() {
        progressDialog.hide();
    }
}
