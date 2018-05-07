package cn.edu.csust.liman.erobot.admin.service;

import cn.edu.csust.liman.erobot.admin.component.SenderManager;
import cn.edu.csust.liman.erobot.admin.component.TaskManager;
import cn.edu.csust.liman.erobot.admin.dao.TaskDao;
import cn.edu.csust.liman.erobot.admin.entity.Result;
import cn.edu.csust.liman.erobot.admin.entity.Sender;
import cn.edu.csust.liman.erobot.admin.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskService {
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private SenderManager senderManager;
    @Autowired
    private TaskManager taskManager;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@Validated Task task) {
        Integer senderId = task.getSenderId();
        Sender sender = senderManager.getById(senderId);
        if (sender == null || !sender.isAlive()) {
            return Result.err("sender not alive");
        }
        task.setSenderIp(sender.getAddr());
        task.setFailureTimes(0);
        taskDao.insert(task);
        taskDao.insertGroupInTask(task);
        taskManager.addTask(task.getId());
        return Result.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result delete(Long id) {
        taskDao.deleteByPrimaryKey(id);
        return Result.ok();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list() {
        List<Task> tasks = taskDao.selectAll();
        for (Task task : tasks) {
            Sender sender = senderManager.getByAddr(task.getSenderIp());
            if (sender == null) {
                task.setSenderId(null);
            } else {
                task.setSenderId(sender.getId());
            }
        }
        return Result.ok(tasks);
    }
}
