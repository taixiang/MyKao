package com.kaoyan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.kaoyan.R;
import com.kaoyan.base.BaseActivity;
import com.kaoyan.model.FindItem;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by tx on 2017/6/9.
 */

public class FindAdapter extends BaseAdapter {
    private List<FindItem.Find> mList;
    private BaseActivity mActivity;
    private LayoutInflater mLayoutInflater;

    public FindAdapter(List<FindItem.Find> mList, BaseActivity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
        this.mLayoutInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public int getCount() {
        return mList == null ? 0:mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(null == convertView){
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.adapter_keywords,null);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.iv_logo = (ImageView) convertView.findViewById(R.id.iv_logo);

        holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
        final FindItem.Find item = mList.get(position);
        if(item != null){

            holder.tv_name.setText(item.pro_name);
            holder.tv_price.setText("￥ "+item.price_sell);
        }
        return convertView;
    }

    private class ViewHolder{
        private ImageView iv_logo;
        private TextView tv_num;
        private TextView tv_school;
        private TextView tv_name;
        private TextView tv_price;

    }

}
