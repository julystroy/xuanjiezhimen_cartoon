package com.jssj.jiangye.data;

/**
 * Created by cc on 17-6-2.
 */
public class ImagCode {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ImagCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
