package com.xzq.module_base.utils;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.xzq.module_base.R;

/**
 * MyToast
 * Created by xzq on 2019/8/20.
 */
public class MyToast {

    public static void showSucceed(String msg) {
        show(R.layout.layout_toast_succeed, msg);
    }

    public static void showFailed(String msg) {
        show(R.layout.layout_toast_failed, msg);
    }

    public static void show(String msg) {
        show(R.layout.layout_toast_nor, msg);
    }

    private static void show(int layoutId, String msg) {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        View view = ToastUtils.showCustomShort(layoutId);
        TextView tvMsg = view.findViewById(R.id.tv_toast_msg);
        tvMsg.setText(msg);
    }
}
