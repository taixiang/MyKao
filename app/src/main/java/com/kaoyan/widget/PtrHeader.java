package com.kaoyan.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * Created by tx on 2017/8/14.
 */

public class PtrHeader extends ClassicsHeader {

    public PtrHeader(Context context) {
        super(context);
        init();
    }

    public PtrHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PtrHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mLastUpdateText.setVisibility(GONE);
        mHeaderText.setVisibility(GONE);
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        super.onStateChanged(refreshLayout, oldState, newState);
        mLastUpdateText.setVisibility(GONE);
        mHeaderText.setVisibility(GONE);
    }
}
