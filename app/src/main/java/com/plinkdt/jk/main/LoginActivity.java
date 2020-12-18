package com.plinkdt.jk.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.plinkdt.jk.R;
import com.plinkdt.jk.VPNLoginActivity;
import com.plinkdt.jk.welcome.WelcomeActivity;
import com.sangfor.bugreport.logger.Log;
import com.sangfor.ssl.BaseMessage;
import com.sangfor.ssl.IConstants;
import com.sangfor.ssl.IVpnDelegate;
import com.sangfor.ssl.LoginResultListener;
import com.sangfor.ssl.OnStatusChangedListener;
import com.sangfor.ssl.SFException;
import com.sangfor.ssl.SangforAuthManager;
import com.sangfor.ssl.StatusChangedReason;
import com.sangfor.ssl.common.ErrorCode;
import com.xzq.module_base.User;
import com.xzq.module_base.api.NetManager;
import com.xzq.module_base.arouter.RouterPath;
import com.xzq.module_base.arouter.RouterUtils;
import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.LoginBean;
import com.xzq.module_base.bean.UserDto;
import com.xzq.module_base.dialog.CommonDialog;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.EventUtil;
import com.xzq.module_base.eventbus.MessageEvent;
import com.xzq.module_base.mvp.MvpContract;
import com.xzq.module_base.utils.EntitySerializerUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.StringUtils;
import com.xzq.module_base.utils.XZQLog;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
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
@Route(path = RouterPath.LOGIN)
public class LoginActivity extends BasePresenterActivity implements MvpContract.UserInfoView , LoginResultListener {


    String deviceId;
    private SangforAuthManager mSFManager = null;
    private IConstants.VPNMode mVpnMode = IConstants.VPNMode.L3VPN;            //默认开启L3VPN模式
    private URL mVpnAddressURL = null;
    private boolean VPN_ON = false;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @OnClick({R.id.btn_login, R.id.verificationCode,R.id.btn_vpn})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_login:
                if (TextUtils.isEmpty(mName.getText())){
                    MyToast.show("请填写用户名");
                    return;
                }
                if (TextUtils.isEmpty(mPassword.getText())){
                    MyToast.show("请填写用密码");
                    return;
                }
                if (TextUtils.isEmpty(mCode.getText())){
                    MyToast.show("请填写验证码");
                    return;
                }
                login(deviceId);
//                presenter.login(mPhone.getText().toString(), mPassword.getText().toString());
//                MainActivity.start(me);

                break;
            case R.id.verificationCode:
                getCode();
                break;
            case R.id.btn_vpn:
                startActivity(new Intent(LoginActivity.this, VPNLoginActivity.class));
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


        boolean login = User.isLogin();
        if (User.isLogin()){
//            startTicketLogin();

            MainActivity.start(me);
            finish();
            return;
//            RouterUtils.openVpn();
//            try {
//                Thread.sleep(1000);
//
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            if (VPN_ON){
//
//                MainActivity.start(me);
//                finish();
//                return;
//            }

        }

        hideToolbar();

        okHttpClient = new OkHttpClient();

//        mPhone.setText("admin");
//        mPassword.setText("123456");
//        presenter.getValidata(UUID.randomUUID().toString());
        getCode();
    }

