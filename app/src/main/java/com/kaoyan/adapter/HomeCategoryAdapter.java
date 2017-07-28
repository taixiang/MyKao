package com.kaoyan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaoyan.R;


/**
 * Created by tx on 2017/7/28.
 * 首页分类模块adapter
 */

public class HomeCategoryAdapter extends BaseAdapter {

    private String[] name;
    private Context context;
    private LayoutInflater inflater;

    public HomeCategoryAdapter(String[] name, Context context) {
        this.name = name;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return name[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_home_category, parent, false);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName = convertView.findViewById(R.id.tv_name);
        holder.ivLogo = convertView.findViewById(R.id.iv_logo);
        holder.tvName.setText(name[position]);
        return convertView;
    }

    private class ViewHolder {
        private ImageView ivLogo;
        private TextView tvName;
    }

}
