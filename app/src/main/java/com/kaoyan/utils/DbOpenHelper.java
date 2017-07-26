package com.kaoyan.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kaoyan.database.DaoMaster;
import com.kaoyan.database.TestDao;
import com.kaoyan.database.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by tx on 2017/7/26.
 * 封装DBHelp，用于升级
 */

public class DbOpenHelper extends DaoMaster.OpenHelper {

    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        LogUtil.i(" oldVer "+oldVersion + "  newVer "+newVersion );
        MigrationHelper.getInstance().migrate(db,UserDao.class,TestDao.class);
    }
}
