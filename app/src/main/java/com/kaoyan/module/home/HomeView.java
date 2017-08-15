package com.kaoyan.module.home;


import com.kaoyan.base.IBaseView;
import com.kaoyan.model.BannerItem;

/**
 * Created by tx on 2017/7/25.
 * view层
 */

public interface HomeView extends IBaseView {

    /**
     * 轮播图
     */
    void showBanner(BannerItem bannerItem);

    /**
     * 限时活动
     */
    void showLimitAct();

    /**
     * 推荐课程
     */
    void showRecommendCourse();

}
