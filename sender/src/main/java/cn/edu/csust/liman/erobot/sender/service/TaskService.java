package cn.edu.csust.liman.erobot.sender.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskService {
    public void attachment(){
        
    }

    @PostMapping("/add")
    public void add(){

    }

    @GetMapping("/delete")
    public void delete(){

    }

}
