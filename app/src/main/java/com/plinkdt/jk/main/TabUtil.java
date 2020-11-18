package com.plinkdt.jk.main;

import com.xzq.module_base.dto.TabDto;
import com.xzq.module_base.utils.MyToast;
import com.plinkdt.jk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * TabUtil
 * Created by xzq on 2019/11/12.
 */
public class TabUtil {

    public static final int SPAN_COUNT = 4;
    private static final List<TabDto> MONITOR_LIST = new ArrayList<>();
    private static final List<TabDto> DISPOSE_LIST = new ArrayList<>();
    private static final List<TabDto> REPORT_LIST = new ArrayList<>();

    public static List<TabDto> getMonitorList() {

        if (!MONITOR_LIST.isEmpty()) {
            return MONITOR_LIST;
        }

        TabDto tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_wlyq_normal;
        tabDto.name = "网络舆情";
        tabDto.msgCount = 20;
        MONITOR_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_yqss_normal;
        tabDto.name = "舆情搜索";
        tabDto.msgCount = 0;
        MONITOR_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_sjfx_normal;
        tabDto.name = "事件分析";
        tabDto.msgCount = 8;
        MONITOR_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_yqyj_normal;
        tabDto.name = "舆情预警";
        tabDto.msgCount = 100;
        MONITOR_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_zddy_normal;
        tabDto.name = "重点订阅";
        tabDto.msgCount = 0;
        MONITOR_LIST.add(tabDto);

        return MONITOR_LIST;
    }

    public static List<TabDto> getDisposeList() {

        if (!DISPOSE_LIST.isEmpty()) {
            return DISPOSE_LIST;
        }

        TabDto tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_xxzx_normal;
        tabDto.name = "消息中心";
        tabDto.msgCount = 20;
        DISPOSE_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_yqcz_normal;
        tabDto.name = "舆情处置";
        tabDto.msgCount = 0;
        DISPOSE_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_yqjl_normal;
        tabDto.name = "舆情交流";
        tabDto.msgCount = 8;
        DISPOSE_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_yqtz_normal;
        tabDto.name = "舆情台账";
        tabDto.msgCount = 100;
        DISPOSE_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_tjfx_normal;
        tabDto.name = "统计分析";
        tabDto.msgCount = 0;
        DISPOSE_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_xtsz_normal;
        tabDto.name = "系统设置";
        tabDto.msgCount = 0;
        DISPOSE_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_rygl_normal;
        tabDto.name = "人员管理";
        tabDto.msgCount = 0;
        DISPOSE_LIST.add(tabDto);

        return DISPOSE_LIST;
    }

    public static List<TabDto> getReportList() {

        if (!REPORT_LIST.isEmpty()) {
            return REPORT_LIST;
        }

        TabDto tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_zb_normal;
        tabDto.name = "工作周报";
        REPORT_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_yb_normal;
        tabDto.name = "工作月报";
        REPORT_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_nb_normal;
        tabDto.name = "工作年报";
        REPORT_LIST.add(tabDto);

        tabDto = new TabDto();
        tabDto.resId = R.drawable.icon_module_other_normal;
        tabDto.name = "其他报告";
        REPORT_LIST.add(tabDto);

        return REPORT_LIST;
    }

    public static String getNameByType(int type) {
        switch (type) {
            default:
            case 0:
                return "网络舆情";
            case 1:
                return "处置应对";
            case 2:
                return "工作报告";
        }
    }

    public static List<TabDto> getLisByType(int type) {
        switch (type) {
            default:
            case 0:
                return getMonitorList();
            case 1:
                return getDisposeList();
            case 2:
                return getReportList();
        }
    }

    public static void openPage(TabDto tab) {
        MyToast.show(tab.name);
        switch (tab.resId) {
            case R.drawable.icon_module_wlyq_normal: //网络舆情
                break;
            case R.drawable.icon_module_yqss_normal: //舆情搜索
                break;
            case R.drawable.icon_module_sjfx_normal: //事件分析
                break;
            case R.drawable.icon_module_yqyj_normal: //舆情预警
                break;
            case R.drawable.icon_module_zddy_normal: //重点订阅
                break;

            case R.drawable.icon_module_xxzx_normal: //消息中心
                break;
            case R.drawable.icon_module_yqcz_normal: //舆情处置
                break;
            case R.drawable.icon_module_yqjl_normal: //舆情交流
                break;
            case R.drawable.icon_module_yqtz_normal: //舆情台账
                break;
            case R.drawable.icon_module_tjfx_normal: //统计分析
                break;
            case R.drawable.icon_module_xtsz_normal: //系统设置
                break;
            case R.drawable.icon_module_rygl_normal: //人员管理
                break;

            case R.drawable.icon_module_zb_normal: //工作周报
                break;
            case R.drawable.icon_module_yb_normal: //工作月报
                break;
            case R.drawable.icon_module_nb_normal: //工作年报
                break;
            case R.drawable.icon_module_other_normal: //其它报告
                break;
        }
    }
}






