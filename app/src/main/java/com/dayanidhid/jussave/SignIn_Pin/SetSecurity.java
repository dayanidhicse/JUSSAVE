package com.dayanidhid.jussave.SignIn_Pin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dayanidhid.jussave.R;

/**
 * Created by subash on 1/10/17.
 */

public class SetSecurity extends AppCompatActivity
{
    Button createpin,systempin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_security);
        createpin = (Button)findViewById(R.id.createpin);
        systempin = (Button)findViewById(R.id.systempin);
        createpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create Pin For Application
                startActivity(new Intent(getApplicationContext(),SetPin.class));
                finish();
            }
        });
        systempin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {



            }
        });
    }
}
