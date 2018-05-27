package cn.edu.csust.liman.erobot.sender.service;

import cn.edu.csust.liman.erobot.sender.dao.TaskDao;
import cn.edu.csust.liman.erobot.sender.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskService {
    @Autowired
    private TaskDao taskDao;
    public void attachment(){
        
    }

    @PostMapping("/receive")
    public void receive(@Validated Task task){
        Task taskFromDb = taskDao.getById(task.getId());
        if(taskFromDb != null){
            taskDao.update(task);
            task.shutdown();
        }else{
            taskDao.insert(task);
        }
        task.start();
    }

    @GetMapping("/delete")
    public void delete(long id){
        Task task = new Task();
        task.setId(id);
        task.shutdown();
        taskDao.delete(id);
    }

}
