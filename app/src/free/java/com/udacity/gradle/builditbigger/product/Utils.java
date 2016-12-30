package com.udacity.gradle.builditbigger.product;

import android.app.Activity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.R;

/**
 * Created by Mahesh Gaya on 12/27/16.
 */

public class Utils {
    public static void getAdView(View view){
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        if (mAdView != null) {
            mAdView.loadAd(adRequest);
        }
        return mAdView;
    }
}
