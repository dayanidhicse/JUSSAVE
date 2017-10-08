package com.dayanidhid.jussave.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.provider.FontRequest;
import android.support.v4.provider.FontsContractCompat;
import android.widget.TextView;

import com.dayanidhid.jussave.R;

/**
 * Created by dayanidhi.d on 04/10/17.
 */

public class DynamicFontDownload {
    public static Handler mHandler = null;
    public static void requestDownload(String familyName, final TextView textView, Context context) {
        QueryBuilder queryBuilder = new QueryBuilder(familyName);
        String query = queryBuilder.build();

//        Log.d(TAG, "Requesting a font. Query: " + query);
        FontRequest request = new FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                query,
                R.array.com_google_android_gms_fonts_certs);


        FontsContractCompat.FontRequestCallback callback = new FontsContractCompat
                .FontRequestCallback() {
            @Override
            public void onTypefaceRetrieved(Typeface typeface) {
                textView.setTypeface(typeface);
                textView.setEnabled(true);
            }

            @Override
            public void onTypefaceRequestFailed(int reason) {
                textView.setEnabled(true);
            }
        };
        FontsContractCompat
                .requestFont(context, request, callback,
                        getHandlerThreadHandler());
    }

    private static Handler getHandlerThreadHandler() {
        if (mHandler == null) {
            HandlerThread handlerThread = new HandlerThread("fonts");
            handlerThread.start();
            mHandler = new Handler(handlerThread.getLooper());
        }
        return mHandler;
    }
}
