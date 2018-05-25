package Managers;

import Tmp_lib.RoundProgressBar;

public class ProcessBar {
    private RoundProgressBar roundProgressBar;

    private ProcessBar(RoundProgressBar roundProgressBar) {
        this.roundProgressBar = roundProgressBar;
    }

    public static ProcessBar getProgressBar(RoundProgressBar roundProgressBar) {
        return new ProcessBar(roundProgressBar);
    }

    public void processBegin(int second) {
        roundProgressBar.startCountDownTime(() -> {
        }, 322.5, 100, second);
    }

    public void processBegin(int minute, int second) {
        processBegin(minute * 60 + second);
    }

    public void processBegin(int hour, int minute, int second) {
        processBegin(hour * 60 + minute, second);
    }
}

/*
*
*
*
* <com.miao.roundprogressbar.RoundProgressBar
            android:id="@+id/roundProgressBar1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_below="@+id/button1"
            android:layout_gravity="center_horizontal"
            android:padding="20dp"
            android_custom:bottomTextColor="#000000"
            android_custom:bottomTextSize="10dp"
            android_custom:roundColor="#757575"
            android_custom:roundProgressOneColor="#8a8a8a"
            android_custom:roundProgressOneWidth="2dp"
            android_custom:roundProgressThreeColor="#ffcb05"
            android_custom:roundProgressThreeWidth="6dp"
            android_custom:roundProgressTwoColor="#b0b0b0"
            android_custom:roundProgressTwoWidth="3dp"
            android_custom:roundWidth="1dp"
            android_custom:smallRoundWidth="6dp"
            android_custom:topTextColor="#000000"
            android_custom:topTextSize="60dp" />
* */