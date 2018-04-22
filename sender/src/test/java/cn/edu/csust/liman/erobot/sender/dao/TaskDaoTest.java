package cn.edu.csust.liman.erobot.sender.dao;

import cn.edu.csust.liman.erobot.sender.entity.Task;
import cn.edu.csust.liman.erobot.sender.entity.TaskTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
//@Rollback
public class TaskDaoTest {
    @Autowired
    private TaskDao taskDao;

    @Test
    public void insert() {
        Task task = TaskTest.buildTask();
        taskDao.insert(task);
    }

    @Test
    public void select() {
    }
}