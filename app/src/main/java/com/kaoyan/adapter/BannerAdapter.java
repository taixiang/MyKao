package com.kaoyan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tx on 2017/7/21.
 */

public class BannerAdapter extends PagerAdapter {
    private List<Fragment> fragments;
    private FragmentManager fm;

    public BannerAdapter(List<Fragment> fragments, FragmentManager fm) {
        this.fragments = fragments;
        this.fm = fm;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(fragments.size() != 0){
            position = position % fragments.size();
            if(position < 0){
                position = position + fragments.size();
            }

            //动态添加Fragment
            Log.i("===", "instantiateItem"+position);
            FragmentTransaction ft = fm.beginTransaction();
            Fragment frag = fragments.get(position);
            if(!frag.isAdded()){
                String name = frag.getClass().getName();
                ft.add(frag, name);
                ft.commitAllowingStateLoss();
                fm.executePendingTransactions();
            }
            View view = frag.getView();
            if(view.getParent() == null){
                container.addView(view);
            }else{
                ((ViewGroup)view.getParent()).removeView(view);
                container.addView(view);
            }
            return view;
        }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.i("===", "destroyItem"+position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
