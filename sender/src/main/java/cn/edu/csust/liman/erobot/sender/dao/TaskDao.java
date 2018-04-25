package cn.edu.csust.liman.erobot.sender.dao;

import cn.edu.csust.liman.erobot.sender.entity.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskDao {
    @Insert({"insert into ",
            "  e_task ",
            "  (id, name, cron, subject, content, receiver, attachment_name, attachment_path)",
            "VALUES ",
            "  (#{id}, #{name}, #{cron}, #{email.subject}, #{email.content}, #{email.receiver}, #{email.attachmentName}, #{email.attachmentPath})"})
    void insert(Task task);

    @Select({"select",
            "  id,",
            "  name,",
            "  cron,",
            "  subject,",
            "  content,",
            "  receiver,",
            "  attachment_name,",
            "  attachment_path",
            "from e_task",
            "where id = #{id}"})
    @Results({@Result(column = "subject", property = "email.subject"),
            @Result(column = "content", property = "email.content"),
            @Result(column = "receiver", property = "email.receiver"),
            @Result(column = "attachment_name", property = "email.attachmentName"),
            @Result(column = "attachment_path", property = "email.attachmentPath")})
    Task select(@Param("id") long id);

    @Select({"select",
            "  id,",
            "  name,",
            "  cron,",
            "  subject,",
            "  content,",
            "  receiver,",
            "  attachment_name,",
            "  attachment_path",
            "from e_task"})
    @Results({@Result(column = "subject", property = "email.subject"),
            @Result(column = "content", property = "email.content"),
            @Result(column = "receiver", property = "email.receiver"),
            @Result(column = "attachment_name", property = "email.attachmentName"),
            @Result(column = "attachment_path", property = "email.attachmentPath")})
    List<Task> listAll();
}
