package com.kaoyan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.model.FindItem;

import java.util.List;

/**
 * Created by tx on 2017/7/21.
 */

public class TestCustomAdapter extends BaseCustomAdapter<FindItem.Find> {
    public TestCustomAdapter(Context mContext, List<FindItem.Find> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, FindItem.Find item, int position) {
        TextView tv= (TextView)helper.getView(R.id.tv_name);
        tv.setText(item.pro_name);
        TextView tv2= (TextView)helper.getView(R.id.tv_price);
        tv2.setText(item.price_sell);
        if(position ==3||position==12||position==7){
            tv2.setVisibility(View.INVISIBLE);
        }else {
            tv2.setVisibility(View.VISIBLE);
        }
    }
}
