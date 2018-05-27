package cn.edu.csust.liman.erobot.admin.service;

import cn.edu.csust.liman.erobot.admin.dao.AccountDao;
import cn.edu.csust.liman.erobot.admin.entity.Account;
import cn.edu.csust.liman.erobot.admin.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    @PostMapping("/add")
    public Result add(@RequestParam("username") String username,
                      @RequestParam("password") byte[] psaaword) {
        Account account = new Account();
        account.setUsername(username);
        account.setPlaintextOfPassword(psaaword);
        int change = accountDao.insert(account);
        if (change == 0) {
            return Result.err("用户已存在");
        }
        return Result.ok();
    }

    @PostMapping("/set")
    public Result set(@RequestParam("id") Long id,
                      @RequestParam("username") String username,
                      @RequestParam("password") byte[] psaaword) {
        Account account = new Account();
        account.setId(id);
        account.setUsername(username);
        if (psaaword.length > 0) {
            account.setPlaintextOfPassword(psaaword);
        }
        int change = accountDao.updateByPrimaryKeySelective(account);
        if (change == 0) {
            return Result.err("用户已存在");
        }
        return Result.ok();
    }

    @GetMapping("/delete")
    public Result delete(@RequestParam("id") Long id) {
        accountDao.deleteByPrimaryKey(id);
        return Result.ok();
    }

    @GetMapping("/list")
    public Result list() {
        return Result.ok(accountDao.selectWithOutPassword());
    }


}
