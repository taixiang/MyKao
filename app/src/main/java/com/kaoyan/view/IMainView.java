package com.kaoyan.view;


import com.kaoyan.base.IBaseView;
import com.kaoyan.model.BannerItem;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;

import java.util.List;

/**
 * Created by tx on 2017/7/14.
 */

public interface IMainView extends IBaseView {

    /**
     * 显示数据
     */
    void loadData(HomeMiddleItem middleItem);

    void loadNovel(FindItem item);

    void loadFindList(List<FindItem.Find> finds);
    //登录
    void login();

    void loadBanner(BannerItem item);
}
