package com.kaoyan.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kaoyan.R;
import com.kaoyan.adapter.TestAdapter2;
import com.kaoyan.module.Test2Activity;
import com.kaoyan.adapter.TestAdapter;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.utils.ToastUtils;
import com.kaoyan.view.IMainPresenter;
import com.kaoyan.view.IMainView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;

/**
 * Created by tx on 2017/7/18.
 */

public class TestFragment extends BaseFragment implements IMainView{
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private IMainPresenter presenter;
    private TestAdapter adapter;
//    private TestAdapter2 adapter2;
    private ArrayList<FindItem.Find> list = new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_test;
    }

    @Override
    protected void init() {
        presenter = new IMainPresenter(this);
        presenter.getData(false);
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

    }

    @Override
    public void loadData(HomeMiddleItem middleItem) {


    }

    @Override
    public void loadNovel(FindItem item) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        list.addAll(item.pros);

        Log.i("》》》》》  "," list ====  "+list.size());
        if(adapter == null){
            Log.i("》》》》》  "," adapter notifyData fail ");
            recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            adapter = new TestAdapter(R.layout.adapter_keywords,list);
//            adapter2 = new TestAdapter2(mActivity,list,R.layout.adapter_keywords);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Log.i("》》》》  "," ===== "+position);
                    ToastUtils.showToast(mActivity,position+" ");
                    Test2Activity.actTo2(mActivity);
                }
            });
            recyclerView.setAdapter(adapter);
        }else {
            Log.i("》》》》》  "," adapter notifyData");
            adapter.notifyDataSetChanged();
        }
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
