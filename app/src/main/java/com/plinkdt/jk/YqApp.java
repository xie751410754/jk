package com.plinkdt.jk;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CrashUtils;
import com.xzq.module_base.User;
import com.xzq.module_base.bean.LoginBean;
import com.xzq.module_base.bean.LoginDto;
import com.xzq.module_base.utils.RefreshLayoutInitializer;
import com.xzq.module_base.utils.Utils;
import com.xzq.module_base.utils.XTimber;

/**
 * YqApp
 * Created by xzq on 2019/11/12.
 */
public class YqApp extends Application {

    private static final String TAG = "YqApp";
    private static Application sInstance;
    protected boolean isDebug;

    public static Application getContext() {
        return sInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (Utils.isOtherProcess(this)) {
            Log.d(TAG, "attachBaseContext call by other process , stop init");
            return;
        }
        // dex突破65535的限制
        MultiDex.install(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();
        if (Utils.isOtherProcess(this)) {
            Log.d(TAG, "onCreate call by other process , stop init");
            return;
        }
        sInstance = this;
        CrashUtils.init();
        isDebug = AppUtils.isAppDebug();
        if (isDebug) {
            //初始化懒人log
            XTimber.plant(new XTimber.DebugTree());

            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
        //初始化下拉刷新
        RefreshLayoutInitializer.initHeader();
        User.init();
        //SysConfigManager.init();
        //JPushInterface.setDebugMode(isDebug);    // 设置开启日志,发布时请关闭日志
        //JPushInterface.init(this);            // 初始化 JPush
    }

}
