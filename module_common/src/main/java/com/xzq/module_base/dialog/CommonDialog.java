package com.xzq.module_base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.xzq.module_base.R;
import com.xzq.module_base.utils.SizeUtils;

/**
 * 通用对话框
 * Created by xzq on 2019/8/16.
 */
public class CommonDialog extends Dialog implements View.OnClickListener {

    private static final int PADDING_BOTTOM = AdaptScreenUtils.pt2Px(30);

    private CommonDialog(@NonNull Context context,
                         String title,
                         String msg,
                         boolean isDoubleBtn,
                         String cancelMsg,
                         String okMsg) {
        super(context, R.style.DialogScaleStyle);
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_dialog_common));
        window.setGravity(Gravity.CENTER);
        int widthPixels = (int) (SizeUtils.widthPixels(context) * 0.78);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_common, null);
        setContentView(contentView, new ViewGroup.LayoutParams(widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT));

        boolean isTitleEmpty = TextUtils.isEmpty(title);
        TextView tvTitle = findViewById(R.id.dlg_tv_title);
        tvTitle.setText(title);
        tvTitle.setVisibility(isTitleEmpty ? View.GONE : View.VISIBLE);

        TextView tvMsg = findViewById(R.id.dlg_tv_content);
        tvMsg.setText(msg);
        tvMsg.setPadding(tvMsg.getPaddingStart(), tvMsg.getPaddingTop(),
                tvMsg.getPaddingEnd(), isTitleEmpty ? PADDING_BOTTOM : PADDING_BOTTOM / 2);
        tvMsg.setTextColor(ContextCompat.getColor(context, isTitleEmpty ? R.color.color_666666 : R.color.color_888888));

        TextView tvCancel = findViewById(R.id.dlg_tv_cancel);
        tvCancel.setVisibility(isDoubleBtn ? View.VISIBLE : View.GONE);
        if (isDoubleBtn) {
            tvCancel.setText(!TextUtils.isEmpty(cancelMsg) ? cancelMsg : "取消");
            tvCancel.setOnClickListener(this);
        }

        TextView tvOk = findViewById(R.id.dlg_tv_ok);
        tvOk.setOnClickListener(this);
        tvOk.setText(!TextUtils.isEmpty(okMsg) ? okMsg : "确定");
    }

    public static void show(Context context,
                            String title,
                            String msg,
                            boolean isDoubleBtn,
                            String cancelMsg,
                            String okMsg,
                            OnOkClickListener okClickListener,
                            OnCancelClickListener cancelClickListener) {
        CommonDialog commonDialog = new CommonDialog(context, title, msg, isDoubleBtn, cancelMsg, okMsg);
        commonDialog.setOkClickListener(okClickListener);
        commonDialog.setCancelClickListener(cancelClickListener);
        commonDialog.show();
    }

    public static void showSingle(Context context, String msg, OnOkClickListener okClickListener) {
        show(context, null, msg, false, null, null, okClickListener, null);
    }

    public static void showDouble(Context context, String msg, OnOkClickListener okClickListener) {
        showDouble(context, msg, okClickListener, null);
    }

    public static void showDouble(Context context, String msg,
                                  OnOkClickListener okClickListener, OnCancelClickListener cancelClickListener) {
        showDouble(context, null, msg, okClickListener, cancelClickListener);
    }

    public static void showDouble(Context context, String title, String msg, OnOkClickListener okClickListener) {
        showDouble(context, title, msg, okClickListener, null);
    }

    public static void showDouble(Context context, String title, String msg,
                                  OnOkClickListener okClickListener, OnCancelClickListener cancelClickListener) {
        show(context, title, msg, true, null, null, okClickListener, cancelClickListener);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (v.getId() == R.id.dlg_tv_cancel) {
            if (cancelClickListener != null) {
                cancelClickListener.onDialogCancelClicked();
            }
        } else {
            if (okClickListener != null) {
                okClickListener.onDialogOkClicked();
            }
        }
    }

    private OnOkClickListener okClickListener;
    private OnCancelClickListener cancelClickListener;

    private void setOkClickListener(OnOkClickListener okClickListener) {
        this.okClickListener = okClickListener;
    }

    private void setCancelClickListener(OnCancelClickListener cancelClickListener) {
        this.cancelClickListener = cancelClickListener;
    }

    public interface OnOkClickListener {
        void onDialogOkClicked();
    }

    public interface OnCancelClickListener {
        void onDialogCancelClicked();
    }
}
