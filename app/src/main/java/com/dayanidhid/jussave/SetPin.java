package com.dayanidhid.jussave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by subash on 1/10/17.
 */

public class SetPin extends AppCompatActivity
{
    EditText pin1,pin2;
    Button submitpin;
    String p1,p2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin);

        pin1 = (EditText)findViewById(R.id.pin1);
        pin2 = (EditText)findViewById(R.id.pin2);
        submitpin = (Button) findViewById(R.id.submitpin);
        submitpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1 = pin1.getText().toString();
                p2 = pin2.getText().toString();

                System.out.println("PIN! "+p1.length()+" "+p2.length());
                    SharedPreferences sharedpreferences = getSharedPreferences("JUSSAVE_PREF", Context.MODE_PRIVATE);

                if(p1.equals(p2)&&(p1.length()>0&&p2.length()>0))
                {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("PIN",p1);
                    editor.putBoolean("isSecuredLogin",true);
                    editor.putString("LoginMethod","PIN");
                    editor.commit();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                }
                else if(p1!=p2)
                {
                    Toast.makeText(getApplicationContext(),"Enter Proper PIN",Toast.LENGTH_SHORT).show();


                }

            }
        });
    }

}
