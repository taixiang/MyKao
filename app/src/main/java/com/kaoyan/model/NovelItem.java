package com.kaoyan.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by tx on 2017/6/15.
 id: 1100877128,
 name: "爱他，就去追 (韩)徐亨周 ,李小华 9787221067029",
 ISBN: "9787221067029",
 price_market: 19,
 price_sell: 12,
 img_url: "https://img.alicdn.com/bao/uploaded/i1/T1bPdnXpRhXXXXXXXX_!!0-item_pic.jpg",
 sale_nums: "5",
 goods_nums: "21"
 */

public class NovelItem implements Parcelable {
    public boolean result;
    public List<Novel> pros;

    protected NovelItem(Parcel in) {
        result = in.readByte() != 0;
        pros = in.createTypedArrayList(Novel.CREATOR);
    }

    public static final Creator<NovelItem> CREATOR = new Creator<NovelItem>() {
        @Override
        public NovelItem createFromParcel(Parcel in) {
            return new NovelItem(in);
        }

        @Override
        public NovelItem[] newArray(int size) {
            return new NovelItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (result ? 1 : 0));
        dest.writeTypedList(pros);
    }


    public static class Novel implements Parcelable {
        public String id;
        public String name;
        public String ISBN;
        public String price_market;
        public String price_sell;
        public String img_url;
        public String sale_nums;
        public String goods_nums;


        protected Novel(Parcel in) {
            id = in.readString();
            name = in.readString();
            ISBN = in.readString();
            price_market = in.readString();
            price_sell = in.readString();
            img_url = in.readString();
            sale_nums = in.readString();
            goods_nums = in.readString();
        }

        public static final Creator<Novel> CREATOR = new Creator<Novel>() {
            @Override
            public Novel createFromParcel(Parcel in) {
                return new Novel(in);
            }

            @Override
            public Novel[] newArray(int size) {
                return new Novel[size];
            }
        };

        @Override
        public String toString() {
            return "Novel{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", ISBN='" + ISBN + '\'' +
                    ", price_market='" + price_market + '\'' +
                    ", price_sell='" + price_sell + '\'' +
                    ", img_url='" + img_url + '\'' +
                    ", sale_nums='" + sale_nums + '\'' +
                    ", goods_nums='" + goods_nums + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeString(ISBN);
            dest.writeString(price_market);
            dest.writeString(price_sell);
            dest.writeString(img_url);
            dest.writeString(sale_nums);
            dest.writeString(goods_nums);
        }
    }


    @Override
    public String toString() {
        return "NovelItem{" +
                "result=" + result +
                ", pros=" + pros +
                '}';
    }
}
