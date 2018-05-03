package cn.edu.csust.liman.erobot.admin.dao;

import cn.edu.csust.liman.erobot.admin.entity.Group;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface GroupDao extends Mapper<Group> {

    @Insert({"<script>",
            "insert into",
            "  l_receiver_group (receiver_id, group_id)",
            "values",
            "<foreach collection =\"receiverId\" item=\"item\" separator =\",\">",
            "    (#{item}, #{id})",
            "</foreach >",
            "</script>"})
    void insertReceiverInGroup(Group group);
}
