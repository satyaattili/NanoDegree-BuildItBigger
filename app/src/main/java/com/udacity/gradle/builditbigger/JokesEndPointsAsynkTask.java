package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigge.backend.myApi.MyApi;

import java.io.IOException;

import in.mobileappdev.jokeslib.Joke;
import in.mobileappdev.jokeslib.JokesProvider;

/**
 * Created by satyanarayana.avv on 31-08-2016.
 */

public class JokesEndPointsAsynkTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context mContext;
    private ProgressBar mProgressBar;
    private JokeListener mJokeListener;

    public JokesEndPointsAsynkTask(Context context, ProgressBar progressBar, JokeListener jokeListener) {
        this.mContext = context;
        this.mProgressBar = progressBar;
        this.mJokeListener = jokeListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once

            MyApi.Builder builder = new
                    MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override                         public void initialize(AbstractGoogleClientRequest<?>
                                                                                         abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();

          /*  MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                    AndroidJsonFactory(), null)
                    .setRootUrl("https://android­app­backend.appspot.com/_ah/api/");
            myApiService = builder.build();*/
        }

        //mContext = params[0];


        try {

            Joke joke = JokesProvider.getJokesProvider().getJoke();

            return myApiService.sayHi(joke.getJoke()).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(mProgressBar != null){
            mProgressBar.setVisibility(View.GONE);
        }

        /*// Create Intent to launch JokeFactory Activity
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        // Put the string in the envelope
        intent.putExtra(DisplayJokeActivity.JOKE_KEY,result);
        context.startActivity(intent);
*/
       // progressDialog.dismiss();
        Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
        mJokeListener.onJokeRecieved(result);
    }
}