package cn.edu.csust.liman.erobot.admin.component;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SenderConnection {

    private final static OkHttpClient HTTP_CLIENT = new OkHttpClient();
    public void sendTask(Map<String,Object> task){
        //{cron=15 * * * * ? *, name=taaa, sender_ip=127.0.0.1, id=9, email={receiver=cc@email.com aa@email.com, subject=mbb, content=bbccddee}}
        Map<String,Object> email = (Map<String, Object>) task.get("email");
        FormBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(task.get("id")))
                .add("name", (String) task.get("name"))
                .add("cron", (String) task.get("cron"))
                .add("email.subject", (String) email.get("subject"))
                .add("email.content", (String) email.get("content"))
                .add("email.receiver", (String) email.get("receiver"))
                .build();
        Request request = new Request.Builder()
                .url("http:www.baidu.com")
                .post(formBody)
                .build();
    }
}
