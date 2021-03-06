package com.kaoyan.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tx on 2017/7/26.
 */

public class TestItem extends BaseItem{

    private String id;
    private String name;

    protected TestItem(Parcel in) {
        super(in);
    }

    @Override
    public String toString() {
        return "TestItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
