package com.kaoyan;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.kaoyan.base.BaseActivity;
import com.kaoyan.event.LoginEvent;
import com.kaoyan.fragment.BannerItemFragment;
import com.kaoyan.fragment.TestFragment;
import com.kaoyan.model.BannerItem;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.module.Course.CourseFragment;
import com.kaoyan.module.act.ActFragment;
import com.kaoyan.module.home.HomeFragment;
import com.kaoyan.module.me.MeFragment;
import com.kaoyan.utils.CommonUtil;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.view.IMainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import io.reactivex.schedulers.Schedulers;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;


public class MainActivity extends BaseActivity implements IMainView{
    FragmentTabHost tabHost;

    @BindView(android.R.id.tabs)
    TabWidget tabWidget;


    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {HomeFragment.class,CourseFragment.class,ActFragment.class,MeFragment.class};
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.selector_tab_img,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "选课", "活动", "我的"};

    private long exitTime = 0;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        EventBus.getDefault().register(this);

          setUpFragmentTabHost();
//        Rect outRect = new Rect();
//        getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
//        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) container.getLayoutParams();
//        params.height = outRect.bottom - outRect.top;
//        LogUtil.i("  params  height=  "+params.height+"  window height"+ CommonUtil.getWidthAndHeight(mActivity)[1]);


        //线程调度
//        Observable
//                .create(new Observable.OnSubscribe<String>() {
//                    @Override
//                    public void call(Subscriber<? super String> subscriber) {
//                        LogUtil.i( "rx_call" , Thread.currentThread().getName()  );
//
//                        subscriber.onNext( "dd");
//                        subscriber.onCompleted();
//                    }
//                }).map(new Func1<String, String >() {
//            @Override
//            public String call(String s) {
//                LogUtil.i( "rx_map" , Thread.currentThread().getName()  );
//                return s + "88";
//            }
//        })
//                .subscribeOn(rx.schedulers.Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//
//
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        LogUtil.i( "rx_subscribe" , Thread.currentThread().getName()  );
//                    }
//                }) ;


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }

    private void setUpFragmentTabHost(){
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(MainActivity.this,MainActivity.this.getSupportFragmentManager(),android.R.id.tabcontent);
        //得到fragment的个数
        int count = fragmentArray.length;
        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            tabHost.addTab(tabSpec, fragmentArray[i], null);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);
        return view;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.i("  loginSuccess  resultcode  " + resultCode);
        if(resultCode == 99){
            tabHost.setCurrentTab(3);
        }
    }

    @Override
    public void loadData(HomeMiddleItem middleItem) {
    }

    @Override
    public void loadNovel(FindItem item) {
        


    }

    @Override
    public void loadFindList(List<FindItem.Find> finds) {

    }

    @Override
    public void login() {

    }

    @Override
    public void loadBanner(BannerItem item) {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        exit();
    }

    private void exit(){
        if(System.currentTimeMillis() - exitTime > 2000){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            LogUtil.i(" exittime "+exitTime);
            exitTime = System.currentTimeMillis();
        }else {
            finish();
            System.exit(0);
        }
    }
}
