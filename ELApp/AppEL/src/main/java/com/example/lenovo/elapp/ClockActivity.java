package com.example.lenovo.elapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.moos.library.CircleProgressView;

import Activitys.MainActivity;
import Managers.CountingDown;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by hxh on 2018/5/24.
 */

public class ClockActivity extends AppCompatActivity {
    private Thread thread;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Button circleBrn = (Button) findViewById(R.id.circle_return);
        circleBrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //CircleProgressView
        CircleProgressView circleProgressView = findViewById(R.id.progressView_circle);
        circleProgressView.setStartProgress(0);
        circleProgressView.setEndProgress(100);
        //circleProgressView.setTrackColor(R.color.white);
        circleProgressView.setCircleBroken(true);
        circleProgressView.setTrackWidth(30);
        circleProgressView.setProgressDuration(5*1000);
        circleProgressView.setTrackEnabled(true);
        circleProgressView.setFillEnabled(false);
        circleProgressView.startProgressAnimation();
        circleProgressView.setProgressTextColor(Color.WHITE);

        CountdownView countdownView=findViewById(R.id.countingDown);
        CountingDown countingDown=CountingDown.getCountingDown(this);
        countingDown.start(5,countdownView);
    }
}
