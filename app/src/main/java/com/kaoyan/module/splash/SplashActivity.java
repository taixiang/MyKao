package com.kaoyan.module.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.kaoyan.MainActivity;
import com.kaoyan.R;
import com.kaoyan.base.BaseActivity;
import com.kaoyan.module.guide.GuideActivity;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.utils.SharedPreferencesUtil;

/**
 * Created by tx on 2017/8/10.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected int attachLayoutRes() {
        this.setTheme(R.style.AppTheme);
        boolean isFirst = (boolean) SharedPreferencesUtil.get(SplashActivity.this,GuideActivity.ISFIRST,false);
        if(!isFirst){
            Intent intent= new Intent(SplashActivity.this,GuideActivity.class);
            startActivity(intent);
            return 0;
        }
        return R.layout.activity_splash;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void init() {
        LogUtil.i("SplashActivity init ");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        },3000) ;
    }
}
