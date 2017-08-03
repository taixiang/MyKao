package com.kaoyan.module.Course;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.fragment.TestFragment;
import com.kaoyan.model.CourseListItem;
import com.kaoyan.module.courselist.CourseListFragment;
import com.kaoyan.utils.LogUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.first)
    TextView first;
    @BindView(R.id.second)
    TextView second;
    List<CourseListItem> list = new ArrayList<>();

    private List<String> tagList = new ArrayList<>();

    private int mPosition;
    private MyFragAdapter adapter;
    private int tag = 2017;
    private boolean isChange = false;
    private String[] titles = {"全部","蜕变计划","专业课","协议班","联报班","协议班","公共课1对1"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_course;
    }
    public static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    @Override
    protected void init() {

        for(int i=0;i<titles.length;i++){
            list.add(new CourseListItem(CourseListFragment.newInstance(2017),2017,false));
        }
        adapter = new MyFragAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
                LogUtil.i(" position = " + position + " tag ==  "+ tag );

                if(!list.get(position).isShow){
                    CourseListFragment fragment = adapter.getmCurrentFrag(mPosition);
                    list.get(position).isShow = true;
                    fragment.reGetData(tag);
                    return;
                }
                if(list.get(position).tag != tag){
                    CourseListFragment fragment = adapter.getmCurrentFrag(mPosition);
                    list.get(position).tag = tag;
                    fragment.reGetData(tag);
                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.first)
    void first(){
        if(tag ==2017){
            isChange = false;
            return;
        }
        tag = 2017;
        isChange = true;

//        list.clear();
//        for(int i=0;i<titles.length;i++){
//            list.add(CourseListFragment.newInstance(2017));
//        }
//        adapter.notifyDataSetChanged();
//        viewPager.setCurrentItem(mPosition);
        CourseListFragment fragment = adapter.getmCurrentFrag(mPosition);
        fragment.reGetData(tag);
        list.get(mPosition).tag = tag;
    }

    @OnClick(R.id.second)
    void second(){
        if(tag == 2018){
            isChange = false;
            return;
        }
        tag = 2018;
        isChange = true;
//        for(CourseListFragment fragment : list){
//            fragment.setYear(2018);
//        }

//        CourseListFragment fragment = adapter.getmCurrentFrag();
//        fragment.reGetData();


//        list.clear();
//        for(int i=0;i<titles.length;i++){
//            list.add(CourseListFragment.newInstance(2018));
//        }
//        adapter.notifyDataSetChanged();
//        viewPager.setCurrentItem(mPosition);
        for(int i=0;i<titles.length;i++){

        }
        CourseListFragment fragment = adapter.getmCurrentFrag(mPosition);
        fragment.reGetData(tag);
        list.get(mPosition).tag = tag;
    }

    private class MyFragAdapter extends FragmentStatePagerAdapter{
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
            return titles[position];
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

    private void removeFragment(FragmentManager fm,ViewGroup container,int index,int flag) {
        String tag = getFragmentTag(container.getId(), index);
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment == null)
            return;
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(container.getId(),CourseListFragment.newInstance(flag));
        ft.commit();
        ft = null;
        fm.executePendingTransactions();
    }


    private String getFragmentTag(int viewId, int index) {
        try {
            Class<FragmentStatePagerAdapter> cls = FragmentStatePagerAdapter.class;
            Class<?>[] parameterTypes = { int.class, long.class };
            Method method = cls.getDeclaredMethod("makeFragmentName",
                    parameterTypes);
            method.setAccessible(true);
            String tag = (String) method.invoke(this, viewId, index);
            return tag;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
