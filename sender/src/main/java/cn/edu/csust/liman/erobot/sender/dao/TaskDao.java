package cn.edu.csust.liman.erobot.sender.dao;

import cn.edu.csust.liman.erobot.sender.entity.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaskDao {
    @Insert({"insert into ",
            "  e_task ",
            "  (id, name, cron, subject, content, receiver, attachment_name, attachment_path)",
            "VALUES ",
            "  (#{id}, #{name}, #{cron}, #{email.subject}, #{email.content}, #{email.receiver}, #{email.attachment_name}, #{email.attachment_path})"})
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
    Task select(@Param("id") long id);
}
