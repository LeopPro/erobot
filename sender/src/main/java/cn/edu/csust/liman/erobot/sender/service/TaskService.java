package cn.edu.csust.liman.erobot.sender.service;

import cn.edu.csust.liman.erobot.sender.dao.TaskDao;
import cn.edu.csust.liman.erobot.sender.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

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

    @PostMapping("/attachment")
    public void attachment(@RequestParam("file") MultipartFile updateFile) throws IOException {
        File localFile = new File("sender-attachment/"+updateFile.getOriginalFilename());
        localFile.createNewFile();
        FileOutputStream localFileStream = new FileOutputStream(localFile);
        localFileStream.write(updateFile.getBytes());
        localFileStream.close();
    }

}
