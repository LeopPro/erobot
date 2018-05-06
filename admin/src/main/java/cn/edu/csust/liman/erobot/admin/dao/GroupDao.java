package cn.edu.csust.liman.erobot.admin.dao;

import cn.edu.csust.liman.erobot.admin.entity.Group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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

    @Select({"select",
            "  id,",
            "  name,",
            "  count(id) as receiverNumber",
            "from e_group",
            "  inner join l_receiver_group g on e_group.id = g.group_id",
            "group by id"})
    List<Group> selectAllWithReceiverNumber();
}
