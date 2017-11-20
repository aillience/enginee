package com.aillience.enginee.net;


import retrofit2.HttpException;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 网络服务类
 */

public class NetWorkUtil {
    /**
     * 解析网络错误
     * @param e 错误对象
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static String analyzeNetworkError(Throwable e) {
        String errMsg = "";
        if (e instanceof HttpException) {
            int state = ((HttpException) e).code();
            switch (state){
                case 403:
                    errMsg = "请稍后再试:"+state;
                    break;
                case 504:
                    errMsg = "网络请求错误："+state;
                    break;
                default:
                    errMsg ="加载失败:"+state;
                    break;
            }
        }
        return errMsg;
    }

}
