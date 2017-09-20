package com.kaoyan.model;

/**
 * Created by tx on 2017/9/14.
 */

public class User {
    private int imgId;
    private String nickName;
    private boolean isOnline;
    private String sign;


    public User() {
        super();
    }
    public User(int imgId, String nickName, boolean isOnline, String sign) {
        super();
        this.imgId = imgId;
        this.nickName = nickName;
        this.isOnline = isOnline;
        this.sign = sign;
    }

    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public boolean isOnline() {
        return isOnline;
    }
    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
}
