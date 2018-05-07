package cn.edu.csust.liman.erobot.admin.entity;

import cn.edu.csust.liman.erobot.admin.validator.Cron;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.Objects;

@Table(name = "e_task")
public class Task {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Long messageId;
    @Cron
    private String cron;
    @NotNull
    @Transient
    private Integer senderId;
    @Null
    private String senderIp;
    @NotNull
    private Long[] groupId;
    @Null
    private Integer failureTimes;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", messageId=" + messageId +
                ", cron='" + cron + '\'' +
                ", senderId=" + senderId +
                ", groupId=" + Arrays.toString(groupId) +
                ", failureTimes=" + failureTimes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(name, task.name) &&
                Objects.equals(messageId, task.messageId) &&
                Objects.equals(cron, task.cron) &&
                Objects.equals(senderId, task.senderId) &&
                Arrays.equals(groupId, task.groupId) &&
                Objects.equals(failureTimes, task.failureTimes);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, name, messageId, cron, senderId, failureTimes);
        result = 31 * result + Arrays.hashCode(groupId);
        return result;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Long[] getGroupId() {
        return groupId;
    }

    public void setGroupId(Long[] groupId) {
        this.groupId = groupId;
    }

    public Integer getFailureTimes() {
        return failureTimes;
    }

    public void setFailureTimes(Integer failureTimes) {
        this.failureTimes = failureTimes;
    }

    public String getSenderIp() {
        return senderIp;
    }

    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }
}
