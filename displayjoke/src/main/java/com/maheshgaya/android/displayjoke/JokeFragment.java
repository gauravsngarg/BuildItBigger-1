package com.maheshgaya.android.displayjoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mahesh Gaya on 12/4/16.
 */

public class JokeFragment extends Fragment {
    private String mJoke;
    private static final String TAG = JokeFragment.class.getSimpleName();
    private TextView mJokeTextView;

    public JokeFragment(){

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        JokeActivity parentActivity = (JokeActivity)getActivity();
        mJoke = parentActivity.getJokeFromIntent();
        mJokeTextView.setText(mJoke);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        mJokeTextView = (TextView)rootView.findViewById(R.id.joke_textview);
        return rootView;
    }


}
