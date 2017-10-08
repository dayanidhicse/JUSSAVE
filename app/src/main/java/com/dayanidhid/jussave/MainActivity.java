package com.dayanidhid.jussave;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

//import com.dayanidhid.jussave.Backup.drive;
import com.dayanidhid.jussave.Backup.drive;
import com.dayanidhid.jussave.notes.MyNotes;
import com.dayanidhid.jussave.Reminders.Remainders;
import com.dayanidhid.jussave.Secrets.Secrets;

public class MainActivity extends AppCompatActivity {
    private static final int CREDENTIALS_RESULT = 4342;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FrameLayout frameLayoutMyNote = (FrameLayout) findViewById(R.id.my_note);
        FrameLayout frameLayoutPassword = (FrameLayout) findViewById(R.id.password);
        FrameLayout frameLayoutRemainder = (FrameLayout) findViewById(R.id.remainder);

        frameLayoutMyNote.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyNotes.class);
                startActivity(intent);
            }
        });

        frameLayoutPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), Secrets.class);
//                startActivity(intent);
                checkCredentials();
            }
        });

        frameLayoutRemainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Remainders.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,drive.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    void checkCredentials()
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
