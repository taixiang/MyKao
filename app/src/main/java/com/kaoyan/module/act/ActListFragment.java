package com.kaoyan.module.act;

import android.widget.ListView;

import com.kaoyan.R;
import com.kaoyan.adapter.ActListAdapter;
import com.kaoyan.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tx on 2017/8/11.
 * 活动列表
 */

public class ActListFragment extends BaseFragment{

    @BindView(R.id.listView)
    ListView listView;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_act_list;
    }

    @Override
    protected void init() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        listView.setAdapter(new ActListAdapter(mActivity,list,R.layout.adapter_act_list));
    }
}
