package cn.edu.csust.liman.erobot.admin.dao;

import cn.edu.csust.liman.erobot.admin.entity.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
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
            "  attachment_name                   as 'email.attachmentName',",
            "  attachment_path                   as 'email.attachmentPath',",
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

    @Update({"<script>",
            "delete from l_group_task",
            "where task_id = #{id}",
            "      and group_id not in",
            "(",
            "<foreach collection =\"groupId\" item=\"item\" separator =\",\">",
            "    #{item}",
            "</foreach >",
            ");",
            "insert ignore into l_group_task (group_id, task_id) VALUES",
            "<foreach collection =\"groupId\" item=\"item\" separator =\",\">",
            "    (#{item}, #{id})",
            "</foreach >;",
            "</script>"})
    void updateGroupInTask(Task task);

    @Select({"select group_id from l_group_task where task_id = #{taskId}"})
    Long[] selectAllGroupIdByTaskId(long id);


    @Select({" select",
            "         e_task.id,",
            "         e_task.name,",
            "         e_task.cron,",
            "         e_task.sender_ip,",
            "         e_message.subject,",
            "         e_message.content,",
            "         e_message.attachment_name,",
            "         e_message.attachment_path,",
            "         GROUP_CONCAT(email separator ' ') as 'email.receiver'",
            "       from e_task",
            "         inner join e_message on e_task.message_id = e_message.id",
            "         inner join l_group_task on e_task.id = l_group_task.task_id",
            "         inner join l_receiver_group on l_group_task.group_id = l_receiver_group.group_id",
            "         inner join e_receiver on l_receiver_group.receiver_id = e_receiver.id",
            "     group by id"})
    List<Map<String, Object>> selectAllExecutableTask();
}
