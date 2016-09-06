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
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.JokeListener;
import com.udacity.gradle.builditbigger.JokesEndPointsAsynkTask;
import com.udacity.gradle.builditbigger.R;

import in.mobileappdev.joksandrolib.JokesTellingLibActivity;


public class MainFragment extends Fragment {

    private InterstitialAd mInterstitialAd;
    private ProgressBar progressBar;
    private Button getJokesBtn;

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
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        getJokesBtn = (Button) root.findViewById(R.id.tellJokeBtn);
        showInterstialAd(root);
        return root;
    }


    private void showInterstialAd(View view){
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));

        getJokesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    new JokesEndPointsAsynkTask(getActivity(), progressBar,jokeListener).execute(getActivity());
                }
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                new JokesEndPointsAsynkTask(getActivity(), progressBar, jokeListener).execute(getActivity());
            }
        });

        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd.loadAd(adRequest);
    }

    private JokeListener jokeListener = new JokeListener() {
        @Override
        public void onJokeRecieved(String joke) {
            if(joke != null){
                Intent intent = new Intent(getActivity(), JokesTellingLibActivity.class);
                intent.putExtra(getString(R.string.joke_key), joke);
                startActivity(intent);
            }

        }
    };
}


