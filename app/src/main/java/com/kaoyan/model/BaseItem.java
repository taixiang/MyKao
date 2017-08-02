package com.kaoyan.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tx on 2017/7/25.
 */

public class BaseItem<T>  implements Parcelable{
    public String result;
    public T pros;

    protected BaseItem(Parcel in) {
        result = in.readString();
    }

    public static final Creator<BaseItem> CREATOR = new Creator<BaseItem>() {
        @Override
        public BaseItem createFromParcel(Parcel in) {
            return new BaseItem(in);
        }

        @Override
        public BaseItem[] newArray(int size) {
            return new BaseItem[size];
        }
    };

    @Override
    public String toString() {
        return "BaseItem{" +
                "result='" + result + '\'' +
                ", pros=" + pros +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(result);
    }
}
