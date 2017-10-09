package com.dayanidhid.jussave;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

//import com.dayanidhid.jussave.Backup.drive;
import com.dayanidhid.jussave.notes.MyNotes;
import com.dayanidhid.jussave.Reminders.Remainders;
import com.dayanidhid.jussave.Secrets.Secrets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    private static final int CREDENTIALS_RESULT = 4342;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.my_note)
    public void onMyNoteClick(View view){
        Intent intent = new Intent(getApplicationContext(), MyNotes.class);
        startActivity(intent);
    }

    @OnClick(R.id.password)
    public void onSecretsClick(View view) {
        checkCredentials();
    }

    @OnClick(R.id.remainder)
    public void onRemainderClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Remainders.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
   public void checkCredentials()
    {
        KeyguardManager keyguardManager = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
        Intent credentialsIntent = null;

        //credentialsIntent = keyguardManager.createConfirmDeviceCredentialIntent("Password required", "please enter your pattern to receive your token");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            credentialsIntent=keyguardManager.createConfirmDeviceCredentialIntent("Unlock Secrets","please enter the security confirmation");
        }
        if (credentialsIntent != null) {
            startActivityForResult(credentialsIntent, CREDENTIALS_RESULT);
        }
        else {

            //Use this part when the VERSION_CODE is less than API LVL 20
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CREDENTIALS_RESULT) {
            if(resultCode == RESULT_OK) {

                Toast.makeText(this, "Password Correct", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Secrets.class);
                startActivity(intent);
            }
            else {
                //Todo:handle cancel click

            }
        }
    }

}
