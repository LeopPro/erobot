package cn.edu.csust.liman.erobot.admin.dao;

import cn.edu.csust.liman.erobot.admin.entity.Group;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;

import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class GroupDaoTest {
    @Autowired
    private GroupDao groupDao;

//    @Test
    public void test() {
        Group group = new Group();
        group.setName("哈哈哈");
//        groupDao.insert(group);
        group.setId(1L);
        group.setReceiverId(new Long[]{ 3L, 6L, 36L});
        groupDao.insertReceiverInGroup(group);
    }

}