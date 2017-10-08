package com.dayanidhid.jussave.On_Boarding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.dayanidhid.jussave.helper.DynamicFontDownload;
import com.dayanidhid.jussave.HomeActivity;
import com.dayanidhid.jussave.R;
import io.fabric.sdk.android.Fabric;

/**
 * Created by subash on 1/10/17.
 */

public class SplashScreen extends Activity {
    private static int SPLASH_TIME_OUT = 2000;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.splash);

        SharedPreferences sharedpreferences = getSharedPreferences("JUSSAVE_PREF", Context.MODE_PRIVATE);
        boolean isLog = sharedpreferences.getBoolean("isLogin",false);
//        if(isLog)
//        {
//
//            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//            finish();
//
//        }

//        SharedPreferences sharedpreferences = getSharedPreferences("JUSSAVE_PREF", Context.MODE_PRIVATE);
//        boolean islog1 = sharedpreferences.getBoolean("isLogin",false);
//
//        boolean islog2 = sharedpreferences.getBoolean("isSecuredLogin",false);
        textView = findViewById(R.id.appName);
        DynamicFontDownload.requestDownload("Open Sans",textView,this);
        if(isLog) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
                }
            }, SPLASH_TIME_OUT);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, EducationScreen.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }
}
