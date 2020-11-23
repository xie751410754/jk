package com.plinkdt.jk.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.plinkdt.jk.R;
import com.xzq.module_base.User;
import com.xzq.module_base.api.NetManager;
import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.LoginBean;
import com.xzq.module_base.bean.UserDto;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.MessageEvent;
import com.xzq.module_base.mvp.MvpContract;
import com.xzq.module_base.utils.EntitySerializerUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.StringUtils;
import com.xzq.module_base.utils.XZQLog;

import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BasePresenterActivity implements MvpContract.UserInfoView {


    String deviceId;


    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @OnClick({R.id.btn_login, R.id.verificationCode})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_login:
                if (TextUtils.isEmpty(mName.getText())) return;
                if (TextUtils.isEmpty(mPassword.getText())) return;
                if (TextUtils.isEmpty(mCode.getText())) return;
                login(deviceId);
//                presenter.login(mPhone.getText().toString(), mPassword.getText().toString());
//                MainActivity.start(me);

                break;
            case R.id.verificationCode:
                getCode();
                break;

        }
    }


    @BindView(R.id.userName)
    EditText mName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.verificationCode)
    ImageView verificationCode;

    @BindView(R.id.et_code)
    EditText mCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

        if (User.isLogin()){
            MainActivity.start(me);
            finish();
            return;
        }


        hideToolbar();

        okHttpClient = new OkHttpClient();

//        mPhone.setText("admin");
//        mPassword.setText("123456");
//        presenter.getValidata(UUID.randomUUID().toString());
        getCode();
    }


    private OkHttpClient okHttpClient;
    private String sessionIdCode;


    private void getCode() {

        deviceId = UUID.randomUUID().toString();
        XZQLog.debug("deviceId  =" + deviceId + "base64==" + StringUtils.setEncryption("app:app"));

        Request request = new Request.Builder()
                .url(NetManager.BASEURL + "/api-uaa/validata/code/" + deviceId)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                XZQLog.debug("getCode error =" + e.toString());
                MyToast.show("请检查手机是否已连上vpn，再重新打开我");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    return;
                }
                byte[] byte_image = response.body().bytes();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(byte_image, 0, byte_image.length);

                        verificationCode.setImageBitmap(ImageUtils.scale(bitmap, 120, 40));
                    }
                });

            }
        });
    }


    private void login(String sessionId) {

        String userName = mName.getText().toString();
        String password = mPassword.getText().toString();
        String captchaCode = mCode.getText().toString();
        XZQLog.debug("login userName = " + userName);
        XZQLog.debug("login password = " + password);
        XZQLog.debug("login captchaCode = " + captchaCode);
        XZQLog.debug("login sessionId = " + sessionId);
        RequestBody requestBody = new FormBody.Builder()
                .add("deviceId", sessionId)
                .add("username", userName)
                .add("password", password)
                .add("validCode", captchaCode).build();
        Request request = new Request.Builder()
                .header("Authorization", "Basic " + StringUtils.setEncryption("app:app"))
                .url(NetManager.BASEURL + "/api-uaa/oauth/user/token")
                .post(requestBody)
                .build();
        Call call2 = okHttpClient.newCall(request);
        call2.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                XZQLog.debug("login error = " + e.toString());
                MyToast.showFailed(e.toString());
                hideLoading();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                hideLoading();
                if (response.isSuccessful()) {
                    try {
                        String json = response.body() != null ? response.body().string() : "";
                        XZQLog.debug("login succeed = " + json);
                        JSONObject jsonObject = new JSONObject(json);
                        String errorCode = jsonObject.getString("code");

                        if (TextUtils.equals(errorCode, "200")) {
//                            JSONArray jsonArray = jsonObject.getJSONArray("datas");
//                            String access_token = jsonArray.getString(0);
//                            String token_type = jsonArray.getString(1);
//                            ConfigSPManager.putLastUserAccount(access_token);
//                            ConfigSPManager.putLastUserPwd(token_type);
                            String dataJson = jsonObject.getString("data");
                            User.login(EntitySerializerUtils.deserializerEntity(dataJson, LoginBean.class));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    presenter.getUser();
                                }
                            });
                        } else {
                            String errorMessage = jsonObject.getString("message");
                            MyToast.showFailed(errorMessage);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    MyToast.show("登陆失败，请检查账号密码是否正确");
                }
            }
        });
    }

    private void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hidePostLoading();
            }
        });
    }

    private void toMain() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MainActivity.start(me);
            }
        });
    }

    @Override
    public void onGetUserInfoFinish(UserDto user) {
        if (user != null) {
            User.saveUser(user);
            MainActivity.start(me);
        }
    }

    @Override
    public void onMessageEvent(@NonNull MessageEvent event) {
        super.onMessageEvent(event);
        if (event.equals(EventAction.MAIN_ACTIVITY_RESTART)) {
            ActivityUtils.finishAllActivities();
            restartActivity();
        }
    }

    private void restartActivity() {
       LoginActivity.start(me);
    }
}
