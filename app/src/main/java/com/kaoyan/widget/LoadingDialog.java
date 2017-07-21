package com.kaoyan.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.kaoyan.R;

/**
 * Created by tx on 2017/7/21.
 */

public class LoadingDialog {
    private static Dialog dialog;
    public LoadingDialog(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_loading,null);
        ImageView imageView = view.findViewById(R.id.img);
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.loading_animation);
        imageView.startAnimation(animation);
        if(null == dialog){
            dialog = new Dialog(context);
            dialog.setContentView(view);
        }else if(!dialog.isShowing()){
            dialog.show();
        }

    }

    public void showDialog(){
        if(null != dialog){
            dialog.show();
        }
    }

    public static void dismissDialog(){
        if(null != dialog){
            dialog.dismiss();
        }
    }



}
