package com.tpay.user.model;

import com.tpay.base.model.BaseModel;

import java.sql.Timestamp;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-03-26 11:05
 **/
public class SysUser extends BaseModel {

    private String userName;

    private String loginName;

    private String password;

    private String salt;

    private String realName;

    private String avatar;

    private String registerType;

    private String userType;

    private String phone;

    private String email;

    private Integer sex;

    private Integer locked;

    private Integer loginNum;

    private Timestamp loginTime;

    private Timestamp oldLoginTime;

    private String loginIp;

    private String oldLoginIp;

    private String status;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getOldLoginTime() {
        return oldLoginTime;
    }

    public void setOldLoginTime(Timestamp oldLoginTime) {
        this.oldLoginTime = oldLoginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getOldLoginIp() {
        return oldLoginIp;
    }

    public void setOldLoginIp(String oldLoginIp) {
        this.oldLoginIp = oldLoginIp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", realName='" + realName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", registerType='" + registerType + '\'' +
                ", userType='" + userType + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", locked=" + locked +
                ", loginNum=" + loginNum +
                ", loginTime=" + loginTime +
                ", oldLoginTime=" + oldLoginTime +
                ", loginIp='" + loginIp + '\'' +
                ", oldLoginIp='" + oldLoginIp + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
