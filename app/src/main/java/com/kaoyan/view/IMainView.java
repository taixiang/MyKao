package com.kaoyan.view;


import com.kaoyan.base.IBaseView;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;

/**
 * Created by tx on 2017/7/14.
 */

public interface IMainView extends IBaseView {

    /**
     * 显示数据
     */
    void loadData(HomeMiddleItem middleItem);

    void loadNovel(FindItem item);
}
