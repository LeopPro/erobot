package cn.edu.csust.liman.erobot.admin.service;

import cn.edu.csust.liman.erobot.admin.component.TaskManager;
import cn.edu.csust.liman.erobot.admin.dao.MessageDao;
import cn.edu.csust.liman.erobot.admin.entity.Message;
import cn.edu.csust.liman.erobot.admin.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private TaskManager taskManager;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@Validated Message message) {
        messageDao.insert(message);
        return Result.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result delete(@RequestParam("id") Long id) {
        messageDao.deleteByPrimaryKey(id);
        taskManager.change();
        return Result.ok();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list() {
        return Result.ok(messageDao.selectAll());
    }

    @PostMapping("/set")
    public Result set(@Validated Message message) {
        if (message.getId() == null) {
            return Result.err("id is null");
        }
        messageDao.updateByPrimaryKey(message);
        taskManager.change();
        return Result.ok();
    }

    @PostMapping("/attachment")
    public Result attachment(@RequestParam("file") CommonsMultipartFile updateFile) throws IOException {
        String randomFileName = UUID.randomUUID().toString();
        File localFile = new File("attachment/" + randomFileName);
        FileOutputStream localFileStream = new FileOutputStream(localFile);
        localFileStream.write(updateFile.getBytes());
        localFileStream.close();
        return Result.ok(randomFileName);
    }

}
