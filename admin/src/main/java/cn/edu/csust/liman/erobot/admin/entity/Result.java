package cn.edu.csust.liman.erobot.admin.entity;

public class Result {
    private int status;
    private Object result;

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

    public int getStatus() {
        return status;
    }

    public Object getResult() {
        return result;
    }
}
