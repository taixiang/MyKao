package com.kaoyan.module.home;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.kaoyan.R;
import com.kaoyan.adapter.HomeCategoryAdapter;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.utils.CommonUtil;
import com.kaoyan.utils.DensityUtil;
import com.kaoyan.utils.ImgManager;
import com.kaoyan.widget.CustomGridView;
import com.kaoyan.widget.CustomListView;
import com.youth.banner.Banner;

import butterknife.BindView;

/**
 * Created by tx on 2017/7/25.
 * 首页Fragment
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.gv_category)
    CustomGridView gvCategory;
    @BindView(R.id.ll_limit_act)
    LinearLayout llLimitAct;
    @BindView(R.id.h_scrollview)
    HorizontalScrollView hScrollView;
    @BindView(R.id.lv_recommend)
    CustomListView lvRecommend;
    private int width;

    private String[] categoryName = {"蜕变计划", "VIP协议班", "公共课联报", "公共课1对1", "考研英语", "考研政治", "考研数学", "全部分类"};
    private int[] categoryImg = {};

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        width = CommonUtil.getWidthAndHeight(mActivity)[0];
        initBanner();
        initCategory();
        initAct();
    }

    /**
     * 初始化banner
     */
    private void initBanner() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, width * 2 / 5);
        banner.setLayoutParams(params);
    }

    /**
     * 分类模块
     */
    private void initCategory() {
        gvCategory.setAdapter(new HomeCategoryAdapter(categoryName, mActivity));
    }

    /**
     * 限时活动
     */
    private void initAct() {
        final int[] test = {1};
        hScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (test.length<2) {
                    return true;
                }
                return false;
            }
        });
        int ivWidth = width - DensityUtil.dpToPx(mActivity,35);
        for (int i = 0; i < test.length; i++) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_home_limit_act, null);
            final ImageView iv = view.findViewById(R.id.iv_home_limit_act);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
            if(test.length == 1){
                params.width = width;
//                params.height = 500;
            }else if(test.length == 2){
                params.width = ivWidth/2;
            }else {
                params.width = ivWidth/2-20;
            }
            iv.setLayoutParams(params);
            ImgManager.loadImage(mActivity,"http://m.iisbn.com/images_side/1.jpg",iv);
            llLimitAct.addView(view);
        }
    }

    /**
     * 推荐课程
     */
    private void initRecommend(){

    }

}
