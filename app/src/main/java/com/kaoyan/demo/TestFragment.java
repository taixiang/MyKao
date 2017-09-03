package com.kaoyan.demo;

import com.kaoyan.R;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.bean.FindBean;
import com.kaoyan.utils.GsonUtils;
import com.kaoyan.utils.LogUtil;

/**
 * Created by tx on 2017/9/2.
 */

public class TestFragment extends BaseFragment implements DataView {
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_test;
    }

    @Override
    protected void init() {
        getData();
    }

    private void getData(){

        RequestUtil.getData3(RequestUtil.msgApi.getFind3(1),this.<String>bindToLife(),this,"tag");

    }

    @Override
    public void onGetDataFailured(Throwable e, String requestTag) {

    }

    @Override
    public void onGetDataSuccess(String result, String requestTag) {

        FindBean t = GsonUtils.jsonToClass(result,FindBean.class);

        LogUtil.i("findItem  utils ========= "+t.toString());
    }
}