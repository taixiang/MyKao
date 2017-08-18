package com.kaoyan.module;

import android.Manifest;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kaoyan.R;
import com.kaoyan.adapter.FindAdapter;
import com.kaoyan.adapter.TestActAdapter2;
import com.kaoyan.adapter.TestAdapter;
import com.kaoyan.adapter.TestCustomAdapter;
import com.kaoyan.api.RetrofitService;
import com.kaoyan.base.BaseActivity;
import com.kaoyan.base.BaseApplication;
import com.kaoyan.event.LoginEvent;
import com.kaoyan.model.BannerItem;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.utils.CommonUtil;
import com.kaoyan.utils.ImgManager;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.utils.NetUtil;
import com.kaoyan.utils.ToastUtils;
import com.kaoyan.view.IMainPresenter;
import com.kaoyan.view.IMainView;
import com.kaoyan.widget.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by tx on 2017/7/19.
 */

public class Test2Activity extends BaseActivity implements IMainView {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.listView)
    ListView recyclerView;
    @BindView(R.id.btn)
    TextView btn;
    @BindView(R.id.tv_search_bg)
    EditText search;
    @BindView(R.id.tv_timer)
    TextView tv_timer;
    Timer timer = new Timer();
    long t = 100;
    long dis;
    @BindView(R.id.banner_guide_content)
    BGABanner banner;

    private IMainPresenter p = new IMainPresenter(this);
    private FindAdapter adapter;
    private TestCustomAdapter adapter2;
    private TestActAdapter2 testActAdapter2;
    //    private TestAdapter2 adapter2;
    private LinkedList<FindItem.Find> list = new LinkedList<>();

    private List<String> list1 = new ArrayList<>();
    private BGABanner.Adapter bgaAdapter;

    public static void actTo2(BaseActivity activity) {
        Intent intent = new Intent(activity, Test2Activity.class);
        activity.startActivity(intent);
    }

    @OnClick(R.id.btn)
    void click() {
//        p.login();
        LogUtil.i(" EventBus  " + EventBus.getDefault().toString());
//        EventBus.getDefault().post(new LoginEvent());
//        setResult(99);
//        finish();
        AndPermission.with(Test2Activity.this)
                .requestCode(100)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(Test2Activity.this,rationale).show();
                    }
                })
                .permission(Manifest.permission.CAMERA)
                .callback(listener)
                .start();
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            LogUtil.i("requestCode onSucceed"+requestCode);
            if(AndPermission.hasPermission(Test2Activity.this,Manifest.permission.CAMERA)){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            LogUtil.i("requestCode onFailed"+requestCode);
            if(AndPermission.hasPermission(Test2Activity.this,Manifest.permission.CAMERA)){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }else {

            }
        }
    };

    @Override
    public void loadNoData() {
        Log.i("》》》 ", " loadnodata  ==== ");
        refreshLayout.finishLoadmore();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_test2;
    }

    @OnClick(R.id.tv_search_bg)
    void search() {
        Intent intent = new Intent(this, TestActivity3.class);
        int location[] = new int[2];
        search.getLocationOnScreen(location);
        LogUtil.i("location  x= " + location[0] + "  y= " + location[1]);
        intent.putExtra("x", location[0]);
        intent.putExtra("y", location[1]);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    protected void init() {
        setTitle("标题");
        goBack();
        p.getData(true);
        list1.add("http://m.iisbn.com/images_side/11_11.jpg");
        list1.add("http://m.iisbn.com/images_side/1.jpg");
        list1.add("http://m.iisbn.com/images_side/5.jpg");
        list1.add("http://m.iisbn.com/images_side/6.jpg");
        list1.add("http://m.iisbn.com/images_side/2.jpg");

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.format(new Date()); //date转string
            Date date = format.parse("2017-08-08 17:35:00"); //string转date
            long t1 = date.getTime();
            LogUtil.i("  timer t1 ===  " + t1);
            date = format.parse("2017-08-09 17:35:10");
            long t2 = date.getTime();
            LogUtil.i("  timer t2 ===  " + t2);
            dis = t2 - t1;
            LogUtil.i("  timer dis ===  " + dis);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take((int) (dis / 1000)+1).map(new Func1<Long, Long>() {
            @Override
            public Long call(Long aLong) {
//                LogUtil.i(" call  long   " + aLong);

                return dis - aLong *1000;
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Long>bindToLife())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
//                        LogUtil.i(" onNext  long   " + aLong);
                        getDiff(aLong);
                    }
                });


//        p.getData(false);
//        timer.schedule(task, 0,1000);


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
                ToastUtils.showToast(Test2Activity.this, "" + position);
            }
        });

        bgaAdapter = new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                ImgManager.loadImage(Test2Activity.this,model,itemView);
            }
        };
        banner.setAdapter(bgaAdapter);
        banner.setData(list1,
                Arrays.asList("","","","",""));

        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.i("onPageSelected  "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    private void getDiff(long dis) {
        if (dis <=0) {
            tv_timer.setText(0 + "天" + 0 + "小时" + 0 + "分" + 0 + "秒");
            if (timer != null && task != null) {
                timer.cancel();
                task.cancel();
            }
            return;
        }
//        LogUtil.i("  getDiff dis " + dis);
        long day = dis / (24 * 60 * 60 * 1000);
        String dayStr = day<10 ? "0"+day : ""+day;
        long hour = (dis / (60 * 60 * 1000) - day * 24);
        String hourStr = hour<10 ? "0"+hour : ""+hour;
        long min = ((dis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        String minStr = min<10 ? "0"+min : ""+min;
        long s = (dis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String secStr = s<10 ? "0"+s : ""+s;

        tv_timer.setText(dayStr + "天" + hourStr + "小时" + minStr + "分" + secStr + "秒");
//        dis -= 1000;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

//            getDiff();
        }
    };

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (task != null) {
            task.cancel();
        }
        if (timer != null) {
            timer.cancel();
        }

    }

    @Override
    public void showNetError() {
        super.showNetError();
        refreshLayout.finishLoadmore();
    }

    @Override
    public void loadData(HomeMiddleItem middleItem) {
        LogUtil.i(" middleItem ", " 》》》》  " + middleItem.toString());
    }

    @Override
    public void loadNovel(FindItem item) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        list.addAll((List<FindItem.Find>) item.pros1);
        Log.i("》》》》》  ", " list ====  " + list.size());
//        if (adapter2 == null) {
////            adapter = new FindAdapter(list,this);
//            adapter2 = new TestCustomAdapter(this, list, R.layout.adapter_keywords);
//            recyclerView.setAdapter(adapter2);
//        } else {
//            adapter2.notifyDataSetChanged();
//        }


    }

    @Override
    public void loadFindList(List<FindItem.Find> finds) {
        list1.add("http://m.iisbn.com/images_side/11_11.jpg");
        refreshLayout.finishRefresh();
        list.addAll(finds);
        Log.i("》》》》》  ", "loadFindList  list ====  " + list.size());
//        if (adapter2 == null) {
////            adapter = new FindAdapter(list,this);
//            adapter2 = new TestCustomAdapter(this, list, R.layout.adapter_keywords);
//            recyclerView.setAdapter(adapter2);
//        } else {
//            adapter2.notifyDataSetChanged();
//        }
        if (testActAdapter2 == null) {
//            adapter = new FindAdapter(list,this);
            testActAdapter2 = new TestActAdapter2(list,mActivity);
            recyclerView.setAdapter(testActAdapter2);
        } else {
            testActAdapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void login() {

    }

    @Override
    public void loadBanner(BannerItem item) {

    }

    private void stopRefreshAndLoad() {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
    }
}
