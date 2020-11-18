package com.xzq.module_base.dialog.photo;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xzq.module_base.R;


/**
 * 底部选取对话框（目前支持2个tab）
 * Created by xzq on 2019/1/3.
 */
public class BottomSelectDialog extends Dialog implements View.OnClickListener {

    private TextView tvTitle;
    private TextView tvTab1;
    private TextView tvTab2;

    public BottomSelectDialog(@NonNull Context context) {
        super(context, R.style.BottomInDialogStyle);
        Window window = getWindow();
        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_bottom_select);
            window.setBackgroundDrawable(null);
            window.setGravity(Gravity.CENTER | Gravity.BOTTOM);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);

            tvTitle = findViewById(R.id.dbs_tv_title);
            tvTab1 = findViewById(R.id.dbs_btn_1);
            tvTab2 = findViewById(R.id.dbs_btn_2);
            tvTab1.setOnClickListener(this);
            tvTab2.setOnClickListener(this);
            findViewById(R.id.dbs_btn_cancel).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (v.getId() == R.id.dbs_btn_cancel) {
            return;
        }
        if (listener != null) {
            listener.onBottomTabClick(v.getId() == R.id.dbs_btn_1 ? 0 : 1);
        }
    }

    public void show(String... tab) {
        if (tab != null && tab.length >= 3) {
            tvTitle.setText(tab[0]);
            tvTitle.setVisibility(TextUtils.isEmpty(tab[0]) ? View.GONE : View.VISIBLE);
            tvTab1.setText(tab[1]);
            tvTab2.setText(tab[2]);
            show();
        }
    }

    private OnBottomTabClickListener listener;

    public void setOnBottomTabClickListener(OnBottomTabClickListener listener) {
        this.listener = listener;
    }

    public interface OnBottomTabClickListener {
        void onBottomTabClick(int position);
    }


}