    private void startTicketLogin() {
        initLoginParms();
        //判断是否开启免密，如果免密直接进行一次登录，如果无法免密或免密登录失败，通知界面
        if (mSFManager.ticketAuthAvailable(LoginActivity.this)) { //允许免密，直接走免密流程
            try {
                addStatusChangedListener(); //添加vpn状态变化监听器
                mSFManager.startTicketAuthLogin(getApplication(), LoginActivity.this, mVpnMode);
            } catch (SFException e) {
                //关闭登录进度框
            }
        } else {
            Toast.makeText(LoginActivity.this, R.string.str_ticket_not_support, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 注册vpn状态监听器，可在多处进行注册
     */
    private void addStatusChangedListener() throws SFException {
        mSFManager.addStatusChangedListener(new OnStatusChangedListener() {
            @Override
            public void onStatusCallback(IVpnDelegate.VPNStatus vpnStatus, StatusChangedReason reason) {
                //对回调结果进行处理，这里只是简单的显示，可根据业务需求自行扩展
                String status = "";
                switch (vpnStatus){
                    case VPNONLINE:
                        status = getString(R.string.str_vpn_online);
                        VPN_ON = true;

                        break;
                    case VPNOFFLINE:
                        status = getString(R.string.str_vpn_offline);
//
//                        try {
//                            mSFManager.startPasswordAuthLogin(getApplication(), VPNLoginActivity.this, mVpnMode,
//                                    mVpnAddressURL, mUserName, mUserPassword);
//                        } catch (SFException e) {
//                            e.printStackTrace();
//                        }

//                        startActivity(new Intent(VPNLoginActivity.this, VPNLoginActivity.class));
                        break;
                    case VPNRECONNECTED:
                        status = getString(R.string.str_vpn_reconnected);
                        break;
                }
                Toast.makeText(LoginActivity.this, status, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String mVpnAddress,mUserName,mUserPassword;
    private void initLoginParms() {
        // 1.构建SangforAuthManager对象
        mSFManager = SangforAuthManager.getInstance();

        // 2.设置VPN认证结果回调
        try {
            mSFManager.setLoginResultListener(this);
        }catch (SFException e) {
        }

        //3.设置登录超时时间，单位为秒
        mSFManager.setAuthConnectTimeOut(8);
        SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        mVpnAddress = sharedPreferences.getString("VpnAddress", "");
        mUserName = sharedPreferences.getString("UserName", "");
        mUserPassword = sharedPreferences.getString("UserPassword", "");
        try {
            //将地址字符串封装成url
            mVpnAddressURL = new URL(mVpnAddress);
            if (TextUtils.isEmpty(mVpnAddressURL.getHost()))
            {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, R.string.str_url_error, Toast.LENGTH_LONG).show();
        }

        try {
            mSFManager.startPasswordAuthLogin(getApplication(), LoginActivity.this, mVpnMode,
                    mVpnAddressURL, mUserName, mUserPassword);
        } catch (SFException e) {
            e.printStackTrace();
        }
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
//                MyToast.showLong("验证码加载失败");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        verificationCode.setImageResource(R.drawable.ic_error_vercode);
//                        CommonDialog.showSingle(me, "加载验证码失败,请检查手机是否已连上vpn,若没有请点击确定按钮进行vpn登录", new CommonDialog.OnOkClickListener() {
//                            @Override
//                            public void onDialogOkClicked() {
//                                startActivity(new Intent(LoginActivity.this, VPNLoginActivity.class));
//                                finish();
//                            }
//                        });
                    }
                });


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

                        if (bitmap!=null){
                            verificationCode.setImageBitmap(ImageUtils.scale(bitmap, 120, 40));
                        }
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
                MyToast.show("登录失败，请检查网络");
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


    @Override
    public void onBackPressed() {

        ActivityUtils.startHomeActivity();

    }

    private void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hidePostLoading();
            }
        });
    }



    @Override
    public void onGetUserInfoFinish(UserDto user) {
        if (user != null) {
            User.saveUser(user);
            MainActivity.start(me);
            finish();
        }
    }

    @Override
    public void onMessageEvent(@NonNull MessageEvent event) {
        super.onMessageEvent(event);
        if (event.equals(EventAction.MAIN_ACTIVITY_RESTART)) {
            restartActivity();
        }
    }

    private void restartActivity() {
       LoginActivity.start(me);
    }

    @Override
    public void onLoginFailed(ErrorCode errorCode, String s) {

    }

    @Override
    public void onLoginProcess(int i, BaseMessage baseMessage) {

    }

    @Override
    public void onLoginSuccess() {

    }
}
