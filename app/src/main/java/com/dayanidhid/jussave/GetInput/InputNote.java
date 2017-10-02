package com.dayanidhid.jussave.GetInput;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dayanidhid.jussave.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by subash on 2/10/17.
 */

public class InputNote extends AppCompatActivity
{
    Button savebut;
    EditText about,contenttxt;
    String Title,Data,Src,CreatedAt,UpdatedAt,isFav;
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Jussave/" ;
    File gpxfile;
    int count=0;
    ArrayList<JSONObject> Content = new ArrayList<JSONObject>();

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
//            try
//            {
//                Title = about.getText().toString();
//                Data = contenttxt.getText().toString();
//                Src = "";
//                CreatedAt = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
//                UpdatedAt = CreatedAt;
//                isFav = "";
//                JSONObject MyNotes  = new JSONObject();
//                JSONObject obj = new JSONObject();
//                JSONObject D = new JSONObject();
////                    JSONObject Content = new JSONObject();
//                D.put("Title",Title);
//                D.put("Data",Data);
//                D.put("Type","txt");
//                D.put("Src",Src);
//                D.put("CreatedAt",CreatedAt);
//                D.put("UpdatedAt",UpdatedAt);
//                D.put("isFav",isFav);
//
//                Content.add(D);
//                System.out.print(Content);
//                obj.put("Content",Content);
//                obj.put("Count",count);
//                System.out.println(obj);
//                System.out.println("!@#$%^");
//
//                MyNotes.put("Mynotes",obj);
//                System.out.println(obj);
//                System.out.println("!@#$%^");
////                    Toast.makeText(getApplicationContext(), MyNotes, Toast.LENGTH_SHORT).show();
//
//
////                    String line = Title+"#"+Data+"#"+Src+"#"+CreatedAt+"#"+UpdatedAt+"#"+isFav;
//                File root = new File(Environment.getExternalStorageDirectory(), "Jussave");
//                if (!root.exists()) {
//                    root.mkdirs();
//                    gpxfile = new File(root, "MyNotes.json");
//                    Toast.makeText(getApplicationContext(), "MKDIR", Toast.LENGTH_SHORT).show();
//                    gpxfile = new File(root, "MyNotes.txt");
//                    FileWriter writer = new FileWriter(gpxfile);
//                    System.out.println("BOWWW "+MyNotes.toString());
//                    writer.append((CharSequence) MyNotes);
//                    writer.flush();
//                    writer.close();
//                    Toast.makeText(getApplicationContext(), (CharSequence) MyNotes, Toast.LENGTH_SHORT).show();
//
//                }
//                System.out.println("ROOT "+root.exists());
//                if(root.exists())
//                {
//                    File file = new File(path, "MyNotes.json");
//                    int length = (int) file.length();
//                    byte[] bytes = new byte[length];
//                    FileInputStream in = new FileInputStream(file);
//                    try {
//                        in.read(bytes);
//                    } finally {
//                        in.close();
//                    }
//                    String contents = new String(bytes);
//                    JSONObject jsonObject = new JSONObject(contents);
//                    System.out.println(" CONTENTS "+contents);
//                }
//
//
//            }
//            catch(Exception e)
//            {
//                    System.out.println("EXCEPTION "+e);
//            }

            }
        });

    }
}
