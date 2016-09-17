package com.example.jasongibson.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public final void sendPostGood(View v) {
        String[] sampleData = {"patient1", "1997-07-16T19:20+01:00", "130"};
        new SendPost(v.getContext()).post(sampleData);
    }

    public final void sendPostLow(View v) {
        String[] sampleData = {"patient1", "1997-07-16T19:20+01:00", "20"};
        new SendPost(v.getContext()).post(sampleData);
    }
    public final void sendPostHigh(View v) {
        String[] sampleData = {"patient1", "1997-07-16T19:20+01:00", "410"};
        new SendPost(v.getContext()).post(sampleData);
    }
}
