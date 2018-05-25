package com.example.lenovo.elapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.DatePicker;

/**
 * Created by hxh on 2018/5/25.
 */

public class CalendarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        DatePicker datePicker=findViewById(R.id.datePicker);
    }
}
