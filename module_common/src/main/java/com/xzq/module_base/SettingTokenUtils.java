package com.xzq.module_base;

import android.text.TextUtils;

import com.xzq.module_base.api.NetManager;
import com.xzq.module_base.bean.LoginBean;
import com.xzq.module_base.utils.EntitySerializerUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.XZQLog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

public class SettingTokenUtils {

    private static SettingTokenUtils mInstance;
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;



    public static SettingTokenUtils getInstance() {
        if (mInstance == null) {
            synchronized (SettingTokenUtils.class) {
                if (mInstance == null) {
                    mInstance = new SettingTokenUtils();
                }
            }
        }
        return mInstance;
    }


    /**
     * 初始化必要对象和参数
     */
    public void init() {

        okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type", "refresh_token")
                .add("refresh_token", User.getRefreshToken())
                .add("client_id", "app")
                .add("client_secret", "app").build();
        Request request = new Request.Builder()
                .header("Authorization", User.getToken())
                .url(NetManager.BASEURL + "/api-uaa/oauth/token")
                .post(requestBody)
                .build();

        Call call2 = okHttpClient.newCall(request);
        call2.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                XZQLog.debug("login error = " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String json = response.body() != null ? response.body().string() : "";
                        XZQLog.debug("login succeed = " + json);
                        JSONObject jsonObject = new JSONObject(json);
                        String errorCode = jsonObject.getString("code");

                        if (TextUtils.equals(errorCode, "200")) {
                            String dataJson = jsonObject.getString("data");
//                            JSONArray jsonArray = jsonObject.getJSONArray(dataJson);
//                            String access_token = jsonArray.getString(0);
//                            User.setToken(access_token);
                            User.login(EntitySerializerUtils.deserializerEntity(dataJson, LoginBean.class));

                        } else{
                            String errorMessage = jsonObject.getString("message");
                            MyToast.showFailed(errorMessage);
//                            MyToast.show("登录已过期，请重新登录");
//                            User.logout();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    MyToast.show("登录已过期，请重新登录");
                    User.logout();
                }
            }
        });

    }

}
