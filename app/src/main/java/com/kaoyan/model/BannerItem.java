package com.kaoyan.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by tx on 2017/7/27.
 */

public class BannerItem implements Parcelable {
    public List<News> news;

    protected BannerItem(Parcel in) {
        news = in.createTypedArrayList(News.CREATOR);
    }

    public static final Creator<BannerItem> CREATOR = new Creator<BannerItem>() {
        @Override
        public BannerItem createFromParcel(Parcel in) {
            return new BannerItem(in);
        }

        @Override
        public BannerItem[] newArray(int size) {
            return new BannerItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(news);
    }

    public static class News implements Parcelable{
        public String img_url;
        public int newsid;


        protected News(Parcel in) {
            img_url = in.readString();
            newsid = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(img_url);
            dest.writeInt(newsid);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<News> CREATOR = new Creator<News>() {
            @Override
            public News createFromParcel(Parcel in) {
                return new News(in);
            }

            @Override
            public News[] newArray(int size) {
                return new News[size];
            }
        };
    }
}
