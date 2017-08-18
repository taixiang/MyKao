package com.kaoyan.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.model.FindItem;
import com.kaoyan.utils.ImgManager;

import java.util.List;

/**
 * Created by tx on 2017/7/21.
 */

public class TestCustomAdapter extends BaseCustomAdapter<FindItem.Find> {
    public TestCustomAdapter(Context mContext, List<FindItem.Find> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
    }

    @Override
    public void convert(Context context, ViewHolder helper, FindItem.Find item, int position) {
        TextView tv= (TextView)helper.getView(R.id.tv_name);
        tv.setText(item.pro_name);
        TextView tv2= (TextView)helper.getView(R.id.tv_price);
        tv2.setText(item.price_sell);
        if(position ==3||position==12||position==7){
            tv2.setVisibility(View.INVISIBLE);
        }else {
            tv2.setVisibility(View.VISIBLE);
        }
        ImageView imageView = helper.getView(R.id.iv_logo);
        ImgManager.loadImage(context,item.image_url,imageView);

        LinearLayout ll = helper.getView(R.id.container);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 5;
        params.rightMargin = 5;
        for(int i=0;i<item.price_sell.length();i++){
            TextView textView = new TextView(context);
            textView.setTextSize(12);
            textView.setLayoutParams(params);
            textView.setText("111");
            ll.addView(textView);
        }

    }
}
