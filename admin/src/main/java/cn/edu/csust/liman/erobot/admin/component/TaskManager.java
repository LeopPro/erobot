package cn.edu.csust.liman.erobot.admin.component;

import cn.edu.csust.liman.erobot.admin.dao.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class TaskManager {
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private SenderConnection senderConnection;
    public void updateReceiver(Long id) {

    }

    public void updateGroup(Long id) {

    }

    public void updateTask(Long id) {

    }

    public void deleteReceiver(Long id) {

    }

    public void deleteGroup(Long id) {

    }

    public void deleteTask(Long id) {

    }

    public void addTask(Long id) {
        Map<String, Object> executableTask = taskDao.getExecutableTask(id);
        System.out.println(executableTask);
        try {
            senderConnection.sendTask(executableTask);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
