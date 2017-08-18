package com.kaoyan.widget;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.base.BaseActivity;
import com.kaoyan.utils.ImgManager;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<View> mViews;
    private List<String> mData;
    private BaseActivity activity;
    private float mBaseElevation;

    public CardPagerAdapter(BaseActivity activity) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        this.activity = activity;
    }

    public void addCardItem(String item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public View getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter_ulviewpager, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        LinearLayout cardView = (LinearLayout) view.findViewById(R.id.container);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getElevation();
        }
        cardView.setElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(String item, View view) {
        ImageView titleTextView = view.findViewById(R.id.iv);
        ImgManager.loadImage(activity,"",titleTextView);
    }

}
