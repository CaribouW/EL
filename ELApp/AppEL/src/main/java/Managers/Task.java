package Managers;

import java.util.Timer;

/*
*
* //任务名，重要性（颜色：四种颜色）
//开始时间，完成时间
//所需交互APP
//单次事项or每周每天
//提醒时间
//备注
* */
public class Task {
    private Timer timer;
    private String taskName;
    private String remarks;
    private String importance;  //red orange pink blue.
    private String beginTime;   //day-hour-minute-second
    private String duration;    //
    private String endTime;
    private String[] Repetition;
    private boolean IsFailed;
    private boolean IsFinished;
    private String AnotherApp;
    private String Condition;
    private String RingTime;   //hour-minute-second

    private Task() {
        timer = new Timer();
        taskName = "None";
        Condition = "None";
        remarks = "None";
        importance = "None";
        beginTime = "None";
        endTime = "None";
        AnotherApp = "None";
        RingTime = "None";
        IsFailed = false;
        IsFinished = false;

    }

    public static Task getTask() {
        return new Task();
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String[] getRepetition() {
        return Repetition;
    }

    public void setRepetition(String[] repetition) {
        Repetition = repetition;
    }

    public boolean isFailed() {
        return IsFailed;
    }

    public void setFailed(boolean failed) {
        IsFailed = failed;
    }

    public boolean isFinished() {
        return IsFinished;
    }

    public void setFinished(boolean finished) {
        IsFinished = finished;
    }

    public String getAnotherApp() {
        return AnotherApp;
    }

    public void setAnotherApp(String anotherApp) {
        AnotherApp = anotherApp;
    }

    public String getRingTime() {
        return RingTime;
    }

    public void setRingTime(String ringTime) {
        RingTime = ringTime;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }
}
