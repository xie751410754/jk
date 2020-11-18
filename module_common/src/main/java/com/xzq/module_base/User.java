package com.xzq.module_base;

import android.text.TextUtils;

import com.xzq.module_base.bean.LoginBean;
import com.xzq.module_base.bean.UserDto;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.EventUtil;
import com.xzq.module_base.sp.UserSPManager;


/**
 * 登录信息管理
 * Created by Wesley on 2018/7/9.
 */

public class User {

    private static LoginBean sLogin = null;
    private static UserDto sUser = null;

    public static void init() {
        sLogin = UserSPManager.getLoginData();
        sUser = UserSPManager.getUser();
    }

    /**
     * 用户登录成功后设置登录数据
     *
     * @param data 登录数据
     */
    public static void login(LoginBean data) {
        sLogin = data;
//        //保存到SP
        UserSPManager.putLoginData(data);
//        if (!User.isVisitor()) {
//            String uid = getUserId();
//            JPushInterface.setAlias(Utils.getApp(), 0, uid);
//            ArraySet<String> tagSet = new ArraySet<>();
//            JPushInterface.setTags(Utils.getApp(), 0, tagSet);
//        }
    }

    public static void saveUser(UserDto user) {
        sUser = user;
        UserSPManager.putUser(user);
    }

    public static UserDto getUser() {
        return sUser;
    }


    /**
     * 用户登出、用户登录凭证失效时清除用户数据
     */
    public static void logout() {
//        if (!User.isVisitor()) {
//            JPushInterface.deleteAlias(Utils.getApp(), 0);
//            JPushInterface.cleanTags(Utils.getApp(), 0);
//        }
        sLogin = null;
        UserSPManager.clearUserData();
        EventUtil.post(EventAction.MAIN_ACTIVITY_RESTART);
    }

    /**
     * 用户是否已经登录
     *
     * @return .
     */
    public static boolean isLogin() {
        return sLogin != null;
    }

    /**
     * 是否有登录token，有token则是正常用户
     *
     * @return .
     */
    public static boolean hasToken() {
        return !TextUtils.isEmpty(getToken());
    }

    /**
     * 获取用户登录凭证
     *
     * @return .
     */
    public static String getToken() {
        return sLogin != null ? sLogin.getAccess_token() : null;
    }

    /**
     * 获取tokentype
     *
     * @return .
     */
    public static String getTokenType() {
        return sLogin != null ? sLogin.getToken_type() : null;
    }

}
