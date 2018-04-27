package cn.edu.csust.liman.erobot.admin.dao;

import cn.edu.csust.liman.erobot.admin.entity.Group;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface GroupDao extends Mapper<Group> {

    @Insert({"insert into",
            "  l_receiver_group (receiver_id, group_id)",
            "values",
            "<foreach collection =\"receiverId\" item=\"receiverId\" separator =\",\">" ,
            "    (#{receiverId}, #{id})" ,
            "</foreach >"})
    void insertReceiverInGroup(Group group);
}
