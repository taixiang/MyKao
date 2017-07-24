package com.kaoyan.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kaoyan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tx on 2017/7/24.
 * 加载空视图
 */

public class EmptyLayout extends FrameLayout {

    public static final int STATUS_HIDE = 1001;
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_NO_NET = 2;
    public static final int STATUS_NO_DATA = 3;

    private Context mContext;
    private int mEmptyStatus = STATUS_LOADING;



    @BindView(R.id.tvEmpty)
    TextView mTvEmptyMessage;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public EmptyLayout(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.layout_empty_loading,this);
//        View.inflate(mContext, R.layout.layout_empty_loading,this);
        ButterKnife.bind(this);
        switchEmptyView();
    }

    /**
     * 切换视图
     */
    private void switchEmptyView(){
        switch (mEmptyStatus){
            case STATUS_LOADING:
                setVisibility(VISIBLE);
                mTvEmptyMessage.setVisibility(GONE);
                progressBar.setVisibility(VISIBLE);
                break;
            case STATUS_NO_DATA:
            case STATUS_NO_NET:
                setVisibility(VISIBLE);
                mTvEmptyMessage.setVisibility(VISIBLE);
                progressBar.setVisibility(GONE);
                break;
            case STATUS_HIDE:
                setVisibility(GONE);
                break;
        }
    }

    /**
     * 隐藏视图
     */
    public void hide(){
        mEmptyStatus = STATUS_HIDE;
        switchEmptyView();
    }

    /**
     * 设置状态
     * @param emptyStatus
     */
    public void setEmptyStatus(int emptyStatus){
        mEmptyStatus = emptyStatus;
        switchEmptyView();
    }

    /**
     * 获取状态
     * @param emptyStatus
     * @return
     */
    public int getEmptyStatus(int emptyStatus){
        return emptyStatus;
    }


}
