package cn.edu.csust.liman.erobot.admin.entity;

public class Sender {
    /**
     * 超时时间：10 分钟
     */
    private static final long TIME_OUT = 10 * 60 * 1000;
    private Integer id;
    private String addr;
    private long heartbeatTime;
    private Integer errorNumber = 0;

    public Sender(String remoteAddr) {
        this.addr = remoteAddr;
        this.heartbeat();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void heartbeat() {
        this.heartbeatTime = System.currentTimeMillis();
    }

    public boolean isAlive() {
        return System.currentTimeMillis() - this.heartbeatTime < TIME_OUT;
    }

    public void countError() {
        errorNumber++;
    }

    public Integer getErrorNumber() {
        return errorNumber;
    }
}
