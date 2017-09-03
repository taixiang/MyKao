package com.kaoyan.testhttp;

import com.google.gson.reflect.TypeToken;
import com.kaoyan.R;
import com.kaoyan.api.RetrofitService;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.bean.BaseBean;
import com.kaoyan.bean.FindBean;
import com.kaoyan.model.FindItem;
import com.kaoyan.utils.GsonUtils;
import com.kaoyan.utils.LogUtil;

import java.util.List;

/**
 * Created by tx on 2017/9/2.
 */

public class TestFragment extends BaseFragment implements DataView{
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_test;
    }

    @Override
    protected void init() {
        getData();
    }

    private void getData(){

//        RequestUtil.getData2(RequestUtil.msgApi.getFind2(1),this.<String>bindToLife(),this,"tag");
        RequestUtil.getData3(RequestUtil.msgApi.getFind3(1),this.<String>bindToLife(),this,"tag");

    }

    @Override
    public void onGetDataFailured(Throwable e, String requestTag) {

    }

    @Override
    public void onGetDataSuccess(String result, String requestTag) {

        FindBean t = GsonUtils.jsonToClass(result,FindBean.class);


//        List<FindBean.Find> t  = GsonUtils.jsonToList(result,new TypeToken<List<FindBean.Find>>(){}.getType());

        LogUtil.i("findItem  utils  "+t.toString());

    }


}
