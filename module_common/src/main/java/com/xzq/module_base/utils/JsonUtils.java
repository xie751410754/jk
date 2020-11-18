package com.xzq.module_base.utils;

import com.xzq.module_base.bean.UserInfoDto;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static String setLoginJson(String deviceId,String postUsername, String postPassword, String captchaCode) {
        JSONObject postJson = new JSONObject();

        try {
            postJson.put("deviceId", deviceId);
            postJson.put("username", postUsername);
            postJson.put("password", postPassword);
            postJson.put("validCode", captchaCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJson.toString();
    }

    public static String setFastModeJson(int current, int wordType, int size, int userId) {
        JSONObject postJson = new JSONObject();

        try {
            postJson.put("current", current);
            postJson.put("wordType", wordType);
            postJson.put("size", size);
            postJson.put("userId", userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJson.toString();
    }

    public static String setSpeciaTitleJson(int current, String subject_name, int size, String subject_level, String belong_area_province, String belong_area_city, String belong_area_district, String belong_industry) {
        JSONObject postJson = new JSONObject();

        try {
            postJson.put("current", current);
            postJson.put("subject_name", subject_name);
            postJson.put("size", size);
            postJson.put("subject_level", subject_level);
            postJson.put("belong_area_province", belong_area_province);
            postJson.put("belong_area_city", belong_area_city);
            postJson.put("belong_area_district", belong_area_district);
            postJson.put("belong_industry", belong_industry);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJson.toString();
    }

    public static String setFastModeResultJson(String queryMode, String searchScope, String storeType, String startTime, String endTime, String isSimilar, String sort, String rubbish, String keywords, String orderBy, String emotion, String media, int pageNo, int pageSize) {
        JSONObject postJson = new JSONObject();
        try {
            postJson.put("queryMode", queryMode);
            postJson.put("searchScope", searchScope);
            postJson.put("storeType", storeType);
            postJson.put("startTime", startTime);
            postJson.put("endTime", endTime);
            postJson.put("isSimilar", isSimilar);
            postJson.put("sort", sort);
            postJson.put("rubbish", rubbish);
            postJson.put("keywords", keywords);
            postJson.put("orderBy", orderBy);
            postJson.put("emotion", emotion);
            postJson.put("media", media);
            postJson.put("pageNo", pageNo);
            postJson.put("pageSize", pageSize);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJson.toString();
    }

    public static String setFastModeDelJson(int wordType, String userId) {
        JSONObject postJson = new JSONObject();

        try {
            postJson.put("type", wordType);
            postJson.put("assignmentid", userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJson.toString();
    }

    public static String setFastModeAddJson(String keyword, int searchScope, int searchType, int wordType, int userId) {
        JSONObject postJson = new JSONObject();

        try {
            postJson.put("wordType", wordType);
            postJson.put("userId", userId);
            postJson.put("keyword", keyword);
            postJson.put("searchScope", searchScope);
            postJson.put("searchType", searchType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJson.toString();
    }

    //customer_name: "中泓在线-专题分析"
    //email: "12266661@126.com"
    //loginName: "kys.nf"
    //openid: ""
    //phone: "13800166661"
    //qq_number: ""
    //rolename: "KYS内服"
    //sex: "m"
    //username: "内服kys.nf2"
    public static String setUserInfo(UserInfoDto dataBean) {
        JSONObject postJson = new JSONObject();

        try {
            postJson.put("customer_name", dataBean.customer_name);
            postJson.put("email", dataBean.email);
            postJson.put("loginName", dataBean.loginName);
            postJson.put("openid", dataBean.openid);
            postJson.put("phone", dataBean.phone);
            postJson.put("qq_number", dataBean.qq_number);
            postJson.put("rolename", dataBean.rolename);
            postJson.put("sex", dataBean.sex);
            postJson.put("username", dataBean.username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJson.toString();
    }

    public static String setConfirmSms(String phone, String smsCode) {
        JSONObject postJson = new JSONObject();

        try {
            postJson.put("phone", phone);
            postJson.put("smsCode", smsCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJson.toString();
    }

    public static String setUpdatePwd(String oldPwd, String newPwd) {
        JSONObject postJson = new JSONObject();

        try {
            postJson.put("oldPwd", oldPwd);
            postJson.put("newPwd", newPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJson.toString();
    }

    public static String setMids(String mids) {
        JSONObject postJson = new JSONObject();

        try {
            postJson.put("mids", mids);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJson.toString();
    }

    public static String setArticleDetails(String mid, String subIds, String keywords) {
        JSONObject postJson = new JSONObject();

        try {
            postJson.put("mid", mid);
            postJson.put("subIds", subIds);
            postJson.put("keywords", keywords);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postJson.toString();
    }
}
