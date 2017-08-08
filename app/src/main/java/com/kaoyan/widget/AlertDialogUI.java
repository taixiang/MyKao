package com.kaoyan.widget;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by tx on 2017/8/8.
 */

public class AlertDialogUI {
    private AlertDialog ad;
    public AlertDialogUI(Context context) {
        if(ad == null){
            ad = new AlertDialog.Builder(context).create();
        }
    }
}
