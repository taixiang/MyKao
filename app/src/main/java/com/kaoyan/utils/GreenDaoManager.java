package com.kaoyan.utils;

import android.content.Context;

import com.kaoyan.database.DaoMaster;
import com.kaoyan.database.DaoSession;

import org.greenrobot.greendao.database.Database;


/**
 * Created by tx on 2017/7/26.
 * 数据库初始化
 */

public class GreenDaoManager {
    private static String DB_NAME = "yantuvip_db";
    private static GreenDaoManager greenDaoManager;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private GreenDaoManager(Context context){
        DbOpenHelper helper = new DbOpenHelper(context,DB_NAME);
        Database database = helper.getWritableDb();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }
    public static GreenDaoManager getInstance(Context context){
        if(greenDaoManager==null){
            greenDaoManager=new GreenDaoManager(context);
        }
        return greenDaoManager;
    }

    public DaoMaster getMaster(){
        return daoMaster;
    }
    public DaoSession getDaosession(){

        return daoSession;
    }

}
