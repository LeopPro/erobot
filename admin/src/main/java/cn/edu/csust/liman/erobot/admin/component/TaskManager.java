package cn.edu.csust.liman.erobot.admin.component;

import cn.edu.csust.liman.erobot.admin.dao.TaskDao;
import cn.edu.csust.liman.erobot.admin.entity.Task;
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
}
