package com.example.testlfm;

import android.app.Activity;

import cn.iwgang.countdownview.CountdownView;

//compile 'com.github.iwgang:countdownview:2.1.6'
public class CountingDown {
    public static CountdownView getCountingDown(Activity activity, int R_id) {
        return activity.findViewById(R_id);
    }
}
