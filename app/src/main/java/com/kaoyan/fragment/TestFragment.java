package com.kaoyan.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kaoyan.R;
import com.kaoyan.adapter.BannerAdapter;
import com.kaoyan.adapter.TestAdapter2;
import com.kaoyan.model.BannerItem;
import com.kaoyan.module.Test2Activity;
import com.kaoyan.adapter.TestAdapter;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
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
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tx on 2017/7/18.
 */

public class TestFragment extends BaseFragment implements IMainView {
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

    public static TestFragment newInstance(){
        return new TestFragment();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_test;
    }

    @Override
    protected void init() {
//        mIsMulti = false;
        LogUtil.i(" widthheigth == 》》》》  " + CommonUtil.getWidthAndHeight(mActivity)[0] + "   " + CommonUtil.getWidthAndHeight(mActivity)[1] + "    " + CommonUtil.getWidthAndHeight(mActivity)[0] * 2 / 5);

        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtil.getWidthAndHeight(mActivity)[0] * 2 / 5);
        banner.setLayoutParams(ll);
        presenter = new IMainPresenter(this);
        presenter.getData(false);
        LogUtil.i("  11111  "+Thread.currentThread().getName());

        presenter.loadBanner();

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                LogUtil.i(" onRefresh ");
                presenter.getData(true);
                presenter.loadBanner();
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


        LogUtil.i("baseFragment 》》》》》 test  super.setUserVisibleHint   after");

    }

    @Override
    public void loadData(HomeMiddleItem middleItem) {


    }

    @Override
    public void loadNovel(FindItem item) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        list.addAll(item.pros1);

        Log.i("》》》》》  ", " list ====  " + list.size());
        if (adapter == null) {
            Log.i("》》》》》  ", " adapter notifyData fail ");
            recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            adapter = new TestAdapter(R.layout.adapter_keywords, list);
//            adapter2 = new TestAdapter2(mActivity,list,R.layout.adapter_keywords);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Log.i("》》》》  ", " ===== " + position);
//                    ToastUtils.showToast(mActivity, position + " ");
                    Test2Activity.actTo2(mActivity);
                }
            });

            recyclerView.setAdapter(adapter);
        } else {
            Log.i("》》》》》  ", " adapter notifyData");
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadFindList(List<FindItem.Find> finds) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        list.addAll(finds);

        Log.i("》》》》》  ", " list ====  " + list.size());
        if (adapter == null) {
            Log.i("》》》》》  ", " adapter notifyData fail ");
            recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            adapter = new TestAdapter(R.layout.adapter_keywords, list);
//            adapter2 = new TestAdapter2(mActivity,list,R.layout.adapter_keywords);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Log.i("》》》》  ", " ===== " + position);
//                    ToastUtils.showToast(mActivity, position + " ");
                    Test2Activity.actTo2(mActivity);
                }
            });

            recyclerView.setAdapter(adapter);
        } else {
            Log.i("》》》》》  ", " adapter notifyData");
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void login() {

    }

    @Override
    public void loadBanner(BannerItem item) {
        LogUtil.i(" banner   "+item.toString());
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
    public void onStart() {
        super.onStart();
        LogUtil.i("onStart");
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
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
