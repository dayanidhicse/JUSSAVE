package com.dayanidhid.jussave;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
                Utils.setValue(permission,"false");
            }else{
                Utils.setValue(permission,"true");
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
                Utils.setValue(permission,"false");
            }else{
                Utils.setValue(permission,"true");
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

    public static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int) (dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static void handlePermission(final Activity activity, final String[] permissionsRequired){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.permission_dialog, null);
        LinearLayout linearLayout = dialogView.findViewById(R.id.imageSegment);
        addImage(dialogView,linearLayout,"FOLDER");
        addImage(dialogView,linearLayout,"PLUS");
        addImage(dialogView,linearLayout,"CAMERA");
        builder.setView(dialogView);
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                getPermission(activity,permissionsRequired);
            }
        });
        builder.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public static void addImage(View dialogView, LinearLayout linearLayout, String type){
        ImageView imgPlus = new ImageView(dialogView.getContext());
        imgPlus.setLayoutParams(new DrawerLayout.LayoutParams(convertDpToPixel(54,dialogView.getContext()), convertDpToPixel(54,dialogView.getContext())));
        imgPlus.setPadding(convertDpToPixel(0,dialogView.getContext()),convertDpToPixel(0,dialogView.getContext()),convertDpToPixel(16,dialogView.getContext()),convertDpToPixel(0,dialogView.getContext()));
        if(type == "CAMERA"){
            imgPlus.setImageResource(
                    R.drawable.ic_photo_camera_white
            );
        } else if(type == "PLUS"){
            imgPlus.setLayoutParams(new DrawerLayout.LayoutParams(convertDpToPixel(48,dialogView.getContext()), convertDpToPixel(48,dialogView.getContext())));
            imgPlus.setImageResource(
                    R.drawable.ic_plus
            );
        } else if(type == "FOLDER"){
            imgPlus.setImageResource(
                    R.drawable.ic_folder_white
            );
        }
        linearLayout.addView(imgPlus);
    }
}