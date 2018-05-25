package com.example.lenovo.elapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import Managers.TimeManager;

/**
 * Created by hxh on 2018/5/25.
 */

public class CalendarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        DatePicker datePicker=findViewById(R.id.datePicker);
        TextView textView = (TextView)findViewById(R.id.main_month);
        TextView textView2 = (TextView)findViewById(R.id.main_day);
        TextView textView3 = (TextView)findViewById(R.id.main_year);
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView.setText(year);
                textView2.setText(monthOfYear);
                textView3.setText(dayOfMonth);
            }
        });
    }
}
