package com.aillience.enginee.mvp.model.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Happy every day.
 * Created by yfl on 2017/9/7 0007
 * explain: 基类
 */

 class BaseBean<T> implements Serializable {

    @SerializedName(value = "code",alternate ={"retCode"})
    private int code;
    @SerializedName(value = "msg")
    private String msg;
    @SerializedName(value = "success")
    private boolean success;
    @SerializedName(value = "totalCount")
    private int totalCount;
    @SerializedName(value = "data",alternate ={"result"})
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
