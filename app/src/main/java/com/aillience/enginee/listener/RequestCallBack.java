package com.aillience.enginee.listener;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 网络请求回调监听
 */

public interface RequestCallBack<T> {
    void beforeRequest();

    void success(T data);

    void onError(String errorMsg);
}
