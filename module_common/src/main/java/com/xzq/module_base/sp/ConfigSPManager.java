package com.xzq.module_base.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.blankj.utilcode.util.Utils;
import com.google.gson.reflect.TypeToken;
import com.xzq.module_base.config.Config;
import com.xzq.module_base.utils.EntitySerializerUtils;
import com.xzq.module_base.utils.cipher.CipherUtils;

import java.util.List;

/**
 * 配置SP数据
 * Created by xzq on 2019/5/20.
 */

public class ConfigSPManager {

    private static final String SP_CONFIG_INFO = "config_info";
    private static final String KEY_SEARCH_KEYWORDS = "search_keywords";
    private static final String KEY_LAST_USER_TYPE = "last_user_type";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_LAST_USER_PHONE = "last_user_phone";
    private static final String KEY_LAST_COLOR = "last_color";
    private static final String KEY_LAST_USER_ACCOUNT = "last_user_account";
    private static final String KEY_LAST_USER_PWD = "last_user_pwd";

    /**
     * 获取配置信息 SharedPreferences
     *
     * @return SharedPreferences
     */
    private static SharedPreferences getConfigInfoSharedPreferences() {
        final Context context = Utils.getApp();
        return context.getSharedPreferences(SP_CONFIG_INFO, Context.MODE_PRIVATE);
    }

    /**
     * 存储用户搜索记录
     *
     * @param keys 用户搜索记录
     */
    public static void putKeywords(List<String> keys) {
        if (keys == null || keys.isEmpty()) {
            getConfigInfoSharedPreferences().edit()
                    .putString(KEY_SEARCH_KEYWORDS, null).apply();
            return;
        }
        String keywords;
        try {
            keywords = EntitySerializerUtils.serializerList(keys);
        } catch (Exception e) {
            keywords = null;
        }
        getConfigInfoSharedPreferences().edit()
                .putString(KEY_SEARCH_KEYWORDS, keywords).apply();
    }

    /**
     * 获取用户搜索记录
     *
     * @return 用户搜索记录
     */
    public static List<String> getKeywords() {
        String keywords = getConfigInfoSharedPreferences()
                .getString(KEY_SEARCH_KEYWORDS, null);
        if (TextUtils.isEmpty(keywords))
            return null;
        List<String> listKeys;
        try {
            listKeys = EntitySerializerUtils.deserializerType(keywords,
                    new TypeToken<List<String>>() {
                    }.getType());
        } catch (Exception e) {
            listKeys = null;
        }
        return listKeys;
    }

    /**
     * 获取最后登录用户类型
     *
     * @return 最后登录用户类型
     */
    public static int getLastUserType() {
        return getConfigInfoSharedPreferences().getInt(KEY_LAST_USER_TYPE, 0);
    }

    /**
     * 存储最后登录用户类型
     *
     * @param type 最后登录用户类型
     */
    public static void putLastUserType(int type) {
        getConfigInfoSharedPreferences().edit().putInt(KEY_LAST_USER_TYPE, type).apply();
    }

    /**
     * 获取地址串
     *
     * @return 地址串
     */
    public static String getAddress() {
        return getConfigInfoSharedPreferences().getString(KEY_ADDRESS, null);
    }

    /**
     * 存储地址串
     *
     * @param address 地址串
     */
    public static void putAddress(String address) {
        getConfigInfoSharedPreferences().edit().putString(KEY_ADDRESS, address).apply();
    }

    /**
     * 获取最后登录用户手机号
     *
     * @return 最后登录用户手机号
     */
    public static String getLastUserPhone() {
        return getConfigInfoSharedPreferences().getString(KEY_LAST_USER_PHONE, null);
    }

    /**
     * 存储最后登录用户手机号
     *
     * @param phone 最后登录用户手机号
     */
    public static void putLastUserPhone(String phone) {
        getConfigInfoSharedPreferences().edit().putString(KEY_LAST_USER_PHONE, phone).apply();
    }

    /**
     * 获取最后Colour
     *
     * @return 最后Colour
     */
    public static String getLastColour() {
        return getConfigInfoSharedPreferences().getString(KEY_LAST_COLOR, "#ffffffff");
    }

    /**
     * 存储最后Colour
     *
     * @param color 最后Colour
     */
    public static void putLastColour(String color) {
        getConfigInfoSharedPreferences().edit().putString(KEY_LAST_COLOR, color).apply();
    }


    /**
     * 获取最后登录用户账号
     *
     * @return 最后登录用户账号
     */
    public static String getLastUserAccount() {
        String cipher = getConfigInfoSharedPreferences().getString(KEY_LAST_USER_ACCOUNT, null);
        return CipherUtils.decryptAES(Config.CACHE_PASSWORD, Config.CACHE_SALT, cipher, String.class);
    }

    /**
     * 存储最后登录用户账号
     *
     * @param account 最后登录用户账号
     */
    public static void putLastUserAccount(String account) {
        String cipher = CipherUtils.encryptAES(Config.CACHE_PASSWORD, Config.CACHE_SALT, account);
        if (cipher == null)
            return;
        getConfigInfoSharedPreferences().edit().putString(KEY_LAST_USER_ACCOUNT, cipher).apply();
    }

    /**
     * 获取最后登录用户密码
     *
     * @return 最后登录用户密码
     */
    public static String getLastUserPwd() {
        String cipher = getConfigInfoSharedPreferences().getString(KEY_LAST_USER_PWD, null);
        return CipherUtils.decryptAES(Config.CACHE_PASSWORD, Config.CACHE_SALT, cipher, String.class);
    }

    /**
     * 存储最后登录用户密码
     *
     * @param pwd 最后登录用户密码
     */
    public static void putLastUserPwd(String pwd) {
        String cipher = CipherUtils.encryptAES(Config.CACHE_PASSWORD, Config.CACHE_SALT, pwd);
        if (cipher == null)
            return;
        getConfigInfoSharedPreferences().edit().putString(KEY_LAST_USER_PWD, cipher).apply();
    }

}
