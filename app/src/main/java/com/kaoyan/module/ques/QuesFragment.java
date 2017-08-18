package com.kaoyan.module.ques;

import android.widget.ListView;

import com.kaoyan.R;
import com.kaoyan.adapter.QuesAdapter;
import com.kaoyan.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tx on 2017/8/16.
 */
public class QuesFragment extends BaseFragment {

    @BindView(R.id.listView)
    ListView listView;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_ques;
    }

    @Override
    protected void init() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        listView.setAdapter(new QuesAdapter(mActivity,list,R.layout.adapter_ques));
    }
}
