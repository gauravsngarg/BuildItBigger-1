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
public class RetrieveJokeTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static JokesApi jokeApiService = null;
    private Context mContext;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(jokeApiService == null) {  // Only do this once

            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            jokeApiService = builder.build();
        }

        mContext = params[0].first;
        String name = params[0].second;

        try {
            return jokeApiService.getJoke(name).getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        //open joke Activity
        Intent jokeIntent = new Intent(mContext, JokeActivity.class);
        jokeIntent.putExtra(JokeActivity.JOKE_KEY, result);
        mContext.startActivity(jokeIntent);
    }
}
