package com.example.xiaomage.xingvoices.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.example.xiaomage.xingvoices.App;
import com.example.xiaomage.xingvoices.R;
import com.example.xiaomage.xingvoices.api.OnDialogButtonClickListener;

/**
 * Created by xiaomage on 2017/5/24.
 */

public class AlertUtil extends AlertDialog{

    protected AlertUtil(@NonNull Context context) {
        super(context);
    }

    public static void showAlert(Context context,String title, String message){
        new AlertDialog.Builder(context).setTitle(title).setMessage(message)
                .setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void showAlert(Context context, String title, String message, final OnDialogButtonClickListener listener){
        new AlertDialog.Builder(context).setTitle(title).setMessage(message)
                .setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        listener.onDialogButtonClick();
                    }
                }).show();
    }
}
