package cn.edu.csust.liman.erobot.sender.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@EnableScheduling
public class HeartbeatTimer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatTimer.class);
    @Autowired
    private AdminConnection adminConnection;

    @Scheduled(fixedRate = 1000 * 60)
    public void sendHeartbeat() {
        try {
            adminConnection.heartbeat();
        } catch (IOException e) {
            LOGGER.error("发送心跳包失败", e);
        }
    }
}
