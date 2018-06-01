package cn.edu.csust.liman.erobot.admin.component;

import cn.edu.csust.liman.erobot.admin.dao.TaskDao;
import cn.edu.csust.liman.erobot.admin.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class TaskManager {
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private SenderConnection senderConnection;
    private boolean changed = false;

    public TaskManager() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (changed) {
                    changed = false;
                    flushAllTash();
                }
            }
        }).start();
    }

    public void flushTask(Long id) {
        Map<String, Object> executableTask = taskDao.getExecutableTask(id);
        try {
            senderConnection.sendTask(executableTask);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(Task task) {
        try {
            senderConnection.deleteTask(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void change() {
        changed = true;
    }

    public void flushAllTash() {
        List<Map<String, Object>> allExecutableTask = taskDao.selectAllExecutableTask();
        try {
            for (Map<String, Object> executableTask : allExecutableTask) {
                senderConnection.sendTask(executableTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
