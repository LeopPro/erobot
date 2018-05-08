package cn.edu.csust.liman.erobot.admin.component;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class SenderConnection {

    private final static OkHttpClient HTTP_CLIENT = new OkHttpClient();
    private final static int PORT = 8082;

    public void sendTask(Map<String, Object> task) throws IOException {
        Map<String, Object> email = (Map<String, Object>) task.get("email");
        FormBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(task.get("id")))
                .add("name", (String) task.get("name"))
                .add("cron", (String) task.get("cron"))
                .add("email.subject", (String) email.get("subject"))
                .add("email.content", (String) email.get("content"))
                .add("email.receiver", (String) email.get("receiver"))
//                .add("email.attachmentName", (String) email.get("attachmentName"))
//                .add("email.attachmentPath", (String) email.get("attachmentPath"))
                .build();
        Request request = new Request.Builder()
                .url(String.format("http://%s:%d/task/add", task.get("sender_ip"), PORT))
                .post(formBody)
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        if (response.code() != 200) {
            throw new IOException("task send error");
        }
    }
}
