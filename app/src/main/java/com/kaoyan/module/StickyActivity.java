package com.kaoyan.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.kaoyan.R;
import com.kaoyan.base.BaseActivity;
import com.kaoyan.model.CourseListItem;
import com.kaoyan.module.courselist.CourseListFragment;
import com.kaoyan.widget.SimpleViewPagerIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by tx on 2017/8/28.
 */

public class StickyActivity extends BaseActivity {

    @BindView(R.id.id_stickynavlayout_indicator)
    SimpleViewPagerIndicator indicator;
    String[] title = {"1","2","3"};
    List<CourseListItem> list = new ArrayList<>();

    @BindView(R.id.id_stickynavlayout_viewpager)
    ViewPager viewPager;

    @Override
    protected int attachLayoutRes() {
        return R.layout.frag_test3;
    }

    @Override
    protected void init() {
        for(int i=0;i<title.length;i++){
            if(i == 0){
                list.add(new CourseListItem(CourseListFragment.newInstance(2017,true),2017,true));
            }else {
                list.add(new CourseListItem(CourseListFragment.newInstance(2017,false),2017,false));
            }
        }
        indicator.setTitles(title);
        viewPager.setAdapter(new MyFragAdapter(getSupportFragmentManager()));
    }


    private class MyFragAdapter extends FragmentStatePagerAdapter {
        private CourseListFragment mCurrentFrag;
        private FragmentManager fm;

        private Map<Integer, CourseListFragment> map = new HashMap<Integer, CourseListFragment>();

        public MyFragAdapter(FragmentManager fm) {
            super(fm);
            this.fm = fm;
        }

        @Override
        public Fragment getItem(int position) {
//            LogUtil.i("FragmentStatePagerAdapter == "+position);

            return  list.get(position).fragment;
        }

        @Override
        public int getCount() {
            return  list.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            CourseListFragment fragment = (CourseListFragment) super.instantiateItem(container, position);
            map.put(position,fragment);
            return fragment;
        }

        //        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            FragmentTransaction ft = fm.beginTransaction();

//            if(isChange){
//                CourseListFragment frag = list.get(position);
//                frag.reGetData(tag);
//                LogUtil.i("instantiateItem " +frag.isAdded()+"  position "+position + "  tag= " +tag);
//            }

//            if(!frag.isAdded()){
//                LogUtil.i(" instantiateItem  !isAdded "+position);
//                String name = frag.getClass().getName();
//                ft.add(frag,name);
//            }else {
//                LogUtil.i(" instantiateItem   "+position);
//                ft.replace(view.getId(),CourseListFragment.newInstance(tag));
//            }
//            View view = frag.getView();
//            ft.commit();
//            fm.executePendingTransactions();
//            if(isChange){
//                LogUtil.i(" instantiateItem  isChange  "+position);
//
//                removeFragment(fm,container,position,tag);
//            }
//            return super.instantiateItem(container, position);
//
//        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            mCurrentFrag = (CourseListFragment) object;
            super.setPrimaryItem(container, position, object);
        }

        public CourseListFragment getmCurrentFrag(int position){
            return map.get(position);
        }
    }

}
