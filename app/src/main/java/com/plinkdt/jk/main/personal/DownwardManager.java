package com.plinkdt.jk.main.personal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.plinkdt.jk.R;
import com.plinkdt.jk.utils.CommonUtils;
import com.xzq.module_base.dto.TabDto;
import com.xzq.module_base.utils.AnimatorUtils;
import com.xzq.module_base.utils.DateUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.divider.Divider;

import java.util.Date;
import java.util.List;

/**
 * DownwardManager
 * Created by xzq on 2019/12/2.
 */
public class DownwardManager {

    private final static int OFFSET = AdaptScreenUtils.pt2Px(100);
    private final static int DURATION = 300;
    private ViewGroup vgParent;
    private View child;
    private View vTimeParent;
    private RecyclerView childList;
    private final ChildAdapter childAdapter = new ChildAdapter();
    private final OnTabSelectedListener listener;
    private Activity me;
    private ViewGroup contentView;
    private TextView tvStartTime;
    private TextView tvEndTime;

    public DownwardManager(Activity me, ViewGroup vgParent, OnTabSelectedListener listener) {
        this.me = me;
        this.vgParent = vgParent;
        this.listener = listener;
        contentView = me.findViewById(android.R.id.content);
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);
        View.OnKeyListener onKeyBackListener = (v, keyCode, event) -> {
            if (isShow && keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_DOWN) {
                hide();
                return true;
            }
            return false;
        };
        contentView.setOnKeyListener(onKeyBackListener);
        vgParent.setOnClickListener(v -> hide());
    }

    public void show(List<TabDto> data, TabDto select, View v) {
        if (child == null) {
            child = LayoutInflater.from(vgParent.getContext())
                    .inflate(R.layout.layout_downward, vgParent, false);
            vTimeParent = child.findViewById(R.id.llyt_time_parent);
            childList = child.findViewById(R.id.childList);
            childList.setLayoutManager(new LinearLayoutManager(vgParent.getContext()));
            childList.setAdapter(childAdapter);
            childList.addItemDecoration(Divider.with(vgParent.getContext())
                    .pxPadding(AdaptScreenUtils.pt2Px(15))
                    .colorRes(R.color.color_e7eaf0)
                    .build());
            childAdapter.setListener(new ChildAdapter.OnTabChangeListener() {
                @Override
                public void onTabChange(TabDto tab) {
                    listener.onTabSelected(tab, v);
                    vTimeParent.setVisibility(tab.isCustomTime() ? View.VISIBLE : View.GONE);
                }

                @Override
                public void onItemClicked(View v, TabDto data, int pos) {
                    childAdapter.checkTab(data);
                }
            });
            tvStartTime = child.findViewById(R.id.tv_select_start_time);
            tvEndTime = child.findViewById(R.id.tv_select_end_time);

            tvStartTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonUtils.showBottomTimePicker(DateUtils.PATTERN_YYYY_MM_DD_1,
                            v, me, contentView, new OnTimeSelectListener() {
                                @Override
                                public void onTimeSelect(Date date, View v) {
                                    String time = DateUtils.getCalendarStr(DateUtils.PATTERN_YYYY_MM_DD_1, date);
                                    tvStartTime.setText(time);
                                    callbackCustomTime();
                                }
                            }, null);
                }
            });
            tvEndTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonUtils.showBottomTimePicker(DateUtils.PATTERN_YYYY_MM_DD_1,
                            v, me, contentView, new OnTimeSelectListener() {
                                @Override
                                public void onTimeSelect(Date date, View v) {
                                    String time = DateUtils.getCalendarStr(DateUtils.PATTERN_YYYY_MM_DD_1, date);
                                    tvEndTime.setText(time);
                                    callbackCustomTime();
                                }
                            }, null);
                }
            });
            vgParent.addView(child);
        }
        if (vgParent.getVisibility() != View.VISIBLE) {
            isShow = true;
            vgParent.setVisibility(View.VISIBLE);
            AnimatorUtils.alphaShow(vgParent, DURATION);
            AnimatorUtils.translationY(child, DURATION, -OFFSET, 0);
        }
        vTimeParent.setVisibility(select.isCustomTime() ? View.VISIBLE : View.GONE);
        update(data);
    }

    private boolean isShow = false;

    public boolean hide() {
        if (child == null || !isShow) {
            return false;
        }
        listener.onHide();
        isShow = false;
        AnimatorUtils.translationY(child, DURATION, 0, -OFFSET);
        AnimatorUtils.alphaHide(vgParent, DURATION, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                vgParent.setVisibility(View.GONE);
            }
        });
        return true;
    }

    private void update(List<TabDto> data) {
        childAdapter.setData(data);
    }

    public void callbackCustomTime() {
        if (listener != null) {
            long startTimeMs = getMillis(tvStartTime.getText().toString());
            long endTimeMs = getMillis(tvEndTime.getText().toString());
            if (startTimeMs == 0 || endTimeMs == 0) {
                return;
            }
            if (startTimeMs > endTimeMs) {
                MyToast.show("结束时间不能小于开始时间");
                return;
            }
            String startTime = DateUtils.getCalendarStr(DateUtils.PATTERN_YYYY_MM_DD_HHMMSS_1, startTimeMs);
            String endTime = DateUtils.getCalendarStr(DateUtils.PATTERN_YYYY_MM_DD_HHMMSS_1, endTimeMs + 86340000);
            listener.onCustomTime(startTime, endTime);
        }
    }

    private long getMillis(String timeStr) {
        return DateUtils.getTimeInMillis(timeStr, DateUtils.PATTERN_YYYY_MM_DD_1);
    }

    public interface OnTabSelectedListener {
        void onTabSelected(TabDto seleTab, View v);

        void onHide();

        void onCustomTime(String startTime, String endTime);
    }
}
