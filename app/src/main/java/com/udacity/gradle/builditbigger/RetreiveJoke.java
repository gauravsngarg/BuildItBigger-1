package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

/**
 * Created by Mahesh Gaya on 12/17/16.
 */

//input, progress, output
public class RetreiveJoke extends AsyncTask<Void, Void, String> {
    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }
}
