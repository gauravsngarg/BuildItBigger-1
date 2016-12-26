package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.maheshgaya.JokeTeller;
import com.maheshgaya.android.displayjoke.JokeActivity;

import java.util.Random;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    //Joke from http://stackoverflow.com/questions/234075/what-is-your-best-programmer-joke
    private static final String[] jokes = new String[]{
            "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\"",
            "Q: how many programmers does it take to change a light bulb?\n" +
                    "\n" +
                    "A: none, that's a hardware problem",
            "When your hammer is C++, everything begins to look like a thumb."

    };

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
        //initialize AsyncTask
        RetrieveJokeTask retrieveJokeTask = new RetrieveJokeTask();
        Random rand = new Random();
        int randNumber = rand.nextInt(3);

        retrieveJokeTask.execute(new Pair<Context, String>(getActivity(), jokes[randNumber]));
    }
}
