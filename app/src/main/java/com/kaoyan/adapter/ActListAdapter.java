package com.kaoyan.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by tx on 2017/8/11.
 */

public class ActListAdapter extends BaseCustomAdapter<Integer> {

    public ActListAdapter(Context mContext, List<Integer> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
    }

    @Override
    public void convert(Context context, ViewHolder helper, Integer item, int position) {

    }
}
