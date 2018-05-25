package BackUps;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicReference;

import Managers.Achievement;
import Managers.ClockManager;
import Managers.FileManager;
import Managers.MusicManager;
import Managers.ScreenManager;
import Managers.Task;
import Managers.TaskManager;
import cn.iwgang.countdownview.CountdownView;

public class WinJudgement {
    private volatile MediaPlayer mediaPlayer;
    private volatile boolean IsCountingBegin = false;
    private volatile boolean IsCountingEnd = false;
    private volatile TaskManager taskManager;
    private volatile int MaxDelay = 5;
    private volatile ClockManager clockManager;
    private volatile ScreenManager lockScreenManager;
    private volatile MusicManager musicManager;
    private volatile FileManager fileManager;
    private volatile Achievement achievement;
    private volatile Task task;
    private volatile List<String> musicPathList;
    private volatile boolean IsPause = true;
    private volatile boolean IsFailed = false;
    private volatile Class<? extends TaskFailed> FailedClass;
    private volatile CountdownView countdownView;

    private WinJudgement(Context context, MediaPlayer mediaPlayer, int MaxDelay) {
        synchronized (this) {
            this.MaxDelay = MaxDelay;
            clockManager = ClockManager.getClockManager(context);
            lockScreenManager = ScreenManager.getScreenManager(context);
            musicManager = MusicManager.getMusicManager();
            this.mediaPlayer = mediaPlayer;
            taskManager = TaskManager.getTaskManager(context);
            fileManager = FileManager.getFileManager();
            achievement = Achievement.getAchievement(context);
            this.FailedClass = TaskFailed.class;
            musicPathList = new LinkedList<>();
        }
    }

    public static WinJudgement getWinJudgement(Context context, MediaPlayer mediaPlayer, int MaxDelay) {
        return new WinJudgement(context, mediaPlayer, MaxDelay);
    }

    public void setMusicPathList(Context context, String suffix, String targeted) {
        try {
            File musicBuffFile = fileManager.getAllSameSuffixPath(context, suffix, "/" + targeted, true);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(musicBuffFile));
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null && musicPathList.size() <= 10) {
                musicPathList.add(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized Task JudgementStart(Context context) {
        Toast.makeText(context, "任务已经开始\n请少侠放下手机 开始静心专注吧~~~", Toast.LENGTH_LONG).show();
        taskManager.addTask(task);
        try {
            countdownView.setOnCountdownEndListener(cv -> {
                IsCountingEnd = true;
                musicManager.pause(mediaPlayer);
                IsPause = true;
            });
            PlayInArray();
            ScreenListenerPart(context, FailedClass);
        } catch (Exception e) {
            return this.task;
        }
        return this.task;
    }

    private synchronized void PlayInArray() {

        mediaPlayer = musicManager.playExternalAbsolutePath(mediaPlayer, musicPathList.get(0));
        AtomicReference<ListIterator<String>> iterator = new AtomicReference<>(musicPathList.listIterator());
        iterator.get().next();
        mediaPlayer.setOnCompletionListener(mp -> {
            if (!IsFailed && !IsCountingEnd) {
                if (!iterator.get().hasNext()) {
                    iterator.set(musicPathList.listIterator());
                }
                mediaPlayer = musicManager.playExternalAbsolutePath(mediaPlayer, iterator.get().next());
                this.IsPause = false;
            }
        });

    }

    private synchronized void CountingUpStart(Context context, Class<?> cls) {
        new Thread(() -> clockManager.setDelay(context, cls, MaxDelay, 0)).start();
    }

    private synchronized void CountingUpShutDown(Context context, Class<?> cls) {
        new Thread(() -> clockManager.cancelClock(context, cls)).start();
    }

    private void ScreenListenerPart(Context context, Class<?> cls) {
        ScreenManager l = new ScreenManager(context);
        l.begin(new ScreenManager.ScreenStateListener() {

            @Override
            public void onUserPresent() {
                if (IsFailed || IsCountingEnd)
                    return;
                mediaPlayer = musicManager.pause(mediaPlayer);
                IsPause = true;
                Toast.makeText(context, "     任务进行中 请保持专注\n不要玩手机哦~", Toast.LENGTH_SHORT).show();
                CountingUpStart(context, cls);
            }

            @Override
            public void onScreenOn() {
//                Toast.makeText(context, "天干物燥\n小心火烛", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScreenOff() {
                if (IsFailed || IsCountingEnd)
                    return;
                if (!IsFailed())
                    mediaPlayer = musicManager.ContinueToPlay(mediaPlayer);
                CountingUpShutDown(context, cls);
                IsPause = false;
            }
        });
    }

    public boolean IsFailed() {
        return IsFailed;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public CountdownView getCountdownView() {
        return countdownView;
    }

    public void setCountdownView(CountdownView countdownView) {
        this.countdownView = countdownView;
    }
}
