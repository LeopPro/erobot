package cn.edu.csust.liman.erobot.admin.service;

import cn.edu.csust.liman.erobot.admin.dao.AccountDao;
import cn.edu.csust.liman.erobot.admin.entity.Account;
import cn.edu.csust.liman.erobot.admin.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginService {
    @Autowired
    private AccountDao accountDao;

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") byte[] password) {
        Account account = new Account();
        account.setUsername(username);
        account = accountDao.selectOne(account);
        if (account != null && account.verifyPassword(password)) {
            return Result.ok();
        } else {
            return Result.err("用户名或密码错误");
        }
    }

    @GetMapping("/logout")
    public Result logout() {
        return Result.ok();
    }
}
