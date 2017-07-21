package com.kaoyan;

import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.kaoyan.base.BaseActivity;
import com.kaoyan.fragment.TestFragment;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.view.IMainView;


public class MainActivity extends BaseActivity implements IMainView{
    FragmentTabHost tabHost;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {TestFragment.class,TestFragment.class,TestFragment.class,TestFragment.class};
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "选课", "活动", "我的"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        setUpFragmentTabHost();
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
    public void loadNoData() {

    }
}
