package com.plinkdt.jk.utils;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.plinkdt.jk.main.MainActivity;

import java.util.List;

/**
 * AppUtils
 * Created by xzq on 2019/10/12.
 */
public class AppUtils {

    /**
     * 主页是否存在
     *
     * @return .
     */
    public static boolean isMainExist() {
        boolean isMainExist = false;
        List<Activity> activities = ActivityUtils.getActivityList();
        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            if (activity instanceof MainActivity) {
                isMainExist = true;
                break;
            }
        }
        return isMainExist;
    }


}
