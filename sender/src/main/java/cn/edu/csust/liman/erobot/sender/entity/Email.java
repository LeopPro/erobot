package cn.edu.csust.liman.erobot.sender.entity;

import cn.edu.csust.liman.erobot.sender.component.EmailSender;
import cn.edu.csust.liman.erobot.sender.util.BeanUtil;

import javax.mail.MessagingException;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Objects;

public class Email {

    private String[] receivers;
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
    private String attachmentPath;
    private String attachmentName;
    private static final BeanUtil.LazyLoader<EmailSender> EMAIL_SENDER = BeanUtil.getLazyLoader(EmailSender.class);

    public String[] getReceivers() {
        return receivers;
    }

    public String getReceiver() {
        StringBuilder sb = new StringBuilder();
        for (String receiver : receivers) {
            sb.append(receiver);
            sb.append(' ');
        }
        return sb.toString();
    }

    public void setReceivers(String[] receivers) {
        this.receivers = receivers;
    }

    public void setReceiver(String receiver) {
        this.receivers = receiver.split(" ");
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

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public boolean withAttachment() {
        return attachmentPath != null;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Arrays.equals(receivers, email.receivers) &&
                Objects.equals(subject, email.subject) &&
                Objects.equals(content, email.content) &&
                Objects.equals(attachmentPath, email.attachmentPath) &&
                Objects.equals(attachmentName, email.attachmentName);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(subject, content, attachmentPath, attachmentName);
        result = 31 * result + Arrays.hashCode(receivers);
        return result;
    }

    @Override
    public String toString() {
        return "Email{" +
                "receivers=" + Arrays.toString(receivers) +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", attachmentPath='" + attachmentPath + '\'' +
                ", attachmentName='" + attachmentName + '\'' +
                '}';
    }

    public void send() throws MessagingException {
        EMAIL_SENDER.get().send(this);
    }
}
