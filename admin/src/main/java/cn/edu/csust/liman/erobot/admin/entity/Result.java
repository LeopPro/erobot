package cn.edu.csust.liman.erobot.admin.entity;

public class Result {
    private int status;
    private Object result;
    private String errMsg;

    public static Result ok() {
        Result result = new Result();
        result.status = 200;
        return result;
    }

    public static Result ok(Object result) {
        Result r = new Result();
        r.status = 200;
        r.result = result;
        return r;
    }

    public static Result err(String message) {
        Result result = new Result();
        result.status = 400;
        result.errMsg = message;
        return result;
    }

    public int getStatus() {
        return status;
    }

    public Object getResult() {
        return result;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
