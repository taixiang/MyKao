package com.kaoyan.module;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kaoyan.R;
import com.kaoyan.adapter.FindAdapter;
import com.kaoyan.adapter.TestAdapter;
import com.kaoyan.adapter.TestCustomAdapter;
import com.kaoyan.api.RetrofitService;
import com.kaoyan.base.BaseActivity;
import com.kaoyan.base.BaseApplication;
import com.kaoyan.model.BannerItem;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.utils.CommonUtil;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.utils.NetUtil;
import com.kaoyan.utils.ToastUtils;
import com.kaoyan.view.IMainPresenter;
import com.kaoyan.view.IMainView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by tx on 2017/7/19.
 */

public class Test2Activity extends BaseActivity implements IMainView{
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.listView)
    ListView recyclerView;
    @BindView(R.id.btn)
    TextView btn;
    @BindView(R.id.tv_search_bg)
    EditText search;

    private IMainPresenter p = new IMainPresenter(this);
    private FindAdapter adapter;
    private TestCustomAdapter adapter2;
    //    private TestAdapter2 adapter2;
    private LinkedList<FindItem.Find> list = new LinkedList<>();

    public static void actTo2(BaseActivity activity){
        Intent intent = new Intent(activity,Test2Activity.class);
        activity.startActivity(intent);
    }

    @OnClick(R.id.btn)
    void click(){
        p.login();
    }

    @Override
    public void loadNoData() {
        Log.i("》》》 "," loadnodata  ==== ");
        refreshLayout.finishLoadmore();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_test2;
    }

    @OnClick(R.id.tv_search_bg)
    void search(){
        Intent intent = new Intent(this,TestActivity3.class);
        int location[] = new int[2];
        search.getLocationOnScreen(location);
        LogUtil.i("location  x= "+location[0]+"  y= "+location[1]);
        intent.putExtra("x",location[0]);
        intent.putExtra("y",location[1]);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    @Override
    protected void init() {
        setTitle("标题");
        goBack();
        p.getData(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                p.getData(true);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
//                p.getMoreData();
            }
        });

        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(Test2Activity.this,""+position);
            }
        });
    }

    @Override
    public void showNetError() {
        super.showNetError();
        refreshLayout.finishLoadmore();
    }

    @Override
    public void loadData(HomeMiddleItem middleItem) {
        LogUtil.i(" middleItem "," 》》》》  "+middleItem.toString());
    }

    @Override
    public void loadNovel(FindItem item) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        list.addAll((List<FindItem.Find>)item.pros1);
        Log.i("》》》》》  "," list ====  "+list.size());
        if(adapter2 == null){
//            adapter = new FindAdapter(list,this);
            adapter2 = new TestCustomAdapter(this,list,R.layout.adapter_keywords);
            recyclerView.setAdapter(adapter2);
        }else {
            adapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void loadFindList(List<FindItem.Find> finds) {
        list.addAll(finds);
        Log.i("》》》》》  "," list ====  "+list.size());
        if(adapter2 == null){
//            adapter = new FindAdapter(list,this);
            adapter2 = new TestCustomAdapter(this,list,R.layout.adapter_keywords);
            recyclerView.setAdapter(adapter2);
        }else {
            adapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void login() {

    }

    @Override
    public void loadBanner(BannerItem item) {

    }

    private void stopRefreshAndLoad(){
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
    }
}
