package com.xzq.module_base.arouter;

import android.text.TextUtils;

/**
 * 登录页Path
 * Created by xzq on 2018/12/19.
 */
public class LoginPath {

    /**
     * 页面Path是否归属登录组
     *
     * @param group 组名称
     * @return 是否归属登录组
     */
    public static boolean isLoginGroup(String group) {
        return TextUtils.equals(GROUP, group);
    }

    //登录组名
    public static final String GROUP = "login";

    //主页标签详情
    public static final String TAB_DETAIL = "/" + GROUP + "/tab_detail";


}
