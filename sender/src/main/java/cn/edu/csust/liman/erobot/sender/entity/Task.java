package cn.edu.csust.liman.erobot.sender.entity;

import cn.edu.csust.liman.erobot.sender.component.TaskTimer;
import cn.edu.csust.liman.erobot.sender.util.BeanUtil;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Task {
    private long id;
    private String name;
    private String cron;
    private Email email;
    private static final BeanUtil.LazyLoader<TaskTimer> TASK_TIMER = BeanUtil.getLazyLoader(TaskTimer.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void start() {
        try {
            TASK_TIMER.get().start(this);
        } catch (SchedulerException e) {
            LOGGER.error("任务启动异常", e);
        }
    }

    public void shutdown() {
        try {
            TASK_TIMER.get().shutdown(this);
        } catch (SchedulerException e) {
            LOGGER.error("任务关闭异常", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(name, task.name) &&
                Objects.equals(cron, task.cron) &&
                Objects.equals(email, task.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, cron, email);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cron='" + cron + '\'' +
                ", email=" + email +
                '}';
    }
}
