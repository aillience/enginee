package com.aillience.enginee.mvp.model.entity;

/**
 * Happy every day.
 * Created by yfl on 2017/9/7 0007
 * explain: ZW登录用户实体
 *  {
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
 */

public class UserEntity {
    private int id;
    private int Deptid;
    private int AuditState;
    private String userName;
    private String password;
    private String phone;
    private String Head_portrait;
    private String nickname;
    private String oid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptid() {
        return Deptid;
    }

    public void setDeptid(int deptid) {
        Deptid = deptid;
    }

    public int getAuditState() {
        return AuditState;
    }

    public void setAuditState(int auditState) {
        AuditState = auditState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHead_portrait() {
        return Head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        Head_portrait = head_portrait;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
