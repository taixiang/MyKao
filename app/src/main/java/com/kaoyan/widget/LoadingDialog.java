package com.kaoyan.widget;

import android.app.Dialog;
import android.app.ProgressDialog;
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
    private Dialog dialog;
//    public  LoadingDialog(Context context){
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_loading,null);
//        ImageView imageView = view.findViewById(R.id.img);
//        Animation animation = AnimationUtils.loadAnimation(context,R.anim.loading_animation);
//        imageView.startAnimation(animation);
//        dialog = new Dialog(context,R.style.MyDialog);
//        dialog.setContentView(view);
//        dialog.show();
//    }

    public LoadingDialog(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_loading,null);
        ImageView imageView = view.findViewById(R.id.img);
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.loading_animation);
        imageView.startAnimation(animation);
        dialog = new Dialog(context,R.style.MyDialog);
        dialog.setContentView(view);
        dialog.show();
//        if(null != dialog){
//            dialog.show();
//        }
    }

    public void dismissDialog(){
//        dialog.cancel();
            dialog.dismiss();
    }



}
