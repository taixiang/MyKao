package com.kaoyan.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by tx on 2017/6/8.
 */

public class HomeMiddleItem implements Parcelable {

    public boolean result;
    public List<HomeMiddle> data;

    protected HomeMiddleItem(Parcel in) {
        result = in.readByte() != 0;
        data = in.createTypedArrayList(HomeMiddle.CREATOR);
    }

    public static final Creator<HomeMiddleItem> CREATOR = new Creator<HomeMiddleItem>() {
        @Override
        public HomeMiddleItem createFromParcel(Parcel in) {
            return new HomeMiddleItem(in);
        }

        @Override
        public HomeMiddleItem[] newArray(int size) {
            return new HomeMiddleItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (result ? 1 : 0));
        dest.writeTypedList(data);
    }

    public static class HomeMiddle implements Parcelable {
        public String keywords;
        public String image_url;
        public String url;

        @Override
        public String toString() {
            return "HomeMiddle{" +
                    "keywords='" + keywords + '\'' +
                    ", image_url='" + image_url + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        protected HomeMiddle(Parcel in) {
            keywords = in.readString();
            image_url = in.readString();
            url = in.readString();
        }

        public static final Creator<HomeMiddle> CREATOR = new Creator<HomeMiddle>() {
            @Override
            public HomeMiddle createFromParcel(Parcel in) {
                return new HomeMiddle(in);
            }

            @Override
            public HomeMiddle[] newArray(int size) {
                return new HomeMiddle[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(keywords);
            dest.writeString(image_url);
            dest.writeString(url);
        }
    }

    @Override
    public String toString() {
        return "HomeMiddleItem{" +
                "result=" + result +
                ", data=" + data +
                '}';
    }
}
