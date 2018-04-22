package cn.edu.csust.liman.erobot.sender.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobErrorHandler {
    public void handle(Exception e){
        System.out.println("出错啦");
    }
}
