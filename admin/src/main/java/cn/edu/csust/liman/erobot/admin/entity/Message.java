package cn.edu.csust.liman.erobot.admin.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Table(name = "e_message")
public class Message {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
    private String attachmentName;
    private String attachmentPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(subject, message.subject) &&
                Objects.equals(content, message.content) &&
                Objects.equals(attachmentName, message.attachmentName) &&
                Objects.equals(attachmentPath, message.attachmentPath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, subject, content, attachmentName, attachmentPath);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", attachmentName='" + attachmentName + '\'' +
                ", attachmentPath='" + attachmentPath + '\'' +
                '}';
    }
}
