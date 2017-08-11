package com.kaoyan.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kaoyan.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by tx on 2017/8/11.
 */

public class CustomHeader extends LinearLayout implements RefreshHeader{

    private ImageView imageView;
    ObjectAnimator mAnimatorRotate;

    public CustomHeader(Context context) {
        super(context);
        init(context);
    }

    public CustomHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        setGravity(Gravity.CENTER);
        imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {

        imageView.setAnimation(new RotateAnimation(0f,45f));

        mAnimatorRotate = ObjectAnimator.ofFloat(imageView, "rotation", 0.0f,360.0f);
        mAnimatorRotate.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorRotate.setRepeatCount(2);
        mAnimatorRotate.setDuration(2000);
        mAnimatorRotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });
        mAnimatorRotate.start();
        mAnimatorRotate.setIntValues();
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        if(mAnimatorRotate != null){
            mAnimatorRotate.cancel();
        }
        return 500;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState){
            case None:
                break;
            default:
                break;
        }
    }
}
