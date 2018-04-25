package cn.edu.csust.liman.erobot.sender.component;

import cn.edu.csust.liman.erobot.sender.dao.TaskDao;
import cn.edu.csust.liman.erobot.sender.entity.Task;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TaskManager implements InitializingBean {
    private Map<Long, Task> taskMap = new ConcurrentHashMap<>();
    @Autowired
    private TaskDao taskDao;

    public void add(Task task) {
        taskMap.put(task.getId(), task);
        task.start();
    }

    public void delete(long taskId) {
        Task task = taskMap.get(taskId);
        if (task == null) {
            return;
        }
        task.shutdown();
        taskMap.remove(taskId);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Task> tasks = taskDao.listAll();
        for (Task task : tasks) {
            this.add(task);
        }
    }
}
