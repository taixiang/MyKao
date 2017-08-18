package com.kaoyan;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.FileProvider;
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
import com.kaoyan.module.Test2Activity;
import com.kaoyan.module.act.ActFragment;
import com.kaoyan.module.home.HomeFragment;
import com.kaoyan.module.me.MeFragment;
import com.kaoyan.utils.CommonUtil;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.utils.ToastUtils;
import com.kaoyan.view.IMainView;
import com.kaoyan.widget.CommonDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
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

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Environment.getExternalStorageDirectory;


public class MainActivity extends BaseActivity implements IMainView {
    FragmentTabHost tabHost;

    @BindView(android.R.id.tabs)
    TabWidget tabWidget;

    @BindView(R.id.container)
    LinearLayout container;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {HomeFragment.class, CourseFragment.class, ActFragment.class, MeFragment.class};
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.selector_tab_img, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "选课", "活动", "我的"};

    private long exitTime = 0;
    private CommonDialog.Builder builder;
    private DownloadManager downloadManager;
    private long downQueue;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        EventBus.getDefault().register(this);
        String test = "test";
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

        writePermission();
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(receiver, filter);
        update();
    }

    private void update() {
        final String fileName = "/kaoyan" + "/apkDownload/";
        File file = new File(fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        LogUtil.i(" path :: " + Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.kaoyan" + "/");
        builder = new CommonDialog.Builder(MainActivity.this)
                .setTitle("下载")
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                })
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (AndPermission.hasPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            LogUtil.i(" begin download....   ");
                            DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://download.fir.im/v2/app/install/595c5959959d6901ca0004ac?download_token=1a9dfa8f248b6e45ea46bc5ed96a0a9e&source=update"));
                            request.setDestinationInExternalPublicDir(fileName, "test.apk");
                            request.setTitle(getString(R.string.app_name));
                            request.setDescription(getString(R.string.app_name));
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                            downloadManager = (DownloadManager) getSystemService(mActivity.DOWNLOAD_SERVICE);
                            downQueue = downloadManager.enqueue(request);
                        } else {
                            ToastUtils.showToast(MainActivity.this, "权限被禁用");
                        }
                    }
                });
        builder.show();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (reference == downQueue) {
                LogUtil.i(" onReceive  " + reference);
                Intent install = new Intent(Intent.ACTION_VIEW);
                Uri downloadFileUri = downloadManager
                        .getUriForDownloadedFile(reference);
                String fileName = "";
                Cursor c = downloadManager.query(new DownloadManager.Query().setFilterById(reference));
                if (c != null) {
                    c.moveToFirst();
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        int fileUriIdx = c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
                        String fileUri = c.getString(fileUriIdx);
                        fileName = Uri.parse(fileUri).getPath();
                        File file = new File(fileName);
                        LogUtil.i("downloadFileUri  fileNameN： filename  " + fileName);
//                        File file = new File("/kaoyan"+"/apkDownload/test.apk");
                        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Uri apkUri = FileProvider.getUriForFile(MainActivity.this, "com.kaoyan.fileprovide", file);
                        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        install.setDataAndType(apkUri, "application/vnd.android.package-archive");
                        LogUtil.i("downloadFileUri  fileNameN：" + file.getAbsolutePath());
                        LogUtil.i("downloadFileUri  fileNameN：apkUri " + apkUri.getPath());
                        context.startActivity(install);
                    } else {
                        int fileNameIdx = c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                        fileName = c.getString(fileNameIdx);
                        File file = new File(fileName);
                        LogUtil.i("downloadFileUri  fileName：" + fileName);
                        LogUtil.i("downloadFileUri  file：" + file.getAbsolutePath());
                        c.close();
                        LogUtil.i(" downloadFileUri " + downloadFileUri.toString());
                        install.setDataAndType(Uri.parse("file://" + fileName), "application/vnd.android.package-archive");
                        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(install);
                    }
                }
            }
        }
    };

    private void writePermission() {
        AndPermission.with(MainActivity.this)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(MainActivity.this, rationale).show();
                    }
                })
                .permission(WRITE_EXTERNAL_STORAGE)
                .callback(listener)
                .start();
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            LogUtil.i("requestCode onSucceed" + requestCode);
            if (AndPermission.hasPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            LogUtil.i("requestCode onFailed" + requestCode);
            if (AndPermission.hasPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
        unregisterReceiver(receiver);

    }

    private void setUpFragmentTabHost() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(MainActivity.this, MainActivity.this.getSupportFragmentManager(), android.R.id.tabcontent);
        //得到fragment的个数
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            tabHost.addTab(tabSpec, fragmentArray[i], null);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_item_view, null);
        LinearLayout container = view.findViewById(R.id.container);
//        if(index == 0){
//            container.setClipChildren(true);
//        }
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
        if (resultCode == 99) {
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

    private void exit() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            LogUtil.i(" exittime " + exitTime);
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
