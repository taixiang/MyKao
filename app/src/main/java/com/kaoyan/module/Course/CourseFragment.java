package com.kaoyan.module.Course;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.kaoyan.R;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tx on 2017/7/31.
 */
public class CourseFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.iv)
    ImageView iv;

    private String[] titles = {"全部","蜕变计划","专业课","协议班","联报班","协议班","公共课1对1"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_course;
    }

    @Override
    protected void init() {
        final List<Fragment> list = new ArrayList<>();

        list.add(TestFragment.newInstance());
        list.add(TestFragment.newInstance());
        list.add(TestFragment.newInstance());
        list.add(TestFragment.newInstance());
        list.add(TestFragment.newInstance());
        list.add(TestFragment.newInstance());
        list.add(TestFragment.newInstance());

        viewPager.setAdapter(new FragmentStatePagerAdapter(mActivity.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }
}
