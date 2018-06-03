package cn.edu.csust.liman.erobot.admin.component;

import cn.edu.csust.liman.erobot.admin.entity.Task;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class SenderConnection {

    private final static OkHttpClient HTTP_CLIENT = new OkHttpClient();
    private final static int PORT = 8082;

    public void sendTask(Map<String, Object> task) throws IOException {
        if (task == null) {
            return;
        }
        Map<String, Object> email = (Map<String, Object>) task.get("email");
        FormBody.Builder builder = new FormBody.Builder()
                .add("id", String.valueOf(task.get("id")))
                .add("name", (String) task.get("name"))
                .add("cron", (String) task.get("cron"))
                .add("email.subject", (String) email.get("subject"))
                .add("email.content", (String) email.get("content"))
                .add("email.receiver", (String) email.get("receiver"));
        String attachmentName = (String) email.get("attachmentName");
        if (attachmentName != null) {
            builder.add("email.attachmentName", (String) email.get("attachmentName"))
                    .add("email.attachmentPath", (String) email.get("attachmentPath"));
            sendAttachment((String) task.get("sender_ip"), (String) email.get("attachmentPath"));
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(String.format("http://%s:%d/task/receive", task.get("sender_ip"), PORT))
                .post(formBody)
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        response.close();
        if (response.code() != 200) {
            throw new IOException("task send error");
        }
    }

    public void deleteTask(Task task) throws IOException {
        final String url = String.format("http://%s:%d/task/delete?id=%d", task.getSenderIp(), PORT, task.getId());
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        int code = response.code();
        response.close();
        if (code != 200) {
            throw new IOException("heartbeat fail");
        }
    }

    public void sendAttachment(String senderIp, String attachmentPath) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("232"), new File("attachment/"+attachmentPath));
        RequestBody requestBody = new MultipartBody.Builder().addFormDataPart("file", attachmentPath, fileBody).build();

        Request requestPostFile = new Request.Builder()
                .url(String.format("http://%s:%d/task/attachment", senderIp, PORT))
                .post(requestBody)
                .build();
        try {
            Response response = HTTP_CLIENT.newCall(requestPostFile).execute();
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
