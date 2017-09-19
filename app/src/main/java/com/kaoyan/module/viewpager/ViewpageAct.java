package com.kaoyan.module.viewpager;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.kaoyan.R;
import com.kaoyan.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tx on 2017/9/18.
 */

public class ViewpageAct extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.container)
    LinearLayout container;


    public static void actView(BaseActivity ac){
        Intent intent = new Intent(ac,ViewpageAct.class);
        ac.startActivity(intent);
    }

    private List<Fragment> list = new ArrayList<>();
    @Override
    protected int attachLayoutRes() {
        return R.layout.layout_view_page;
    }

    @Override
    protected void init() {
        container.setFocusableInTouchMode(true);
        container.setFocusable(true);
        list.add(new ViewPage1Frag());
        list.add(new ViewPage1Frag());
        list.add(new ViewPage1Frag());
        viewPager.setAdapter(new ViewPage(getSupportFragmentManager()));
    }

    private class ViewPage extends FragmentStatePagerAdapter{

        public ViewPage(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


}
