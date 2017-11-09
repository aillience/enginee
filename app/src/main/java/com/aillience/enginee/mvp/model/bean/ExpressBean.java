package com.aillience.enginee.mvp.model.bean;

import com.aillience.enginee.mvp.model.entity.ExpressEntity;

import java.util.List;

/**
 * Happy every day.
 * Created by yfl on 2017/9/13 0013
 * explain: 物流接口返回
 * {
 "message": "ok",
 "nu": "450920803566",
 "ischeck": "1",
 "condition": "F00",
 "com": "zhongtong",
 "status": "200",
 "state": "3",
 "data": [
 {
 "time": "2017-08-27 13:54:49",
 "ftime": "2017-08-27 13:54:49",
 "context": "[重庆市] [渝北二部]的派件已签收 感谢使用中通快递,期待再次为您服务!",
 "location": "渝北二部"
 },
 {
 "time": "2017-08-27 09:56:12",
 "ftime": "2017-08-27 09:56:12",
 "context": "[重庆市] [渝北二部]的李德林正在第1次派件 电话:13022397526 请保持电话畅通、耐心等待",
 "location": "渝北二部"
 },
 {
 "time": "2017-08-27 06:57:26",
 "ftime": "2017-08-27 06:57:26",
 "context": "[重庆市] 快件到达 [渝北二部]",
 "location": "渝北二部"
 },
 {
 "time": "2017-08-27 04:18:33",
 "ftime": "2017-08-27 04:18:33",
 "context": "[重庆市] 快件离开 [重庆]已发往[渝北二部]",
 "location": "重庆"
 },
 {
 "time": "2017-08-27 04:06:45",
 "ftime": "2017-08-27 04:06:45",
 "context": "[重庆市] 快件到达 [重庆]",
 "location": "重庆"
 },
 {
 "time": "2017-08-26 04:05:44",
 "ftime": "2017-08-26 04:05:44",
 "context": "[佛山市] 快件离开 [佛山中心]已发往[重庆]",
 "location": "佛山中心"
 },
 {
 "time": "2017-08-26 04:03:02",
 "ftime": "2017-08-26 04:03:02",
 "context": "[佛山市] 快件到达 [佛山中心]",
 "location": "佛山中心"
 },
 {
 "time": "2017-08-25 23:24:51",
 "ftime": "2017-08-25 23:24:51",
 "context": "[广州市] 快件离开 [番禺石基]已发往[重庆]",
 "location": "番禺石基"
 },
 {
 "time": "2017-08-25 20:38:24",
 "ftime": "2017-08-25 20:38:24",
 "context": "[广州市] [番禺石基]的直客纪先生已收件 电话:暂无",
 "location": "番禺石基"
 }
 ]
 }
 */

public class ExpressBean {
    private String message;
    private String nu;
    private String ischeck;
    private String condition;
    private String com;
    private String status;
    private String state;
    private List<ExpressEntity> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ExpressEntity> getData() {
        return data;
    }

    public void setData(List<ExpressEntity> data) {
        this.data = data;
    }
}
