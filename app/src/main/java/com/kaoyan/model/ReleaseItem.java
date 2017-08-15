package com.kaoyan.model;

import android.os.Parcel;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ReleaseItem extends BaseItem {
    public String message;


    protected ReleaseItem(Parcel in) {
        super(in);
    }

    @Override
    public String toString() {
        return "ReleaseItem{" +
                "message='" + message + '\'' +
                '}';
    }
}
