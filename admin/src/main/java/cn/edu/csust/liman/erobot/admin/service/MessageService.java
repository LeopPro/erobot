package cn.edu.csust.liman.erobot.admin.service;

import cn.edu.csust.liman.erobot.admin.entity.Message;
import cn.edu.csust.liman.erobot.admin.entity.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageService {
    public Result add(@Validated Message message){

        return Result.ok();
    }


}
