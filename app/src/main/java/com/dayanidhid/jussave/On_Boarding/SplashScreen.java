package com.dayanidhid.jussave.On_Boarding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.dayanidhid.jussave.MainActivity;
import com.dayanidhid.jussave.R;

/**
 * Created by subash on 1/10/17.
 */

public class SplashScreen extends Activity
{
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedpreferences = getSharedPreferences("JUSSAVE_PREF", Context.MODE_PRIVATE);
        boolean islog1 = sharedpreferences.getBoolean("isLogin",false);
//        boolean islog2 = sharedpreferences.getBoolean("isSecuredLogin",false);

        if(islog1)
        {

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();

        }
        else
        {
            setContentView(R.layout.splash);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, Tutorials.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }





    }
}
