package com.kaoyan.module.courselist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kaoyan.R;
import com.kaoyan.adapter.TestAdapter;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.fragment.TestFragment;
import com.kaoyan.model.BannerItem;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.module.Test2Activity;
import com.kaoyan.utils.CommonUtil;
import com.kaoyan.utils.ImgManager;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.utils.ToastUtils;
import com.kaoyan.view.IMainPresenter;
import com.kaoyan.view.IMainView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tx on 2017/8/3.
 */

public class CourseListFragment extends BaseFragment  implements IMainView {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.banner)
    Banner banner;
    private IMainPresenter presenter;
    private TestAdapter adapter;
    //    private TestAdapter2 adapter2;
    private ArrayList<FindItem.Find> list = new ArrayList<>();
      int year;

    public static CourseListFragment newInstance(int year){
        CourseListFragment fragment = new CourseListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("year",year);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void reGetData(int tag){
        LogUtil.i(" tag === " + tag);
        if(presenter == null){
            presenter = new IMainPresenter(this);
        }
        presenter.loadCourse(tag);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_test;
    }

    @Override
    protected void init() {
//        mIsMulti = false;
        year = getArguments().getInt("year");
        LogUtil.i(" year === " + year);
//        LogUtil.i(" widthheigth == 》》》》  " + CommonUtil.getWidthAndHeight(mActivity)[0] + "   " + CommonUtil.getWidthAndHeight(mActivity)[1] + "    " + CommonUtil.getWidthAndHeight(mActivity)[0] * 2 / 5);

        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtil.getWidthAndHeight(mActivity)[0] * 2 / 5);
        banner.setLayoutParams(ll);
        presenter = new IMainPresenter(this);
//        presenter.loadCourse(year);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                presenter.getData(true);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                presenter.getMoreData();
            }
        });
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ImgManager.loadImage(context, (String) path, imageView);
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showToast(mActivity, "" + position);
            }
        });
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void loadData(HomeMiddleItem middleItem) {


    }

    @Override
    public void loadNovel(FindItem item) {

    }

    @Override
    public void loadFindList(List<FindItem.Find> finds) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        list.clear();
        list.addAll(finds);
        LogUtil.i("loadFindList  "+list.toString());

        if (adapter == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            adapter = new TestAdapter(R.layout.adapter_keywords, list);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Test2Activity.actTo2(mActivity);
                }
            });
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void login() {

    }

    @Override
    public void loadBanner(BannerItem item) {
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<4;i++){
            list.add(item.news.get(i).img_url);
        }
//        for (BannerItem.News banner : item.news ) {
//            list.add(banner.img_url);
//        }
        banner.setImages(list);
        banner.start();
    }

    @Override
    public void showNetError() {
        super.showNetError();
        refreshLayout.finishLoadmore();
    }

    @Override
    public void loadNoData() {
        super.loadNoData();
        refreshLayout.finishLoadmore();
    }


}
