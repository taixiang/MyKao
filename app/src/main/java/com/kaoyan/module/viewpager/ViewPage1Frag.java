package com.kaoyan.module.viewpager;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kaoyan.R;
import com.kaoyan.adapter.BaseCustomAdapter;
import com.kaoyan.adapter.ViewHolder;
import com.kaoyan.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tx on 2017/9/18.
 */

public class ViewPage1Frag extends BaseFragment {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.container)
    LinearLayout container;

    @Override
    protected int attachLayoutRes() {
        return R.layout.frag_view_page;
    }

    @Override
    protected void init() {
        container.setFocusable(true);
        container.setFocusableInTouchMode(true);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        listView.setAdapter(new TextAdapter(mActivity,list,R.layout.adapter_text_item));
    }


    private class TextAdapter extends BaseCustomAdapter<Integer>{

        public TextAdapter(Context mContext, List<Integer> mDatas, int itemLayoutId) {
            super(mContext, mDatas, itemLayoutId);
        }

        @Override
        public void convert(Context context, ViewHolder helper, Integer item, int position) {

        }
    }



}
