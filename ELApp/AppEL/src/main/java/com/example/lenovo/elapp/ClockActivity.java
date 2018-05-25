package com.example.lenovo.elapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.moos.library.CircleProgressView;

<<<<<<< HEAD
import Activitys.MainActivity;

import com.moos.library.CircleProgressView;
=======
import java.util.List;
>>>>>>> c73763ba211d54160c3bc0d3f93d82bc09df69b4

import BackUps.WinJudgement;
import Managers.CountingDown;
import Managers.MusicManager;
import Managers.Task;
import Managers.TaskManager;
import cn.iwgang.countdownview.CountdownView;


/**
 * Created by hxh on 2018/5/24.
 */

public class ClockActivity extends AppCompatActivity {

    private MusicManager musicManager = MusicManager.getMusicManager();
    private MediaPlayer mediaPlayer = musicManager.GetMediaPlayer();
    private TaskManager taskManager;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle);
        taskManager = TaskManager.getTaskManager(this);
        Bundle bundle = getIntent().getExtras();
        Task task = findTask(taskManager, (String) bundle.get("task"));
        long duration = Long.parseLong(task.getDuration());
        MusicManager musicManager = MusicManager.getMusicManager();
        MediaPlayer mediaPlayer = musicManager.GetMediaPlayer();
        WinJudgement winJudgement = WinJudgement.getWinJudgement(ClockActivity.this, mediaPlayer
                , 5);
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
        CountdownView countdownView = findViewById(R.id.countingDown);

        CountingStart(circleProgressView, countdownView, duration);  //修改时间 duration
        winJudgement.setTask(task);
        winJudgement.setCountdownView(countdownView);
//        winJudgement.setMusicPathList(this, "mp3", "Music/");
        winJudgement.JudgementStart(this);
    }

    private Task findTask(TaskManager taskManager, String task) {
        List<Task> list = taskManager.getTaskList();
        for (Task task1 : list) {
            if (task1.getTaskName().equals(task))
                return task1;
        }
        return Task.getTask();
    }

    private void CountingStart(CircleProgressView circleProgressView, CountdownView countdownView
            , long second) {
        circleProgressView.setStartProgress(0);
        circleProgressView.setEndProgress(100);
        //circleProgressView.setTrackColor(R.color.white);
        circleProgressView.setCircleBroken(true);
        circleProgressView.setTrackWidth(30);
<<<<<<< HEAD
        circleProgressView.setProgressDuration(5*1000);
=======
        circleProgressView.setProgressDuration(Math.toIntExact(second * 1000));
>>>>>>> c73763ba211d54160c3bc0d3f93d82bc09df69b4
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
