package cn.edu.csust.liman.erobot.sender.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JobErrorHandler {
    @Autowired
    private AdminConnection adminConnection;
    public void handle(Exception e){
        try {
            adminConnection.sendError();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
