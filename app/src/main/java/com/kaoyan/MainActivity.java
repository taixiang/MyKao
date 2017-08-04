package com.kaoyan;

import android.graphics.Rect;
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
import com.kaoyan.fragment.BannerItemFragment;
import com.kaoyan.fragment.TestFragment;
import com.kaoyan.model.BannerItem;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.module.Course.CourseFragment;
import com.kaoyan.module.home.HomeFragment;
import com.kaoyan.module.me.MeFragment;
import com.kaoyan.utils.CommonUtil;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.view.IMainView;

import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements IMainView{
    FragmentTabHost tabHost;

    @BindView(android.R.id.tabs)
    TabWidget tabWidget;


    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {HomeFragment.class,CourseFragment.class,TestFragment.class,MeFragment.class};
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

          setUpFragmentTabHost();
//        Rect outRect = new Rect();
//        getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
//        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) container.getLayoutParams();
//        params.height = outRect.bottom - outRect.top;
//        LogUtil.i("  params  height=  "+params.height+"  window height"+ CommonUtil.getWidthAndHeight(mActivity)[1]);
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

    private void exit(){
        if(System.currentTimeMillis() - exitTime > 2000){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            LogUtil.i(" exittime "+exitTime);
            exitTime = System.currentTimeMillis();
        }else {
            finish();
        }
    }
}
