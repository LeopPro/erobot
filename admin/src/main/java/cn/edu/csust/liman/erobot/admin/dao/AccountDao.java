package cn.edu.csust.liman.erobot.admin.dao;

import cn.edu.csust.liman.erobot.admin.entity.Account;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AccountDao extends Mapper<Account> {

    @Select("select id,username from e_account")
    List<Account> selectWithOutPassword();
}
