package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.product.Utils;

import java.util.Random;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    public static String mCurrentJoke;
    private static final String TAG = MainFragment.class.getSimpleName();

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
        Log.d(TAG, "onCreateView: IS FREE?: " + BuildConfig.IS_FREE);
        Utils.getAdView(rootView);


        return rootView;
    }

    protected void tellJoke() {
        //initialize AsyncTask
        RetrieveJokeTask retrieveJokeTask = new RetrieveJokeTask();
        Random rand = new Random();
        int randNumber = rand.nextInt(3);
        mCurrentJoke = jokes[randNumber];
        retrieveJokeTask.execute(new Pair<Context, String>(getActivity(), jokes[randNumber]));
    }
}
