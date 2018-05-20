package cn.edu.csust.liman.erobot.admin.service;

import cn.edu.csust.liman.erobot.admin.dao.ReceiverDao;
import cn.edu.csust.liman.erobot.admin.entity.Receiver;
import cn.edu.csust.liman.erobot.admin.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接收者服务，提供对发送者操作的HTTP服务
 */
@RestController
@RequestMapping("/receiver")
public class ReceiverService {
    @Autowired
    private ReceiverDao receiverDao;

    @PostMapping("/add")
    public Result add(@Validated Receiver receiver) {
        receiverDao.insert(receiver);
        return Result.ok();
    }

    @GetMapping("/list")
    public Result list() {
        return Result.ok(receiverDao.selectAll());
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam("id") long id) {
        System.out.println(id);
        receiverDao.deleteByPrimaryKey(id);
        return Result.ok();
    }

    @PostMapping("/set")
    public Result set(@Validated Receiver receiver) {
        if (receiver.getId() == null) {
            return Result.err("id is null");
        }
        receiverDao.updateByPrimaryKey(receiver);
        return Result.ok();
    }
}
