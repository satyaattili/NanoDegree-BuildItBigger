package in.mobileappdev.joksandrolib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokesTellingLibActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lib);
        Intent recievedJokeIntent = getIntent();

        if(recievedJokeIntent != null){

            String joke = recievedJokeIntent.getStringExtra(getString(R.string.joke_key));
            if(joke != null){
                TextView tv = (TextView) findViewById(R.id.joke_text);
                tv.setText(joke);
            }

        }
    }
}
