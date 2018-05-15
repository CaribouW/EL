package Managers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

//设置闹钟
public class ClockManager {
    private TimeManager timeManager;
    private AlarmManager alarmManager;
    private PendingIntent pi;
    private int Hour;
    private int Minute;
    private int Second;

    private Calendar getCalender() {
        Calendar calendar = Calendar.getInstance();

        return calendar;
    }

    private ClockManager(Context context) {

        timeManager = TimeManager.getTimeManager();
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Hour = Integer.parseInt(timeManager.getHour());
        Minute = Integer.parseInt(timeManager.getMinute());
        Second = Integer.parseInt(timeManager.getSecond());
    }

    public static ClockManager getClockManager(Context context) {

        return new ClockManager(context);
    }

    public void cancelClock(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.cancel(pi);
    }


    public void setClock(Context context, Class<?> cls, int second) {
        ToClock(context, cls, this.Hour, this.Minute, second);
    }

    public void setClock(Context context, Class<?> cls, int minute, int second) {
        ToClock(context, cls, this.Hour, minute, second);
    }

    public void setClock(Context context, Class<?> cls, int hour, int minute, int second) {
        ToClock(context, cls, hour, minute, second);
    }


    public void setDelay(Context context, Class<?> cls, int second) {
        DelayClock(context, cls, 0, 0, second);
    }

    public void setDelay(Context context, Class<?> cls, int minute, int second) {
        DelayClock(context, cls, 0, minute, second);
    }

    public void setDelay(Context context, Class<?> cls, int hour, int minute, int second) {
        DelayClock(context, cls, hour, minute, second);
    }

    public void setRepeating(Context context, Class<?> cls, int second, double interval) {
        RepeatingClock(context, cls, this.Hour, this.Minute, second, interval);
    }

    public void setRepeating(Context context, Class<?> cls, int minute, int second, double interval) {
        RepeatingClock(context, cls, this.Hour, minute, second, interval);
    }

    public void setRepeating(Context context, Class<?> cls, int hour, int minute, int second, double interval) {
        RepeatingClock(context, cls, hour, minute, second, interval);
    }

    public void setGoodMorning() {

    }

    public void setGoodEvening() {

    }

    private void addClock() {

    }

    private void ToClock(Context context, Class<?> cls, int hour, int minute, int second) {
        Intent intent = new Intent(context, cls);
        pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
    }

    private void DelayClock(Context context, Class<?> cls, int hour, int minute, int second) {
        Intent intent = new Intent(context, cls);
        pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR, this.Hour + hour);
        calendar.set(Calendar.MINUTE, this.Minute + minute);
        calendar.set(Calendar.SECOND, this.Second + second);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
    }

    private void RepeatingClock(Context context, Class<?> cls, int hour, int minute, int second, double Repeating) {
        long interval = (long) (Repeating * 60 * 1000);
        Intent intent = new Intent(context, cls);
        pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, this.Hour);
        calendar.set(Calendar.MINUTE, this.Minute);
        calendar.set(Calendar.SECOND, this.Second);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                interval, pi);
    }

}