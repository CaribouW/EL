package com.example.lenovo.elapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import Managers.Task;
import Managers.TaskPicker;

public class NewTaskActivity extends AppCompatActivity {
    private CheckBox[] checkBoxes = new CheckBox[4];
    private String mImportance;
    private String[] mRepetition = {"monday","tuesday"};
    private String mAnotherApp = "com.gotokeep.keep";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        checkBoxes[0] = (CheckBox) findViewById(R.id.redCheckbox);
        checkBoxes[1] = (CheckBox) findViewById(R.id.orangeCheckbox);
        checkBoxes[2] = (CheckBox) findViewById(R.id.pinkCheckbox);
        checkBoxes[3] = (CheckBox) findViewById(R.id.blueCheckbox);
        checkBoxes[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    for (int i = 0; i < checkBoxes.length; i++) {
                        //不等于当前选中的就变成false
                        if (checkBoxes[i].getId()==(buttonView.getId())) {
                            checkBoxes[i].setChecked(true);
                        } else {
                            checkBoxes[i].setChecked(false);

                        }
                    }
                }
            }
        });
        checkBoxes[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    for (int i = 0; i < checkBoxes.length; i++) {
                        //不等于当前选中的就变成false
                        if (checkBoxes[i].getId()==(buttonView.getId())) {
                            checkBoxes[i].setChecked(true);
                        } else {
                            checkBoxes[i].setChecked(false);
                        }
                    }
                }
            }
        });
        checkBoxes[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    for (int i = 0; i < checkBoxes.length; i++) {
                        //不等于当前选中的就变成false
                        if (checkBoxes[i].getId()==(buttonView.getId())) {
                            checkBoxes[i].setChecked(true);
                        } else {
                            checkBoxes[i].setChecked(false);
                        }
                    }
                }
            }
        });
        checkBoxes[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    for (int i = 0; i < checkBoxes.length; i++) {
                        //不等于当前选中的就变成false
                        if (checkBoxes[i].getId()==(buttonView.getId())) {
                            checkBoxes[i].setChecked(true);
                        } else {
                            checkBoxes[i].setChecked(false);
                        }
                    }
                }
            }
        });
        for(int j = 0;j<checkBoxes.length;j++){
            if(checkBoxes[j].isChecked()){
                switch (checkBoxes[j].getId()){
                    case R.id.redCheckbox:
                        mImportance = "red";
                        break;
                    case R.id.orangeCheckbox:
                        mImportance = "orange";
                        break;
                    case R.id.pinkCheckbox:
                        mImportance = "pink";
                        break;
                    case R.id.blueCheckbox:
                        mImportance = "blue";
                        break;
                }
            }
        }

        int count = 0;
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.monday);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.tuesday);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.wednesday);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.thursday);
        CheckBox checkBox5 = (CheckBox) findViewById(R.id.friday);
        CheckBox checkBox6 = (CheckBox) findViewById(R.id.saturday);
        CheckBox checkBox7 = (CheckBox) findViewById(R.id.sunday);
        if(checkBox1.isChecked())count++;



        Button backBtn = (Button) findViewById(R.id.newTask_btdBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button finishBtn = (Button) findViewById(R.id.btnRight);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = Task.getTask();
                EditText editText1 = (EditText) findViewById(R.id.editTaskName);
                EditText editText2 = (EditText) findViewById(R.id.task_remark);
                EditText editText3 = (EditText) findViewById(R.id.editText1);
                EditText editText4 = (EditText) findViewById(R.id.editText2);
                EditText editText5 = (EditText) findViewById(R.id.edit_ring);
                task.setTaskName(editText1.getText().toString());
                task.setRemarks(editText2.getText().toString());
                task.setImportance(mImportance);
                task.setAnotherApp(mAnotherApp);
                task.setRepetition(mRepetition);
                task.setBeginTime(editText3.getText().toString());
                task.setEndTime(editText4.getText().toString());
                task.setRingTime(editText5.getText().toString());
                TaskPicker taskPicker = TaskPicker.getTaskPicker();
                taskPicker.TaskReady(task);
            }
        });
    }



}
