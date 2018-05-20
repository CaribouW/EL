package com.example.testlfm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import Managers.Achievement;
import Managers.FileManager;
import Managers.TimeManager;

public class MainActivity extends AppCompatActivity {
    private FileManager fileManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        fileManager=FileManager.getFileManager();
        if(!FileManager.IsPermitted(this))
            FileManager.requestPermissions(this);
//        TaskManager taskManager = TaskManager.getTaskManager(this);
//        taskManager.addTask(Task.getTask());
//        Task t1 = Task.getTask();
//        t1.setBeginTime("begin");
//        t1.setDuration("hahaha");
//        taskManager.addTask(t1);
//        Task t3 = Task.getTask();
//        t3.setImportance("imp");
//        taskManager.addTask(t3);;
//        taskManager.deleteTask(t3);
        TimeManager timeManager = TimeManager.getTimeManager();
        new Thread(() -> {
            Calendar a = Calendar.getInstance();
            Calendar b = Calendar.getInstance();
            textView.setText(String.valueOf(timeManager.getTimeStick(a, b)));
        }).start();


    }


    public void playAndCount(View view) {
        Achievement a = Achievement.getAchievement(this);
        a.setAccomplishment("233");
        a.setCoin("333");
        TextView textView = findViewById(R.id.textView);
        textView.setText(a.getAccomplishment() + "\n" + a.getCoin());
    }

    public void btn2Event(View view) {

    }

    public void btn3Event(View view) {
        Intent intent = new Intent(this, Another.class);
        startActivity(intent);
    }
}
