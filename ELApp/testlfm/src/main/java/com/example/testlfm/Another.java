package com.example.testlfm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import BackManagers.TaskFailed;
import BackManagers.WinJudgement;
import Managers.MusicManager;
import Managers.Task;

public class Another extends AppCompatActivity implements View.OnClickListener {

    private Button btn_set;
    private Button btn_cancel;
    private AlarmManager alarmManager;
    private PendingIntent pi;
    private TaskFailed taskFailed;
    private MediaPlayer mediaPlayer;
    private MusicManager musicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        musicManager = MusicManager.getMusicManager();
        mediaPlayer = musicManager.GetMediaPlayer();
        taskFailed = new TaskFailed();
        bindViews();
    }

    private void bindViews() {
        btn_set = findViewById(R.id.btn_set);
        btn_cancel = findViewById(R.id.btn_cancel);
        WinJudgement winJudgement = WinJudgement.getWinJudgement(Another.this, Task.getTask(), mediaPlayer, 10);
        winJudgement.setMusicPathList(Another.this, ".mp3", "music");
        winJudgement.JudgementStart(Another.this);

        btn_set.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set:
//                Calendar currentTime = Calendar.getInstance();
//                new TimePickerDialog(this, 0,
//                        (view, hourOfDay, minute) -> {
//                            //设置当前时间
//                            Calendar c = Calendar.getInstance();
//                            c.setTimeInMillis(System.currentTimeMillis());
//                            // 根据用户选择的时间来设置Calendar对象
//                            c.set(Calendar.HOUR, hourOfDay);
//                            c.set(Calendar.MINUTE, minute);
//                            // ②设置AlarmManager在Calendar对应的时间启动Activity
//                            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
//                                    pi);
//                            Log.e("HEHE", c.getTimeInMillis() + "");   //这里的时间是一个unix时间戳
//                            // 提示闹钟设置完毕:
//                            Toast.makeText(this, "闹钟设置完毕~" + c.getTimeInMillis(),
//                                    Toast.LENGTH_SHORT).show();
//                        }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
//                        .get(Calendar.MINUTE), false).show();
//                btn_cancel.setVisibility(View.VISIBLE);
//                break;
//                TimeManager timeManager = TimeManager.getTimeManager();
//                int Hour = Integer.parseInt(timeManager.getHour());
//                int minute = Integer.parseInt(timeManager.getMinute());
//                int second = Integer.parseInt(timeManager.getSecond());
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR, Hour);
//                calendar.set(Calendar.MINUTE, minute);
//                calendar.set(Calendar.SECOND, second+10);
//                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
//                ClockManager clockManager = ClockManager.getClockManager(this);
//                clockManager.setClock(this, myReceiver.class, 0);

                break;
            case R.id.btn_cancel:
                alarmManager.cancel(pi);
                btn_cancel.setVisibility(View.GONE);
                Toast.makeText(this, "闹钟已取消", Toast.LENGTH_SHORT)
                        .show();
                break;
        }
    }
}


