package com.kaoyan.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.kaoyan.R;
import com.kaoyan.utils.ToastUtils;

import razerdp.basepopup.BasePopupWindow;

import static java.security.AccessController.getContext;

/**
 * Created by tx on 2017/8/16.
 */

public class SlideFromBottomPopup extends BasePopupWindow implements View.OnClickListener {

    private View popupView;
    private Context context;

    public SlideFromBottomPopup(Activity context) {
        super(context);
        this.context = context;
        bindEvent();
    }

    @Override
    protected Animation initShowAnimation() {
        return getTranslateAnimation(250 * 2, 0, 300);
    }

    @Override
    protected Animation initExitAnimation() {
        return getTranslateAnimation(0,250 * 3, 300);
    }

    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.popup_slide_from_bottom, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView != null) {
            popupView.findViewById(R.id.tx_1).setOnClickListener(this);
            popupView.findViewById(R.id.tx_2).setOnClickListener(this);
            popupView.findViewById(R.id.tx_3).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tx_1:

                break;
            case R.id.tx_2:

                break;
            case R.id.tx_3:

                break;
            default:
                break;
        }

    }
}