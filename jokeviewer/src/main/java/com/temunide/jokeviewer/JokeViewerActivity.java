package com.temunide.jokeviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeViewerActivity extends AppCompatActivity {
public static final String EXTRA_JOKE = "joke";
private final String ERROR_NO_JOKE = "Joke needed to be sent as en EXTRA with key com.temunide.JokeViewerActivity.EXTRA_JOKE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_viewer);
        Bundle extras = getIntent().getExtras();
        if(!extras.containsKey(EXTRA_JOKE)){
            throw new IllegalStateException(ERROR_NO_JOKE);
        }else {
            ((TextView)findViewById(R.id.joke_view)).setText(extras.getString(EXTRA_JOKE));
        }

    }
}
