package com.example.lenovo.elapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by hxh on 2018/5/24.
 */

public class ClockActivity extends AppCompatActivity {
    private Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        CircleView circleView = (CircleView) findViewById(R.id.circle_view);
        circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thread != null) {
                    thread.interrupt();
                    thread = null;
                }
                thread = new Thread(circleView);
                thread.start();
            }
        });
    }
}
