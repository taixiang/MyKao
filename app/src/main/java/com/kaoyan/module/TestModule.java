package com.kaoyan.module;

/**
 * Created by tx on 2017/7/24.
 */

public interface TestModule {
    void getData(boolean isRefresh);

    /**
     * 加载更多数据
     */
    void getMoreData();
}
