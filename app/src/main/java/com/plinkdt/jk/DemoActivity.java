package com.plinkdt.jk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.xzq.module_base.base.BaseActivity;
import com.xzq.module_base.dialog.CommonDialog;
import com.xzq.module_base.dialog.CommonWheelDialog;
import com.xzq.module_base.dialog.SelectTimeDialog;
import com.xzq.module_base.utils.DateUtils;
import com.xzq.module_base.utils.MyToast;
import com.plinkdt.jk.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DemoActivity extends BaseActivity {

    @BindView(R.id.btn_time_picker)
    TextView btnTimePicker;
    @BindView(R.id.btn_picker1)
    TextView btnPicker1;
    @BindView(R.id.btn_date_picker)
    TextView btnDatePicker;

    public static void start(Context context) {
        Intent starter = new Intent(context, DemoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        setToolbar("DEMO");
    }

    @OnClick({R.id.btn_dialog1, R.id.btn_dialog2, R.id.btn_dialog3, R.id.btn_time_picker, R.id.btn_date_picker, R.id.btn_picker1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog1:
                CommonDialog.showDouble(me,
                        "您确定要清空最近搜索记录？",
                        new CommonDialog.OnOkClickListener() {
                            @Override
                            public void onDialogOkClicked() {
                                MyToast.show("确定清空");
                            }
                        });
                break;
            case R.id.btn_dialog2:
                CommonDialog.showDouble(me,
                        "删除",
                        "您正在进行删除传操作，已删除信息将不再出现",
                        new CommonDialog.OnOkClickListener() {
                            @Override
                            public void onDialogOkClicked() {
                                MyToast.show("确定删除");
                            }
                        });
                break;
            case R.id.btn_dialog3:
                CommonDialog.showSingle(me,
                        "删除成功",
                        new CommonDialog.OnOkClickListener() {
                            @Override
                            public void onDialogOkClicked() {
                                MyToast.show("确定");
                            }
                        });
                break;
            case R.id.btn_time_picker:
                SelectTimeDialog selectTimeDialog = new SelectTimeDialog(me, "开始时间设置");
                selectTimeDialog.setCallback(new SelectTimeDialog.OnTimeCallback() {
                    @Override
                    public void onTimeCallback(String time, int hour, int min, int sec) {
                        btnTimePicker.setText(time);
                    }
                });
                selectTimeDialog.show();
                break;

            case R.id.btn_date_picker:
                CommonUtils.showTimePicker(DateUtils.PATTERN_YYYY_MM_DD_1, view, me, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String time = DateUtils.getCalendarStr(DateUtils.PATTERN_YYYY_MM_DD_1, date);
                        btnDatePicker.setText(time);
                    }
                });
                break;

            case R.id.btn_picker1:
                List<String> infos = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    infos.add("信息类别0" + i);
                }
                CommonWheelDialog.show(me, view, "信息类别选择", infos, new CommonWheelDialog.OnItemSelectedListener<String>() {
                    @Override
                    public void onItemSelected(String s, int index) {
                        btnPicker1.setText(s);
                    }
                });
                break;
        }
    }

}
