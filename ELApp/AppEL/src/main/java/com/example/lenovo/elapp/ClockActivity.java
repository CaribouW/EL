package com.example.lenovo.elapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.moos.library.CircleProgressView;

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

        //CircleProgressView
        CircleProgressView circleProgressView = findViewById(R.id.progressView_circle);
        circleProgressView.setStartProgress(0);
        circleProgressView.setEndProgress(100);
        //circleProgressView.setTrackColor(R.color.white);
        circleProgressView.setCircleBroken(true);
        circleProgressView.setTrackWidth(20);
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
