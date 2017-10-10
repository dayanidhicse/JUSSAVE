package com.dayanidhid.jussave;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale;

/**
 * Created by dayanidhi.d on 10/10/17.
 */

public class Permissions {
    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;

    public static String[] isPermissionCheck(Activity activity, String[] permissionsRequired){
        List<String> permissionsList = new ArrayList<String>();

        for(String permission : permissionsRequired){
            if(ActivityCompat.checkSelfPermission(activity, permission)!= PackageManager.PERMISSION_GRANTED){
                permissionsList.add(permission);
            }
        }
        String[] permissions = permissionsList.toArray(new String[permissionsList.size()]);
        return permissions;
    }

    public static Boolean isPermissionNever(Activity activity, String[] permissionsRequired){
        List<String> permissionsList = new ArrayList<String>();

        for(String permission : permissionsRequired) {
            if(shouldShowRequestPermissionRationale(activity, permission)){
                permissionsList.add(permission);
            }
        }

        String[] permissions = permissionsList.toArray(new String[permissionsList.size()]);
        return permissions.length > 0 ? false : true;
    }

    public static void getPermission(Activity activity, String[] permissionsRequired){
        String[] permission = isPermissionCheck(activity, permissionsRequired);
        if(permission.length > 0){
            ActivityCompat.requestPermissions(activity,permission,PERMISSION_CALLBACK_CONSTANT);
        }
    }

    public static void openSettings(Activity activity){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
    }

    public static void handlePermission(final Activity activity, final String[] permissionsRequired){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Location permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        getPermission(activity,permissionsRequired);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
    }

}
