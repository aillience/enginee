package com.aillience.enginee.mvp.model.entity;

import java.io.Serializable;

/**
 * Happy every day.
 * Created by yfl on 2017/9/7 0007
 * explain: ZW登录用户实体
 *
 */

public class UserEntity implements Serializable {
    //code= 402，则代表QQ微信第一次登录
    private int code;
    //唯一令牌
    private String token;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 主键
     */
    private int custId;
    /**
     * 客户唯一标识
     */
    private String customerCode;
    /**
     * 客户电话
     */
    private String customerPhone;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 支付密码
     */
    private String payWord;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 生日
     */
    private long birthday;
    /**
     * 性别
     */
    private int sex;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像存放地址
     */
    private String headImgUrl;
    //QQ openid
    private String qqOpenid;
    //微信 openid
    private String wxOpenid;
    /**
     * 是否会员
     */
    private int isMember;
    /**
     * 会员等级
     */
    private int memberLevel;
    /**
     * 会员等级描述
     */
    private String memberDesc;
    /**
     * 录入时间
     */
    private long createTime;
    /**
     * 状态
     */
    private int delFlag;


    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayWord() {
        return payWord;
    }

    public void setPayWord(String payWord) {
        this.payWord = payWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public int getIsMember() {
        return isMember;
    }

    public void setIsMember(int isMember) {
        this.isMember = isMember;
    }

    public int getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(int memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMemberDesc() {
        return memberDesc;
    }

    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
