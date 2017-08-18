package com.kaoyan.widget;


import android.view.View;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    View getCardViewAt(int position);

    int getCount();
}
