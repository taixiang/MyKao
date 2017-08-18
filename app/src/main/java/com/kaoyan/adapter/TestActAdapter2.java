package com.kaoyan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.model.FindItem;
import com.kaoyan.utils.LogUtil;

import java.util.List;

/**
 * Created by tx on 2017/8/18.
 */

public class TestActAdapter2 extends BaseAdapter {

    private List<FindItem.Find> mDatas;
    private Context context;
    private LayoutInflater inflater;

    public TestActAdapter2(List<FindItem.Find> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_keywords,parent,false);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == 0){
            LogUtil.i("  getViewgetView ");
        }
        holder.ll = convertView.findViewById(R.id.container);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 5;
        params.rightMargin = 5;
        holder.ll.removeAllViews();
        for(int i=0;i<3;i++){
            TextView textView = new TextView(context);
            textView.setTextSize(12);
            textView.setLayoutParams(params);
            textView.setText("1");
            holder.ll.addView(textView);
        }
        holder.ll.setVisibility(View.VISIBLE);
        return convertView;
    }
    private class ViewHolder {
        LinearLayout ll;
    }
}


