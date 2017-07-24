package com.kaoyan.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tx on 2017/7/24.
 */

public class LoginBean implements Parcelable{
    public String name;
    public String password;
    public String token;

    public LoginBean(String name, String password, String token) {
        this.name = name;
        this.password = password;
        this.token = token;
    }

    protected LoginBean(Parcel in) {
        name = in.readString();
        password = in.readString();
        token = in.readString();
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel in) {
            return new LoginBean(in);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(token);
    }
}
