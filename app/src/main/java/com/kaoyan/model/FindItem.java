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

    public static class Find extends BaseItem {
        public String user_name;
        public String university;
        public String pro_id;
        public String ISBN;
        public String pro_name;
        public String price_sell;
        public String image_url;

        protected Find(Parcel in) {
            super(in);
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
    }

    @Override
    public String toString() {
        return "FindItem{" +
                "result='" + result + '\'' +
                ", pros=" + pros +
                '}';
    }
}
