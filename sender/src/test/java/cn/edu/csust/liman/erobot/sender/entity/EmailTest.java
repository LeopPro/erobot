package cn.edu.csust.liman.erobot.sender.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {

    public static Email buildEmail(){
        Email email = new Email();
        email.setSubject("测试邮件");
        email.setContent("Hello World!");
        email.setReceiver("i@leop.pro kllso@qq.com 407057652@qq.com");
        email.setAttachmentName("测试附件.jpg");
        email.setAttachmentPath("test_attachment.png");
        return email;
    }

    @Test
    public void sendEmailTest() throws MessagingException, InterruptedException {
        buildEmail().send();
    }

}