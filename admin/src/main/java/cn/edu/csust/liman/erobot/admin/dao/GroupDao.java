package cn.edu.csust.liman.erobot.admin.dao;

import cn.edu.csust.liman.erobot.admin.entity.Group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
            "  count(receiver_id) as receiverNumber",
            "from e_group",
            "  left join l_receiver_group g on e_group.id = g.group_id",
            "group by id"})
    List<Group> selectAllWithReceiverNumber();

    @Update({"<script>",
            "delete from l_receiver_group",
            "where group_id = #{id}",
            "      and receiver_id not in",
            "(",
            "<foreach collection =\"receiverId\" item=\"item\" separator =\",\">",
            "    #{item}",
            "</foreach >",
            ");",
            "insert ignore into l_receiver_group (receiver_id, group_id) VALUES",
            "<foreach collection =\"receiverId\" item=\"item\" separator =\",\">",
            "    (#{item}, #{id})",
            "</foreach >;",
            "</script>"})
    void updateReceiverInGroup(Group group);

    @Select({"select receiver_id from l_receiver_group where group_id = #{groupId}"})
    Long[] selectAllReceiverIdByGroupId(@Param("groupId") Long groupId);

}
