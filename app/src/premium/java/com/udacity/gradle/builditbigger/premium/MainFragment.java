package com.udacity.gradle.builditbigger.premium;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.JokeListener;
import com.udacity.gradle.builditbigger.JokesEndPointsAsynkTask;
import com.udacity.gradle.builditbigger.R;

import in.mobileappdev.joksandrolib.JokesTellingLibActivity;

public class MainFragment extends Fragment {


    private ProgressBar progressBar;
    //private LinearLayout jokeDiaplayView;
    //private TextView jokeText;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
       // jokeDiaplayView = (LinearLayout) v.findViewById(R.id.joke_display_layout);
        //jokeText = (TextView) v.findViewById(R.id.joke_text);
        v.findViewById(R.id.tellJokeBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new JokesEndPointsAsynkTask(getActivity(), progressBar,jokeListener).execute(getActivity());
            }
        });

        progressBar = (ProgressBar) v.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);

        return v;
    }

    private JokeListener jokeListener = new JokeListener() {
        @Override
        public void onJokeRecieved(String joke) {
            if(joke != null){
                Intent libActivity = new Intent(getActivity(), JokesTellingLibActivity.class);
                libActivity.putExtra(getString(in.mobileappdev.joksandrolib.R.string.joke_key), joke);
                startActivity(libActivity);
            }

        }
    };


}
