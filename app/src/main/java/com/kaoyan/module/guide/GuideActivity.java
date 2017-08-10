package com.kaoyan.module.guide;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kaoyan.MainActivity;
import com.kaoyan.R;
import com.kaoyan.adapter.GuideAdapter;
import com.kaoyan.base.BaseActivity;
import com.kaoyan.utils.ImgManager;
import com.kaoyan.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tx on 2017/8/10.
 */

public class GuideActivity extends BaseActivity {

    public static String ISFIRST = "isFirst";

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_guide;
    }

    @Override
    protected void init() {


        int[] imageArray = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        List<ImageView> viewList = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        for (int i=0;i<imageArray.length;i++){
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setLayoutParams(params);
            ImgManager.loadImage(GuideActivity.this,imageArray[i],imageView);
            viewList.add(imageView);
            if(i == 3){
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferencesUtil.put(GuideActivity.this,ISFIRST,true);
                        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                    }
                });
            }
        }
        viewPager.setAdapter(new GuideAdapter(viewList));
    }

}
