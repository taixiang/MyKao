package com.kaoyan.module;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.api.IApi;
import com.kaoyan.base.BaseActivity;
import com.kaoyan.base.IBaseView;
import com.kaoyan.model.BannerItem;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.module.test.TestPresenter3;
import com.kaoyan.utils.ImgManager;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.view.IMainView;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by tx on 2017/7/24.
 */

public class TestActivity3 extends BaseActivity implements IMainView {

    @BindView(R.id.tv_search_bg)
    EditText mSearchBGTxt;

    @BindView(R.id.frame_content_bg)
    FrameLayout mContentFrame;

    @BindView(R.id.tv_hint)
    TextView mHintTxt;
    @BindView(R.id.tv_search)
    TextView mSearchTxt;
    @BindView(R.id.iv_arrow)
    ImageView mArrowImg;
    private boolean finishing;

    TestPresenter3 t;

    @BindView(R.id.photoView)
    ImageView photoView;

    @BindView(R.id.ultra_viewpager)
    ViewPager viewPager;
    private List<String> list = new ArrayList<>();
    UlViewPagerAdapter adapter;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_test3;
    }

    @Override
    protected void init() {
        ImgManager.loadImage(mActivity,"http://m.iisbn.com/images_side/1.jpg",photoView);
        mSearchBGTxt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mSearchBGTxt.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                performEnterAnimation();
            }
        });
        t = new TestPresenter3(this);
        t.getData(false);

//        adapter = new UlViewPagerAdapter();
//        viewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
//        viewPager.setAdapter(adapter);

//        viewPager.setMultiScreen(0.9f);


//        viewPager.initIndicator();
//        viewPager.getIndicator()
//                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
//                .setFocusColor(Color.RED)
//                .setNormalColor(Color.GRAY)
//                .setFocusResId(R.drawable.shape_home_indicator_selected)
//                .setNormalResId(R.drawable.shape_home_indicator_unselected)
//                .setIndicatorPadding(10)
//                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()))
//                .build();
//        viewPager.setInfiniteLoop(true);
//        viewPager.setAutoScroll(2000);

    }

    private class UlViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.adapter_ulviewpager,null);
            ImageView imageView = view.findViewById(R.id.iv);
            ImgManager.loadImage(mActivity,list.get(position),imageView);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public float getPageWidth(int position) {
            return 0.9f;
        }
    }

    private void performEnterAnimation() {
        float originY = getIntent().getIntExtra("y", 0);

        int location[] = new int[2];
        mSearchBGTxt.getLocationOnScreen(location);
        LogUtil.i("location mSearchBGTxt  x= "+location[0]+"  y= "+location[1]);

        final float translateY = originY - (float) location[1];
        LogUtil.i("location translateY= "+translateY);

        LogUtil.i("location mSearchBGTxt.getY()= "+mSearchBGTxt.getY());

        LogUtil.i("location mSearchBGTxt.setY()= "+mSearchBGTxt.getY() + translateY);
        LogUtil.i("location mHintTxt.setY()= "+mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mHintTxt.getHeight()) / 2);
        LogUtil.i("location mSearchTxt.setY()= "+mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mSearchTxt.getHeight()) / 2);
        LogUtil.i("location mArrowImg.setY()= "+mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mArrowImg.getHeight()) / 2);


        //放到前一个页面的位置
        mSearchBGTxt.setY(mSearchBGTxt.getY() + translateY);
        mHintTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mHintTxt.getHeight()) / 2);
        mSearchTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mSearchTxt.getHeight()) / 2);
        float top = getResources().getDisplayMetrics().density * 20;
        LogUtil.i("location top= "+top);
        final ValueAnimator translateVa = ValueAnimator.ofFloat(mSearchBGTxt.getY(), top);
        translateVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mSearchBGTxt.setY((Float) valueAnimator.getAnimatedValue());

                mArrowImg.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mArrowImg.getHeight()) / 2);
                mHintTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mHintTxt.getHeight()) / 2);
                mSearchTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mSearchTxt.getHeight()) / 2);

            }
        });

        translateVa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                InputMethodManager inputManager = (InputMethodManager) mSearchBGTxt.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(mSearchBGTxt, 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

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
                mSearchTxt.setAlpha((Float) valueAnimator.getAnimatedValue());
                mArrowImg.setAlpha((Float) valueAnimator.getAnimatedValue());
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
                mArrowImg.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mArrowImg.getHeight()) / 2);
                mHintTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mHintTxt.getHeight()) / 2);
                mSearchTxt.setY(mSearchBGTxt.getY() + (mSearchBGTxt.getHeight() - mSearchTxt.getHeight()) / 2);
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

                mArrowImg.setAlpha((Float) valueAnimator.getAnimatedValue());
                mSearchTxt.setAlpha((Float) valueAnimator.getAnimatedValue());
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
        LogUtil.i("  login login ");
        list.add("http://m.iisbn.com/images_side/11_11.jpg");
        list.add("http://m.iisbn.com/images_side/1.jpg");
        list.add("http://m.iisbn.com/images_side/5.jpg");
        list.add("http://m.iisbn.com/images_side/6.jpg");
        list.add("http://m.iisbn.com/images_side/2.jpg");
        adapter = new UlViewPagerAdapter();
        viewPager.setAdapter(adapter);
    }

    @Override
    public void loadBanner(BannerItem item) {

    }
}
