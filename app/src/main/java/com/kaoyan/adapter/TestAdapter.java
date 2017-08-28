package com.kaoyan.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaoyan.R;
import com.kaoyan.model.FindItem;
import com.nex3z.flowlayout.FlowLayout;

import java.util.List;

/**
 * Created by tx on 2017/7/18.
 */

public class TestAdapter extends BaseQuickAdapter<FindItem.Find,BaseViewHolder> {
    private Context context;

    public TestAdapter(@LayoutRes int layoutResId, @Nullable List<FindItem.Find> data,Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, FindItem.Find item) {
        helper.setText(R.id.tv_name,item.pro_name).setText(R.id.tv_count,item.price_sell);
        FlowLayout flowContainer = helper.getView(R.id.flowContainer);
        flowContainer.removeAllViews();
        for(int i= 0 ;i<5;i++){
            TextView textView = new TextView(context);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setText("张雪峰");
            flowContainer.addView(textView);
        }

    }
}
