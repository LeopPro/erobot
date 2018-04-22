package cn.edu.csust.liman.erobot.sender.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {
    public static Task buildTask(){
        Task task = new Task();
        task.setId(1);
        task.setEmail(EmailTest.buildEmail());
        task.setName("哈哈哈");
        task.setCron("0/10 * * * * ? ");
        System.out.println(task);
        return task;
    }

    @Test
    public void tastStartTest() throws SchedulerException, InterruptedException {
        Task task = buildTask();
        task.start();
        Thread.sleep(15*1000);
        task.shutdown();
        Thread.sleep(15*1000);
    }
}