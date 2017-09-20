package com.kaoyan.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.kaoyan.R;
import com.kaoyan.adapter.GroupAdapter;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.model.Group;
import com.kaoyan.model.User;
import com.kaoyan.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.kaoyan.R.id.img;

/**
 * Created by tx on 2017/9/14.
 */

public class ExpandableFrag extends BaseFragment {

    @BindView(R.id.elv)
    ExpandableListView elv;
    private List<Group> list = new ArrayList<Group>();
    int[] img = new int[6];
    @Override
    protected int attachLayoutRes() {
        return R.layout.frag_expandable;
    }

    @Override
    protected void init() {
        initData();
        GroupAdapter adapter = new GroupAdapter(mActivity, list);
        elv.setAdapter(adapter);
        for(int i=0;i<list.size();i++){
            elv.expandGroup(i);
        }
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                LogUtil.i(" elv groupPosition"+groupPosition);
                return true;
            }
        });


    }


    private void initData() {


        Group group1 = new Group("贵圈好乱");
        group1.addUser(new User(0, "张翰", true, "我爱娜扎！"));
        group1.addUser(new User(0, "郑爽", false, "妈蛋，要么瘦，要么死！"));


        Group group2 = new Group("超星星");
        group2.addUser(new User(img[4], "林志炫", true, "其实我的小肚子都是唱歌导致的，哈哈哈"));

        list.add(group1);
        list.add(group2);

    }
}
