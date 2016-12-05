package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.maheshgaya.JokeTeller;
import com.maheshgaya.android.displayjoke.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button showJoke = (Button)rootView.findViewById(R.id.show_joke_btn);
        showJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
            }
        });
        AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return rootView;
    }

    protected void tellJoke() {
        //Toast.makeText(this, JokeTeller.showJoke(), Toast.LENGTH_SHORT).show();
        Intent jokeIntent = new Intent(getActivity(), JokeActivity.class);
        JokeTeller jokeTeller = new JokeTeller();
        String joke = jokeTeller.getJoke();
        jokeIntent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(jokeIntent);
    }
}
