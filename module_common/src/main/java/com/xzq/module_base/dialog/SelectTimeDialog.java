package com.xzq.module_base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.xzq.module_base.R;
import com.xzq.module_base.utils.DateUtils;
import com.xzq.module_base.utils.SizeUtils;
import com.xzq.module_base.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * SelectTimeDialog
 * Created by xzq on 2019/4/28.
 */
public class SelectTimeDialog extends Dialog {

    private WheelView wheelView;
    private WheelView wheelView2;
    private WheelView wheelView3;
    private final List<String> hourList = new ArrayList<>();
    private final List<String> minList = new ArrayList<>();
    private final List<String> secList = new ArrayList<>();

    public SelectTimeDialog(@NonNull Context context, String title) {
        super(context, R.style.BottomInDialogStyle);
        Window window = getWindow();
        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_select_time);
            int widthPixels = SizeUtils.widthPixels(context);
            window.setLayout(widthPixels, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_white_top_4dp));
            window.setGravity(Gravity.CENTER | Gravity.BOTTOM);


            this.<TextView>findViewById(R.id.tv_title).setText(title);
            findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (callback != null) {
                        int hourIndex = wheelView.getCurrentItem();
                        String hour = hourList.get(hourIndex);
                        int minIndex = wheelView2.getCurrentItem();
                        String min = minList.get(minIndex);
                        int secIndex = wheelView3.getCurrentItem();
                        String sec = secList.get(secIndex);
                        String time = hour + ":" + min + ":" + sec;
                        int intHour = StringUtils.toInt(hour);
                        int intMin = StringUtils.toInt(min);
                        int intSec = StringUtils.toInt(sec);
                        callback.onTimeCallback(time, intHour, intMin, intSec);
                    }
                }
            });
            for (int i = 0; i < 60; i++) {
                String e = i > 9 ? i + "" : "0" + i;
                if (i < 24) {
                    hourList.add(e);
                }
                minList.add(e);
                secList.add(e);
            }
            initWheelViews();
        }

    }

    private void initWheelViews() {
        String time = DateUtils.getCalendarStr("HH:mm:ss", System.currentTimeMillis() );
        String[] times = time.split(":");
        String hour = times[0];
        String min = times[1];
        String sec = times[2];

        wheelView = findViewById(R.id.wheelview);
        configWheelView(wheelView);
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });
        wheelView.setAdapter(new WheelAdapter() {
            @Override
            public int getItemsCount() {
                return hourList.size();
            }

            @Override
            public Object getItem(int index) {
                return hourList.get(index);
            }

            @Override
            public int indexOf(Object o) {
                return hourList.indexOf(o);
            }
        });
        wheelView.setCurrentItem(hourList.indexOf(hour));

        wheelView2 = findViewById(R.id.wheelview1);
        configWheelView(wheelView2);
        wheelView2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });
        wheelView2.setAdapter(new WheelAdapter() {
            @Override
            public int getItemsCount() {
                return minList.size();
            }

            @Override
            public Object getItem(int index) {
                return minList.get(index);
            }

            @Override
            public int indexOf(Object o) {
                return minList.indexOf(o);
            }
        });
        wheelView2.setCurrentItem(minList.indexOf(min));


        wheelView3 = findViewById(R.id.wheelview2);
        configWheelView(wheelView3);
        wheelView3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });
        wheelView3.setAdapter(new WheelAdapter() {
            @Override
            public int getItemsCount() {
                return secList.size();
            }

            @Override
            public Object getItem(int index) {
                return secList.get(index);
            }

            @Override
            public int indexOf(Object o) {
                return secList.indexOf(o);
            }
        });
        wheelView3.setCurrentItem(secList.indexOf(sec));

    }

    private void configWheelView(WheelView wheelView) {
        wheelView.setCyclic(false);
        wheelView.setDividerType(WheelView.DividerType.FILL);
        wheelView.setIsOptions(true);
        wheelView.setLineSpacingMultiplier(3f);
        wheelView.setGravity(Gravity.CENTER);
        wheelView.setTextXOffset(0);
        wheelView.setTextSize(16f);
        wheelView.setTextColorCenter(ContextCompat.getColor(wheelView.getContext(), R.color.color_main));
        wheelView.setDividerColor(Color.TRANSPARENT);
    }

    private OnTimeCallback callback;

    public void setCallback(OnTimeCallback callback) {
        this.callback = callback;
    }

    public interface OnTimeCallback {
        void onTimeCallback(String time, int hour, int min, int sec);
    }

}
