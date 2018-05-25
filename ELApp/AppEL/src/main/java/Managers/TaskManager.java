package Managers;


import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

//任务名，重要性（颜色：四种颜色）
//开始时间，完成时间
//所需交互APP
//单次事项or每周每天
//提醒时间
//备注


//Json解析
public class TaskManager {
    private String tasksPath;
    private List<Task> taskList;
    private FileManager file_manager;
    private Achievement achievement;

    private TaskManager(Context context) {
        taskList = new LinkedList<>();
        file_manager = FileManager.getFileManager();
        achievement = Achievement.getAchievement(context);
        tasksPath = file_manager.getAppPath(context) + "tasks.txt";
    }

    public static TaskManager getTaskManager(Context context) {
        return new TaskManager(context);
    }

    public Task addTask(Task task) {
        try {
            if (!taskList.contains(task))
                taskList.add(task);
            writeObjFile(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return task;
    }

    public List<Task> getTaskList() {
        try {
            this.taskList = getTasksFormFile();
            return taskList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.taskList;
    }

    public void deleteTask(Task task) {
        try {
            this.taskList.remove(task);
            writeObjFile(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.taskList = flushTask();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void TaskReady(Task task) {
        task.setCondition("ready");
        task.setFailed(false);
        task.setFinished(false);
        addTask(task);
    }

    public void TaskBegin(Task task) {
        task.setCondition("begin");
        task.setFailed(false);
        task.setFinished(false);
    }

    public void TaskSuccess(Task task) {
        task.setCondition("success");
        task.setFinished(true);
        task.setFailed(false);
        int coin = Integer.parseInt(achievement.getCoin());
        achievement.setCoin(String.valueOf(coin + 100));
    }

    public void TaskFail(Task task) {
        task.setCondition("fail");
        task.setFinished(true);
        task.setFailed(true);
        int coin = Integer.parseInt(achievement.getCoin());
        achievement.setCoin(String.valueOf(coin - 100));
    }

    // 0: ready  1:success 2:fail
    public int[] getRatio() {
        int[] result = {0, 0, 0};
        List<Task> list = getTaskList();
        for (Task task : list) {
            String condition = task.getCondition();
            if (condition.equals("ready"))
                result[0]++;
            else if (condition.equals("success"))
                result[1]++;
            else if (condition.equals("fail"))
                result[2]++;
        }
        return result;
    }

    public List<Task> getReadyTaskList() {
        return TaskFilter("ready");
    }

    public List<Task> getFailedTaskList() {
        return TaskFilter("fail");
    }

    public List<Task> getSuccessTaskList() {
        return TaskFilter("success");
    }

    private List<Task> flushTask() throws IOException {
        this.taskList = getTasksFormFile();
        return this.taskList;
    }

    private List<Task> getTasksFormFile() throws IOException {
        BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(new FileOutputStream(getBuffFile(), true));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(getBuffFile())));
        StringBuilder tmp = new StringBuilder();
        String result;
        while ((result = br.readLine()) != null) {
            tmp.append(result);
        }
        result = tmp.toString();
        br.close();
        bufferedOutputStream.close();
        return JSON.parseArray(result, Task.class);
    }

    private File getBuffFile() {
        return new File(this.tasksPath);
    }

    @NonNull
    private List<Task> TaskFilter(String str) {
        List<Task> list = getTaskList();
        List<Task> result = new LinkedList<>();
        for (Task task : list) {
            if (task.getCondition().equals(str))
                result.add(task);
        }
        return result;
    }

    private void writeObjFile(List<Task> jsonArray) throws IOException {
        synchronized (this) {
            BufferedOutputStream bufferedOutputStream =
                    new BufferedOutputStream(new FileOutputStream(getBuffFile()));
            String output = JSON.toJSONString(jsonArray, true);
            bufferedOutputStream.write(output.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
    }
}
