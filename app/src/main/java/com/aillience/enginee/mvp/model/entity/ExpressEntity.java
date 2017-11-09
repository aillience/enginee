package com.aillience.enginee.mvp.model.entity;

/**
 * Happy every day.
 * Created by yfl on 2017/9/13 0013
 * explain: 物流信息对象
 *  {
 "time": "2017-08-27 13:54:49",
 "ftime": "2017-08-27 13:54:49",
 "context": "[重庆市] [渝北二部]的派件已签收 感谢使用中通快递,期待再次为您服务!",
 "location": "渝北二部"
 }
 */

public class ExpressEntity {
    private String time;
    private String ftime;
    private String context;
    private String location;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
