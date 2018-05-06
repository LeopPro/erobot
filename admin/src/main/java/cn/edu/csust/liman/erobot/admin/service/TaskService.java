package cn.edu.csust.liman.erobot.admin.service;

import cn.edu.csust.liman.erobot.admin.dao.TaskDao;
import cn.edu.csust.liman.erobot.admin.entity.Result;
import cn.edu.csust.liman.erobot.admin.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskService {
    @Autowired
    private TaskDao taskDao;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@Validated Task task){
        task.setFailureTimes(0);
        taskDao.insert(task);
        return Result.ok();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result delete(Long id){
        taskDao.deleteByPrimaryKey(id);
        return Result.ok();
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result list(){
        return Result.ok(taskDao.selectAll());
    }
}
