package cn.edu.csust.liman.erobot.admin.service;

import cn.edu.csust.liman.erobot.admin.dao.MessageDao;
import cn.edu.csust.liman.erobot.admin.entity.Message;
import cn.edu.csust.liman.erobot.admin.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageService {
    @Autowired
    private MessageDao messageDao;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(@Validated Message message){
        messageDao.insert(message);
        return Result.ok();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result delete(@RequestParam("id") Long id){
        messageDao.deleteByPrimaryKey(id);
        return Result.ok();
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result list(){
        return Result.ok(messageDao.selectAll());
    }



}
