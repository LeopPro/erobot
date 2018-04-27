package cn.edu.csust.liman.erobot.admin.dao;

import cn.edu.csust.liman.erobot.admin.entity.Receiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiverDaoTest {
    @Autowired
    private ReceiverDao receiverDao;
    @Test
    public void test(){
        Receiver receiver = new Receiver();
        receiver.setEmail("i@leop.por");
        receiver.setName("你好");
        receiverDao.insert(receiver);
    }
}