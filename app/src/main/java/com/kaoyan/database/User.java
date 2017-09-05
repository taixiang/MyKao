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
public class User {
    @Id
    private Long id;
    private String name;
    private String age;
    private String sex;




    @Generated(hash = 596426048)
    public User(Long id, String name, String age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
