package cn.edu.csust.liman.erobot.admin.service;

import cn.edu.csust.liman.erobot.admin.dao.GroupDao;
import cn.edu.csust.liman.erobot.admin.entity.Group;
import cn.edu.csust.liman.erobot.admin.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 发送组服务，提供对发送组操作的HTTP服务
 */
@RestController
@RequestMapping("/group")
public class GroupService {
    @Autowired
    private GroupDao groupDao;

    @PostMapping("/add")
    public Result add(@Validated Group group){
        groupDao.insert(group);
        groupDao.insertReceiverInGroup(group);
        return Result.ok();
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam("id") Long id){
        groupDao.deleteByPrimaryKey(id);
        return Result.ok();
    }

    @GetMapping("/list")
    public Result list(){
        return Result.ok(groupDao.selectAllWithReceiverNumber());
    }
}
