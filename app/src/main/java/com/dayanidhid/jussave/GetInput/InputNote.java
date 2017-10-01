package com.dayanidhid.jussave.GetInput;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dayanidhid.jussave.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by subash on 2/10/17.
 */

public class InputNote extends AppCompatActivity
{
    Button savebut;
    EditText about,contenttxt;
    String Title,Content,Src,CreatedAt,UpdatedAt,isFav;
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Jussave/" ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_note);
        about = (EditText)findViewById(R.id.about);
        contenttxt = (EditText)findViewById(R.id.contenttxt);
        savebut = (Button)findViewById(R.id.savebut);
        savebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Title = about.getText().toString();
                Content = contenttxt.getText().toString();
                Src = "";
                CreatedAt = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                UpdatedAt = CreatedAt;
                isFav = "";
                String line = null;



            }
        });

    }
}
