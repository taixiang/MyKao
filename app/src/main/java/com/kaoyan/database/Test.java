package com.kaoyan.database;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tx on 2017/7/26.
 */
@Entity
public class Test implements Parcelable{
    @Id
    private int id;
    private String test;

    protected Test(Parcel in) {
        id = in.readInt();
        test = in.readString();
    }

    @Generated(hash = 1798008441)
    public Test(int id, String test) {
        this.id = id;
        this.test = test;
    }

    @Generated(hash = 372557997)
    public Test() {
    }

    public static final Creator<Test> CREATOR = new Creator<Test>() {
        @Override
        public Test createFromParcel(Parcel in) {
            return new Test(in);
        }

        @Override
        public Test[] newArray(int size) {
            return new Test[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(test);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTest() {
        return this.test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
