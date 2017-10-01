package com.dayanidhid.jussave;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;


/**
 * Created by subash on 1/10/17.
 */

public class Tutorials extends AppIntro
{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        addSlide(firstFragment);
//        addSlide(secondFragment);
//        addSlide(thirdFragment);
//        addSlide(fourthFragment);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance("Title 1", "Desc 1", R.drawable.ic_skip_white, Color.BLACK));
        addSlide(AppIntroFragment.newInstance("Title 2", "Desc 2", R.drawable.ic_skip_white, Color.BLACK));
        addSlide(AppIntroFragment.newInstance("Title 3", "Desc 3", R.drawable.ic_skip_white, Color.BLACK));
        addSlide(AppIntroFragment.newInstance("Title 4", "Desc 4", R.drawable.ic_skip_white, Color.BLACK));


        // OPTIONAL METHODS
        // Override bar/separator color.
//        setBarColor(Color.parseColor("#ffffff"));
//        setSeparatorColor(Color.parseColor("#020202"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
//        setVibrate(true);
//        setVibrateIntensity(30);


    }
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.n
        startActivity(new Intent(getApplicationContext(),Signin_Google.class));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        startActivity(new Intent(getApplicationContext(),Signin_Google.class));
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }


}
