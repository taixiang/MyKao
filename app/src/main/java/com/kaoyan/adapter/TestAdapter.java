package com.kaoyan.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaoyan.R;
import com.kaoyan.model.FindItem;

import java.util.List;

/**
 * Created by tx on 2017/7/18.
 */

public class TestAdapter extends BaseQuickAdapter<FindItem.Find,BaseViewHolder> {


    public TestAdapter(@LayoutRes int layoutResId, @Nullable List<FindItem.Find> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindItem.Find item) {
        helper.setText(R.id.tv_name,item.pro_name).setText(R.id.tv_count,item.price_sell);

    }
}
