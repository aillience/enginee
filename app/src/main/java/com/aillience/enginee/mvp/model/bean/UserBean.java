package com.aillience.enginee.mvp.model.bean;

import com.aillience.enginee.mvp.model.entity.UserEntity;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 个人信息
 * {
 "Status": 1,
 "Message": "登陆成功!",
 "Key": "21218CCA77804D2BA1922C33E0151105",
 "shzt": 0,
 "TVInfoId": 2,
 "Data": [
 {
 "id": 30,
 "userName": "aillience",
 "password": "123456",
 "phone": "18267788980",
 "Deptid": 851,
 "Head_portrait": "",
 "nickname": "",
 "AuditState": 1,
 "oid": ""
 }
 ]
 }
 */

public class UserBean extends BaseBean<UserEntity> {
    private String Key;
    private int shzt;
    private int TVInfoId;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public int getShzt() {
        return shzt;
    }

    public void setShzt(int shzt) {
        this.shzt = shzt;
    }

    public int getTVInfoId() {
        return TVInfoId;
    }

    public void setTVInfoId(int TVInfoId) {
        this.TVInfoId = TVInfoId;
    }
}
