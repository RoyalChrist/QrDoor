package com.yan.doorcontrol;

import android.app.Application;

/**
 * Created by 95155 on 2016/4/9.
 */
public class MyApplication extends Application{

    private static MyApplication myApplication = null;

    private String _id;
    private String realName;
    private String loginName;
    private String password;
    private String mobilephone;
    private String IMEI;

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static void setMyApplication(MyApplication myApplication) {
        MyApplication.myApplication = myApplication;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }
}
