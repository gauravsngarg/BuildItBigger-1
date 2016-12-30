package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.product.Utils;

import java.util.Random;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    //gets the current joke for testing
    public static String mCurrentJoke;
    //used for logging purposes
    private static final String TAG = MainFragment.class.getSimpleName();
    //shows the pretty horizontal progress bar
    private ProgressBar mProgressBar;
    //used to show the full screen ad for only first time
    private boolean mShownAd = false;

    //Joke from http://stackoverflow.com/questions/234075/what-is-your-best-programmer-joke
    private static final String[] jokes = new String[]{
            "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\"",
            "Q: how many programmers does it take to change a light bulb?\n" +
                    "\n" +
                    "A: none, that's a hardware problem",
            "When your hammer is C++, everything begins to look like a thumb."

    };

    /**
     * Default constructor
     */
    public MainFragment() {

    }

    /**
     * Initializes the progress bar
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProgressBar = (ProgressBar)getActivity().findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * initializes the views for the fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //add functionality to the button
        Button showJoke = (Button)rootView.findViewById(R.id.show_joke_btn);
        showJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
            }
        });

        //if the version is free then show ad
        Log.d(TAG, "onCreateView: IS FREE?: " + BuildConfig.IS_FREE);
        Utils.getAdView(rootView); //banner ad
        Utils.getInterstitialAd(getActivity()); //full screen ad

        return rootView;
    }

    /**
     * reset the progress bar
     */
    @Override
    public void onResume() {
        super.onResume();
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * open ShowJoke activity to show the joke
     * retrieved from the Google Cloud Endpoint via AsyncTask
     */
    protected void tellJoke() {
        if (Utils.isAdAvailable() && !mShownAd) {
            //show ad if available or if it is the first time
            Utils.showInterstitialAd();
            mShownAd = true;
        } else {
            //show the progress bar
            mProgressBar.setVisibility(View.VISIBLE);
            //initialize AsyncTask and get the joke
            RetrieveJokeTask retrieveJokeTask = new RetrieveJokeTask();
            Random rand = new Random();
            int randNumber = rand.nextInt(3);
            mCurrentJoke = jokes[randNumber];
            retrieveJokeTask.execute(new Pair<Context, String>(getActivity(), jokes[randNumber]));
        }
    }
}
