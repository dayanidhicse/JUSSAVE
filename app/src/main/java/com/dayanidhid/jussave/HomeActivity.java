package com.dayanidhid.jussave;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    private static final int CREDENTIALS_RESULT = 4342;
    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    File file;

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getAllShownImagesPath(HomeActivity.this);
    }

//    public void loadImagefromGallery() {
//        // which image properties are we querying
//        String[] projection = new String[] {
//                MediaStore.Images.Media._ID,
//                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
//                MediaStore.Images.Media.DATE_TAKEN
//        };
//
//// content:// style URI for the "primary" external storage volume
//        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//
//// Make the query.
//        Cursor cur = managedQuery(images,
//                projection, // Which columns to return
//                null,       // Which rows to return (all rows)
//                null,       // Selection arguments (none)
//                null        // Ordering
//        );
//
//        Log.i("ListingImages"," query count=" + cur.getCount());
//
//        if (cur.moveToFirst()) {
//            String bucket;
//            String date;
//            String path;
//            int bucketColumn = cur.getColumnIndex(
//                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
//
//            int dateColumn = cur.getColumnIndex(
//                    MediaStore.Images.Media.DATE_TAKEN);
//
//            do {
//                // Get the field values
//                bucket = cur.getString(bucketColumn);
//                date = cur.getString(dateColumn);
//                // Do something with the values.
//                Log.i("ListingImages", " bucket=" + bucket
//                        + "  date_taken=" + date+"path = ");
//
//
//
//            } while (cur.moveToNext());
//
//        }
//
//    }

    public static ArrayList<String> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
//            ImageView imageView = (ImageView) activity.findViewById(R.id.imageView);
//            imageView.setImageBitmap(BitmapFactory.decodeFile(absolutePathOfImage));
            listOfAllImages.add(absolutePathOfImage);
        }
        return listOfAllImages;
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
