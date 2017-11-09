package com.aillience.enginee.net;


import retrofit2.HttpException;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 网络服务类
 */

public class NetWorkUtil {
    public static String analyzeNetworkError(Throwable e) {
        String errMsg = "加载失败";
        if (e instanceof HttpException) {
            int state = ((HttpException) e).code();
            if (state == 403) {
                errMsg = "请稍后再试";
            }
        }
        return errMsg;
    }

}
