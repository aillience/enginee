package com.aillience.enginee.mvp.model.bean;

import java.util.List;

/**
 * Happy every day.
 * Created by yfl on 2017/9/7 0007
 * explain: 基类
 */

 class BaseBean<T> {
    private int Status;
    private String Message;
    private List<T> Data;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<T> getData() {
        return Data;
    }

    public void setData(List<T> data) {
        Data = data;
    }
}
