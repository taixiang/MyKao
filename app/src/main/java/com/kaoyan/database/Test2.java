package com.kaoyan.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tx on 2017/9/5.
 */
@Entity
public class Test2 {
    @Id
    private int id;
    private String test;
    @Generated(hash = 1781498264)
    public Test2(int id, String test) {
        this.id = id;
        this.test = test;
    }
    @Generated(hash = 1885849886)
    public Test2() {
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
