package cn.edu.csust.liman.erobot.admin.component;

import cn.edu.csust.liman.erobot.admin.entity.Sender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SenderManager {
    private static List<Sender> senderList = new CopyOnWriteArrayList<>();
    private static Map<String, Sender> senderMap = new ConcurrentHashMap<>();

    public void heartbeat(String remoteAddr) {
        Sender sender = senderMap.get(remoteAddr);
        if (sender == null) {
            synchronized (SenderManager.class) {
                sender = senderMap.get(remoteAddr);
                if (sender == null) {
                    sender = new Sender(remoteAddr);
                    senderList.add(sender);
                    sender.setId(senderList.size() - 1);
                    senderMap.put(remoteAddr, sender);
                }
            }
        }
        sender.heartbeat();
    }

    public Sender getById(int id) {
        try {
            return senderList.get(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    public Sender getByAddr(String addr){
        return senderMap.get(addr);
    }

    public List<Sender> list() {
        return new ArrayList<>(senderList);
    }
}
