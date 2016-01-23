package com.example.by.colorid;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    private CircleCrossView crossView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        crossView=(CircleCrossView)findViewById(R.id.cross_view);
        Log.i(TAG, "MAIN");
        crossView.setColor(Color.GREEN);
        crossView.refresh();*/
    }
}
