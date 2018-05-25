package com.example.lenovo.elapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
import android.view.View;
import android.widget.Button;

import com.moos.library.CircleProgressView;

import Activitys.MainActivity;
=======

import com.moos.library.CircleProgressView;

import BackUps.WinJudgement;
>>>>>>> 86e3315c2c931159a259e2080aa3243aa9c81374
import Managers.CountingDown;
import Managers.MusicManager;
import Managers.Task;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by hxh on 2018/5/24.
 */

public class ClockActivity extends AppCompatActivity {

    private MusicManager musicManager = MusicManager.getMusicManager();
    private MediaPlayer mediaPlayer = musicManager.GetMediaPlayer();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle);
        MusicManager musicManager = MusicManager.getMusicManager();
        MediaPlayer mediaPlayer = musicManager.GetMediaPlayer();
        WinJudgement winJudgement = WinJudgement.getWinJudgement(ClockActivity.this, mediaPlayer
                , 5);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
<<<<<<< HEAD
        Button circleBrn = (Button) findViewById(R.id.circle_return);
        circleBrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

=======
>>>>>>> 86e3315c2c931159a259e2080aa3243aa9c81374
        //CircleProgressView
        CircleProgressView circleProgressView = findViewById(R.id.progressView_circle);
        CountdownView countdownView = findViewById(R.id.countingDown);

        CountingStart(circleProgressView, countdownView, 10);
        winJudgement.setTask(Task.getTask());
        winJudgement.setCountdownView(countdownView);
        winJudgement.setMusicPathList(this, "mp3", "Music/");
        winJudgement.JudgementStart(this);
    }

    private void CountingStart(CircleProgressView circleProgressView, CountdownView countdownView
            , int second) {
        circleProgressView.setStartProgress(0);
        circleProgressView.setEndProgress(100);
        //circleProgressView.setTrackColor(R.color.white);
        circleProgressView.setCircleBroken(true);
<<<<<<< HEAD
        circleProgressView.setTrackWidth(30);
        circleProgressView.setProgressDuration(5*1000);
=======
        circleProgressView.setTrackWidth(20);
        circleProgressView.setProgressDuration(second * 1000);
>>>>>>> 86e3315c2c931159a259e2080aa3243aa9c81374
        circleProgressView.setTrackEnabled(true);
        circleProgressView.setFillEnabled(false);
        circleProgressView.startProgressAnimation();
        circleProgressView.setProgressTextColor(Color.WHITE);

        CountingDown countingDown = CountingDown.getCountingDown(this);
        countingDown.start(second
                , countdownView);
        countdownView.setOnCountdownEndListener(cv -> mediaPlayer.stop());
    }
}
