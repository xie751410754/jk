package com.plinkdt.jk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

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

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName=null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }


    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode;
        try {
            versionCode = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(),
                            0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }


}
