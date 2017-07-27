package com.kaoyan.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tx on 2017/7/25.
 */

public class BaseItem<T>  {
    public String result;
    public T pros;

    @Override
    public String toString() {
        return "BaseItem{" +
                "result='" + result + '\'' +
                ", pros=" + pros +
                '}';
    }
}
