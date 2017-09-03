package com.kaoyan.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tx on 2017/9/3.
 */

public class FindBean extends BaseBean{

    public List<Find> pros;
    public class Find {
        public String user_name;
        public String university;
        public String pro_id;
        public String ISBN;
        public String pro_name;
        public String price_sell;
        public String image_url;

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
        return "FindBean{" +
                "pros=" + pros +
                '}';
    }
}
