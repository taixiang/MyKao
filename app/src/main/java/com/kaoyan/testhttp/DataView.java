package com.kaoyan.testhttp;

import com.kaoyan.bean.BaseBean;

/**
 * Created by tx on 2017/9/2.
 */

public interface DataView {

    void  onGetDataFailured(Throwable e, String requestTag);
    void onGetDataSuccess(String result, String requestTag);


}
