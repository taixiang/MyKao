package com.kaoyan.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by tx on 2017/8/16.
 */

public class QuesAdapter extends BaseCustomAdapter<Integer> {


    public QuesAdapter(Context mContext, List mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
    }

    @Override
    public void convert(Context context, ViewHolder helper, Integer item, int position) {

    }

}
