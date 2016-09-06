package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.util.Log;

/**
 * Created by satyanarayana.avv on 06-09-2016.
 */


public class JokeTest extends ApplicationTestCase<Application> {

    private static final String LOG_TAG = "NonEmptyStringTest";

    public JokeTest() {
        super(Application.class);
    }

    @SuppressWarnings("unchecked")
    public void test() {

        // Testing that Async task successfully retrieves a non-empty string
        // You can test this from androidTest -> Run 'All Tests'
        Log.v("NonEmptyStringTest", "Running NonEmptyStringTest test");
        String result = null;
        JokesEndPointsAsynkTask endpointsAsyncTask = new JokesEndPointsAsynkTask(getContext(), null, jokeListener);
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
            Log.d(LOG_TAG, "Retrieved a non-empty string successfully: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }

    private JokeListener jokeListener = new JokeListener() {
        @Override
        public void onJokeRecieved(String joke) {

        }
    };

}
