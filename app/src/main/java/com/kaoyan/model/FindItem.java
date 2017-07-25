package com.kaoyan.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by tx on 2017/6/11.
 * user_name: "soul臧",
 university: "",
 pro_id: 170516150208134,
 ISBN: null,
 pro_name: "正版 药理学——第七版/本科学 朱依谆等 人民卫生出版社 97871171",
 price_sell: 19,
 image_url: "https://img.ali
 */

public class FindItem extends BaseItem {

    public List<Find> pros;

    protected FindItem(Parcel in) {
        super(in);
    }

    public static class Find implements Parcelable {
        public String user_name;
        public String university;
        public String pro_id;
        public String ISBN;
        public String pro_name;
        public String price_sell;
        public String image_url;

        protected Find(Parcel in) {
            user_name = in.readString();
            university = in.readString();
            pro_id = in.readString();
            ISBN = in.readString();
            pro_name = in.readString();
            price_sell = in.readString();
            image_url = in.readString();
        }

        @Override
        public String toString() {
            return "Find{" +
                    "user_name='" + user_name + '\'' +
                    ", university='" + university + '\'' +
                    ", pro_id='" + pro_id + '\'' +
                    ", ISBN='" + ISBN + '\'' +
                    ", pro_name='" + pro_name + '\'' +
                    ", price_sell='" + price_sell + '\'' +
                    ", image_url='" + image_url + '\'' +
                    '}';
        }

        public static final Parcelable.Creator<Find> CREATOR = new Creator<Find>() {
            @Override
            public Find createFromParcel(Parcel in) {
                return new Find(in);
            }

            @Override
            public Find[] newArray(int size) {
                return new Find[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(user_name);
            dest.writeString(university);
            dest.writeString(pro_id);
            dest.writeString(ISBN);
            dest.writeString(pro_name);
            dest.writeString(price_sell);
            dest.writeString(image_url);
        }
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(result);
        dest.writeTypedList(pros);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FindItem> CREATOR = new Creator<FindItem>() {
        @Override
        public FindItem createFromParcel(Parcel in) {
            return new FindItem(in);
        }

        @Override
        public FindItem[] newArray(int size) {
            return new FindItem[size];
        }
    };

    @Override
    public String toString() {
        return "FindItem{" +
                "result='" + result + '\'' +
                ", pros=" + pros +
                '}';
    }
}
