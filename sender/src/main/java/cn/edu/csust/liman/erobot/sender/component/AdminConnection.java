package cn.edu.csust.liman.erobot.sender.component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdminConnection {
    private final static OkHttpClient HTTP_CLIENT = new OkHttpClient();

    @Value("${erobot.admin.ip}")
    private String adminAddr;


    @Value("${erobot.admin.port}")
    private String adminPort;

    private String protocol = "http";

    public void heartbeat() throws IOException {
        final String url = String.format("%s://%s:%s/sender/heartbeat", protocol, adminAddr, adminPort);
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        int code = response.code();
        if (code != 200) {
            throw new IOException("heartbeat fail");
        }
    }

    public void sendError() throws IOException {

        final String url = String.format("%s://%s:%s/sender/error", protocol, adminAddr, adminPort);
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        int code = response.code();
        if (code != 200) {
            throw new IOException("heartbeat fail");
        }
    }

}
