package com.kaoyan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.kaoyan.R;
import com.kaoyan.model.FindItem;

import java.util.List;

/**
 * Created by tx on 2017/7/20.
 */

public class TestAdapter2 extends BaseRecyclerAdapter<FindItem.Find> {

    public TestAdapter2(Context context, List<FindItem.Find> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void bindConvert(RecyclerViewHolder holder, int position, FindItem.Find item) {
        holder.setText(R.id.tv_name,item.pro_name);holder.setText(R.id.tv_count,item.price_sell);
    }
}
