package com.dayanidhid.jussave.SignIn_Pin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dayanidhid.jussave.MainActivity;
import com.dayanidhid.jussave.R;

/**
 * Created by subash on 1/10/17.
 */

public class PIN_Login extends AppCompatActivity
{
    Button logbut;
    EditText PINnum;
    String Pin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_login);
        logbut = (Button)findViewById(R.id.logbut);
        PINnum = (EditText)findViewById(R.id.PINnum);
        final SharedPreferences sharedpreferences = getSharedPreferences("JUSSAVE_PREF", Context.MODE_PRIVATE);

        logbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pin = PINnum.getText().toString();
                if(Pin.equals(sharedpreferences.getString("PIN","")))
                {

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect PIN",Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}
