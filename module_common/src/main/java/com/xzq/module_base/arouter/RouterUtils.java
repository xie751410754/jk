package com.xzq.module_base.arouter;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xzq.module_base.User;

/**
 * 路由工具
 * Created by xzq on 2018/12/19.
 */
public class RouterUtils {

    private static Postcard build(String path) {
        return ARouter.getInstance().build(path);
    }

    public static boolean openLoginOrNoTokenIfNeed() {
        if (!User.isLogin()) {
            openLogin();
            return true;
        } else if (!User.hasToken()) {
            openNoToken();
            return true;
        }
        return false;
    }

    public static void openMain() {
        build(RouterPath.MAIN).navigation();
    }

    /**
     * 打开登录页
     */
    public static void openLogin() {
        build(RouterPath.LOGIN).navigation();
    }

    /**
     * 打开没有token页
     */
    public static void openNoToken() {
        build(RouterPath.NO_TOKEN).navigation();
    }

    /**
     * 打开主页标签详情
     */
    public static void openTabDetail(int type) {
        build(LoginPath.TAB_DETAIL)
                .withInt("type", type)
                .navigation();
    }


}
