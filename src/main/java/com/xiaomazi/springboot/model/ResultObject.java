package com.xiaomazi.springboot.model;

/**
 * @program: 15-springboot-Advanced
 * @description
 * @author: XiaoMaZi
 * @create: 2021-05-08 21:50
 **/
public class ResultObject<T> {

    private int statusCode;

    private String statusMessage;

    private T data;

    public ResultObject() {}

    public ResultObject(int statusCode, String statusMessage, T data) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
