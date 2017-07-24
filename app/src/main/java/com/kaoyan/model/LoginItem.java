package com.kaoyan.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/9.
 */
public class LoginItem implements Parcelable {
    public String result;
    public String id;
    public String group_id;
    public String user_name;
    public String real_name;
    public String nick_name;
    public String openid;
    public String amount;
    public String point;
    public String address_id;
    public String user_lvl;

    protected LoginItem(Parcel in) {
        result = in.readString();
        id = in.readString();
        group_id = in.readString();
        user_name = in.readString();
        real_name = in.readString();
        nick_name = in.readString();
        openid = in.readString();
        amount = in.readString();
        point = in.readString();
        address_id = in.readString();
        user_lvl = in.readString();
    }

    public static final Creator<LoginItem> CREATOR = new Creator<LoginItem>() {
        @Override
        public LoginItem createFromParcel(Parcel in) {
            return new LoginItem(in);
        }

        @Override
        public LoginItem[] newArray(int size) {
            return new LoginItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(result);
        dest.writeString(id);
        dest.writeString(group_id);
        dest.writeString(user_name);
        dest.writeString(real_name);
        dest.writeString(nick_name);
        dest.writeString(openid);
        dest.writeString(amount);
        dest.writeString(point);
        dest.writeString(address_id);
        dest.writeString(user_lvl);
    }
}
