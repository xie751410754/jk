package com.xzq.module_base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.view.WheelView;
import com.xzq.module_base.R;
import com.xzq.module_base.utils.SizeUtils;

import java.util.List;


/**
 * 通用WheelView选取器
 * Created by xzq on 2019/1/2.
 */
public class CommonWheelDialog<T> extends Dialog implements View.OnClickListener {

    private List<T> data;
    private TextView mTvTitle;
    private TextView mBtnOk;
    private View vTarget;

    public CommonWheelDialog(@NonNull Context context, List<T> data, String title, View vTarget) {
        super(context, R.style.BottomInDialogStyle);
        this.data = data;
        this.vTarget = vTarget;
        Window window = getWindow();
        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_wheel);
            int widthPixels = SizeUtils.widthPixels(context);
            window.setLayout(widthPixels, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_white_top_4dp));
            window.setGravity(Gravity.CENTER | Gravity.BOTTOM);

            findViewById(R.id.btn_cancel).setOnClickListener(this);
            mBtnOk = findViewById(R.id.btn_ok);
            mBtnOk.setOnClickListener(this);
            mTvTitle = findViewById(R.id.tv_title);
            mTvTitle.setText(title);

            WheelView wheelView = findViewById(R.id.wheelview);
            wheelView.setCyclic(false);
            wheelView.setDividerType(WheelView.DividerType.FILL);
            wheelView.setIsOptions(true);
            wheelView.setLineSpacingMultiplier(3f);
            wheelView.setGravity(Gravity.CENTER);
            wheelView.setTextSize(16f);
            wheelView.setTextXOffset(0);
//            wheelView.setDividerColor(ContextCompat.getColor(context, R.color.color_d9dee8));
            wheelView.setDividerColor(Color.TRANSPARENT);
            wheelView.setTextColorCenter(ContextCompat.getColor(context, R.color.color_main));

            wheelView.setAdapter(new WheelAdapter() {
                @Override
                public int getItemsCount() {
                    return data.size();
                }

                @Override
                public Object getItem(int index) {
                    return data.get(index).toString();
                }

                @Override
                public int indexOf(Object o) {
                    return data.indexOf(o);
                }
            });
            wheelView.setOnItemSelectedListener(index -> currentIndex = index);

            if (vTarget != null && vTarget.getTag(vTarget.getId()) instanceof Integer) {
                int seleIndex = (int) vTarget.getTag(vTarget.getId());
                wheelView.setCurrentItem(seleIndex);
            }
        }

    }

    /**
     * 标题字体
     */
    public void setTitleTextSize(int size) {
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PT, size);
    }

    /**
     * 设置确定按钮文字
     */
    public void setBtnOkText(String ok) {
        mBtnOk.setText(ok);
    }

    private int currentIndex = 0;

    private OnItemSelectedListener<T> listener;

    public void setListener(OnItemSelectedListener<T> listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (v.getId() == R.id.btn_ok) {
            if (vTarget != null) {
                vTarget.setTag(vTarget.getId(), currentIndex);
            }
            if (listener != null) {
                listener.onItemSelected(data.get(currentIndex), currentIndex);
            }
        }
    }

    public interface OnItemSelectedListener<T> {
        void onItemSelected(T t, int index);
    }

    public static <T> void show(Context context, String title, List<T> list, OnItemSelectedListener<T> listener) {
        show(context, null, title, list, listener);
    }

    public static <T> void show(Context context, View view, String title, List<T> list, OnItemSelectedListener<T> listener) {
        CommonWheelDialog<T> wheelDialog = new CommonWheelDialog<>(context,
                list, title, view);
        wheelDialog.setListener(listener);
        wheelDialog.show();
    }

}
