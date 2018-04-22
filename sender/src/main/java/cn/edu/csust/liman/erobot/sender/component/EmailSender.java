package cn.edu.csust.liman.erobot.sender.component;

import cn.edu.csust.liman.erobot.sender.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void send(Email email) throws MessagingException {
        for (String receiver : email.getReceivers()) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(receiver);
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent());
            if (email.withAttachment()) {
                File file = new File(email.getAttachmentPath());
                helper.addAttachment(email.getAttachmentName(), file);
            }
            mailSender.send(message);
        }
    }
}
