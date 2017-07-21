package com.kaoyan.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by tx on 2017/7/21.
 * 自定义adapter
 */

public abstract class BaseCustomAdapter<T> extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<T> mDatas;
    private int mItemLayoutId;

    public BaseCustomAdapter(Context mContext, List<T> mDatas,int itemLayoutId) {
        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mDatas !=null ? mDatas.size():0; //mDatas !=null ? mDatas.size():0
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position,convertView,parent);
        convert(viewHolder,getItem(position),position);
        return viewHolder.getConvertView();
    }
    public abstract void convert(ViewHolder helper, T item,int position);

    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent)
    {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }




}
