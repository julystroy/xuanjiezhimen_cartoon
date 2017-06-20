package com.jssj.jiangye.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cc on 17-6-2.
 */
public class UserInfo implements Parcelable {
    /*
    * "id"//用户id,
       "mobile"//手机号码,
       "nickName"//昵称,
       "sex"//性别,
       "birthday"//出生日期
       "token"//token,
       "uniqueId"//设备唯一标识ID,
       "deviceType"//设备类型：安卓设备传值'ANDROID'，IOS设置传值'IOS',
       "alias"//别名，默认用户名
       "userName"//用户名
       "clientOs"//请求操作系统
       "loginTime"//登录时间
       "portraitUrl"//用户头像
       "isLogin"//是否登录
    */
    private String id;
    private String mobile;
    private String nickName;
    private String sex;
    private String birthday;
    private String token;
    private String uniqueId;
    private String deviceType;
    private String userName;
    private String alias;
    private String clientOs;
    private String loginTime;
    private String portraitUrl;
    private String isLogin;

    public UserInfo(){}
    protected UserInfo(Parcel in) {
        id = in.readString();
        mobile = in.readString();
        nickName = in.readString();
        sex = in.readString();
        birthday = in.readString();
        token = in.readString();
        uniqueId = in.readString();
        deviceType = in.readString();
        userName = in.readString();
        alias = in.readString();
        clientOs = in.readString();
        loginTime = in.readString();
        portraitUrl = in.readString();
        isLogin = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getClientOs() {
        return clientOs;
    }

    public void setClientOs(String clientOs) {
        this.clientOs = clientOs;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String isLogin() {
        return isLogin;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", token='" + token + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", userName='" + userName + '\'' +
                ", alias='" + alias + '\'' +
                ", clientOs='" + clientOs + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", portraitUrl='" + portraitUrl + '\'' +
                ", isLogin=" + isLogin +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(mobile);
        parcel.writeString(nickName);
        parcel.writeString(sex);
        parcel.writeString(birthday);
        parcel.writeString(token);
        parcel.writeString(uniqueId);
        parcel.writeString(deviceType);
        parcel.writeString(userName);
        parcel.writeString(alias);
        parcel.writeString(clientOs);
        parcel.writeString(loginTime);
        parcel.writeString(portraitUrl);
        parcel.writeString(isLogin);
    }
}
