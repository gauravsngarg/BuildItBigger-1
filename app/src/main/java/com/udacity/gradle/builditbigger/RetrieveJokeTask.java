package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.maheshgaya.JokeTeller;
import com.maheshgaya.android.builditbigger.backend.jokesApi.JokesApi;
import  com.google.api.client.extensions.android.http.AndroidHttp;
import com.maheshgaya.android.displayjoke.JokeActivity;

import java.io.IOException;

/**
 * Created by Mahesh Gaya on 12/23/16.
 */

//input, progress, output
public class RetrieveJokeTask extends AsyncTask<Context, Void, String> {
    private static JokesApi jokeApiService = null;
    private Context mContext;
    //gets the current joke for testing
    public static String mJoke;

    //gets the joke in the background
    @Override
    protected String doInBackground(Context... params) {
        if(jokeApiService == null) {  // Only do this once
            String ipAddress = BuildConfig.YOUR_COMPUTER_IP_ADDRESS;
            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://" + ipAddress +":8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            jokeApiService = builder.build();
        }

        mContext = params[0]; //gets the context

        try {
            return jokeApiService.getJoke().execute().getFunnyJoke(); //return the result
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        //open joke Activity
        mJoke = result; //for JUnit testing
        Intent jokeIntent = new Intent(mContext, JokeActivity.class);
        jokeIntent.putExtra(JokeActivity.JOKE_KEY, result);
        mContext.startActivity(jokeIntent);
    }
}
