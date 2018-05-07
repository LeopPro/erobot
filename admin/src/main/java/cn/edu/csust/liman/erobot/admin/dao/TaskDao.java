package cn.edu.csust.liman.erobot.admin.dao;

import cn.edu.csust.liman.erobot.admin.entity.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface TaskDao extends Mapper<Task> {
    @Insert({"<script>",
            "insert into",
            "  l_group_task (group_id, task_id)",
            "values",
            "<foreach collection =\"groupId\" item=\"item\" separator =\",\">",
            "    (#{item}, #{id})",
            "</foreach >",
            "</script>"})
    void insertGroupInTask(Task task);

    @Select({"select",
            "  id,",
            "  name,",
            "  cron,",
            "  sender_ip,",
            "  subject                           as 'email.subject',",
            "  content                           as 'email.content',",
            "  attachment_name                   as 'email.attachment_name',",
            "  attachment_path                   as 'email.attachment_path',",
            "  GROUP_CONCAT(email separator ' ') as 'email.receiver'",
            "from (",
            "       select",
            "         e_task.id,",
            "         e_task.name,",
            "         e_task.cron,",
            "         e_task.sender_ip,",
            "         e_message.subject,",
            "         e_message.content,",
            "         e_message.attachment_name,",
            "         e_message.attachment_path,",
            "         e_receiver.email",
            "       from e_task",
            "         inner join e_message on e_task.message_id = e_message.id",
            "         inner join l_group_task on e_task.id = l_group_task.task_id",
            "         inner join l_receiver_group on l_group_task.group_id = l_receiver_group.group_id",
            "         inner join e_receiver on l_receiver_group.receiver_id = e_receiver.id",
            "       where e_task.id = #{id}",
            "       group by e_receiver.id) as a"})
    Map<String, Object> getExecutableTask(@Param("id") Long id);


}
