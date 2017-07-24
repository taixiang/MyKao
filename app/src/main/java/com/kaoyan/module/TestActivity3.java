package com.kaoyan.module;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.kaoyan.R;
import com.kaoyan.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by tx on 2017/7/24.
 */

public class TestActivity3 extends BaseActivity {

    @BindView(R.id.search)
    EditText mSearchBGTxt;

    @BindView(R.id.mContentFrame)
    LinearLayout mContentFrame;
    private boolean finishing;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_test3;
    }

    @Override
    protected void init() {
        mSearchBGTxt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mSearchBGTxt.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void performEnterAnimation() {
        float originY = getIntent().getIntExtra("y", 0);

        int location[] = new int[2];
        mSearchBGTxt.getLocationOnScreen(location);

        final float translateY = originY - (float) location[1];

        //放到前一个页面的位置
        mSearchBGTxt.setY(mSearchBGTxt.getY() + translateY);
//        mHintTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mHintTxt.getHeight()) / 2);
//        mSearchTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mSearchTxt.getHeight()) / 2);
        float top = getResources().getDisplayMetrics().density * 20;
        final ValueAnimator translateVa = ValueAnimator.ofFloat(mSearchBGTxt.getY(), top);
        translateVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSearchBGTxt.setY((Float) valueAnimator.getAnimatedValue());

//                mArrowImg.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mArrowImg.getHeight()) / 2);
//                mHintTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mHintTxt.getHeight()) / 2);
//                mSearchTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mSearchTxt.getHeight()) / 2);
            }
        });

        ValueAnimator scaleVa = ValueAnimator.ofFloat(1, 0.8f);
        scaleVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSearchBGTxt.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });

        ValueAnimator alphaVa = ValueAnimator.ofFloat(0, 1f);
        alphaVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mContentFrame.setAlpha((Float) valueAnimator.getAnimatedValue());
//                mSearchTxt.setAlpha((Float) valueAnimator.getAnimatedValue());
//                mArrowImg.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });

        alphaVa.setDuration(500);
        translateVa.setDuration(500);
        scaleVa.setDuration(500);

        alphaVa.start();
        translateVa.start();
        scaleVa.start();

    }

    @Override
    public void onBackPressed() {
        if (!finishing){
            finishing = true;
            performExitAnimation();
        }
    }

    private void performExitAnimation() {
        float originY = getIntent().getIntExtra("y", 0);

        int location[] = new int[2];
        mSearchBGTxt.getLocationOnScreen(location);

        final float translateY = originY - (float) location[1];


        final ValueAnimator translateVa = ValueAnimator.ofFloat(mSearchBGTxt.getY(), mSearchBGTxt.getY()+translateY);
        translateVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSearchBGTxt.setY((Float) valueAnimator.getAnimatedValue());
//                mArrowImg.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mArrowImg.getHeight()) / 2);
//                mHintTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mHintTxt.getHeight()) / 2);
//                mSearchTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mSearchTxt.getHeight()) / 2);
            }
        });
        translateVa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        ValueAnimator scaleVa = ValueAnimator.ofFloat(0.8f, 1f);
        scaleVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSearchBGTxt.setScaleX((Float) valueAnimator.getAnimatedValue());
            }
        });

        ValueAnimator alphaVa = ValueAnimator.ofFloat(1, 0f);
        alphaVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mContentFrame.setAlpha((Float) valueAnimator.getAnimatedValue());

//                mArrowImg.setAlpha((Float) valueAnimator.getAnimatedValue());
//                mSearchTxt.setAlpha((Float) valueAnimator.getAnimatedValue());
            }
        });


        alphaVa.setDuration(500);
        translateVa.setDuration(500);
        scaleVa.setDuration(500);

        alphaVa.start();
        translateVa.start();
        scaleVa.start();

    }

}
