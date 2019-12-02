package com.example.jxxy.mobileshop_dyd.http.entity;

import java.io.Serializable;
//HttpResult<T> 类，用以接收网络返回数据
public class HttpResult<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString(){
        return "HttpResult{"+
                "data="+data+
                ",status="+status+
                ",msg'"+msg+'\''+'}';
    }
}
