package cn.edu.csust.liman.erobot.admin.service;

import cn.edu.csust.liman.erobot.admin.component.SenderManager;
import cn.edu.csust.liman.erobot.admin.entity.Result;
import cn.edu.csust.liman.erobot.admin.entity.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sender")
public class SenderService {
    @Autowired
    private SenderManager senderManager;

    @GetMapping("/heartbeat")
    public Result heartbeat(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        senderManager.heartbeat(remoteAddr);
        return Result.ok();
    }
    @GetMapping("/error")
    public Result error(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        Sender sender = senderManager.getByAddr(remoteAddr);
        if(sender==null){
            senderManager.heartbeat(remoteAddr);
            sender = senderManager.getByAddr(remoteAddr);
        }
        sender.countError();
        return Result.ok();
    }

    @GetMapping("/list")
    public Result list(){
        return Result.ok(senderManager.list());
    }

    @GetMapping("/get")
    public Result get(@RequestParam("id") Integer id){
        return Result.ok(senderManager.getById(id));
    }


}
