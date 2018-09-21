package com.zyjk.posmall.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Description：服务器返回结果定义协议
 *
 * Created by Sword God on 2018/9/6.
 */
public class HttpResult<T> implements Serializable {
    @SerializedName(value = "code", alternate = {"status", "page"})
    private int code;
    @SerializedName(value = "msg", alternate = {"message"})
    private String msg;
    @SerializedName(value = "data", alternate = {"subjects"})
    private T data;


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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
