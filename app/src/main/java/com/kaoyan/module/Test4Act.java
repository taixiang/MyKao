package com.kaoyan.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.kaoyan.R;
import com.kaoyan.base.BaseActivity;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.module.test.TestFrag1;
import com.kaoyan.module.test.TestFrag2;
import com.kaoyan.module.test.TestFrag3;
import com.kaoyan.module.test.TestFrag4;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tx on 2017/8/23.
 */

public class Test4Act extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected int attachLayoutRes() {
        return R.layout.act_test4;
    }

    @Override
    protected void init() {

        final List<BaseFragment> list = new ArrayList<>();
        list.add(new TestFrag1());
        list.add(new TestFrag2());
        list.add(new TestFrag3());
        list.add(new TestFrag4());

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

        });
    }
}
