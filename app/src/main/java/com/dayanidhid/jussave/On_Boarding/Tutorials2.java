package com.dayanidhid.jussave.On_Boarding;

import android.graphics.Color;
import android.os.Bundle;

import com.dayanidhid.jussave.R;
import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

/**
 * Created by subash on 1/10/17.
 */

public class Tutorials2 extends TutorialActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new Step.Builder().setTitle("This is header")

                .setContent("This is content")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.ic_alarm) // int top drawable
                .setSummary("This is summary")
                .build());
    }
}
