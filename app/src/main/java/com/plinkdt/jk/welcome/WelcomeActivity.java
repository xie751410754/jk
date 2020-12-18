package com.plinkdt.jk.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.plinkdt.jk.R;
import com.plinkdt.jk.VPNLoginActivity;
import com.plinkdt.jk.main.LoginActivity;
import com.plinkdt.jk.main.MainActivity;
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
import com.xzq.module_base.base.BaseActivity;
import com.xzq.module_base.utils.MyToast;

import java.net.URL;


public class WelcomeActivity extends BaseActivity implements Runnable, LoginResultListener {


    private SangforAuthManager mSFManager = null;
    private IConstants.VPNMode mVpnMode = IConstants.VPNMode.L3VPN;            //默认开启L3VPN模式
    private URL mVpnAddressURL = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().postDelayed(this, 1500);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        hideToolbar();
    }

    @Override
    public void run() {
        if (isFinishing()) {
            finish();
            return;
        }

        if (User.isLogin()) {
            startTicketLogin();

        } else {
            LoginActivity.start(this);
            finish();

        }

//        LoginActivity.start(this);
//        startActivity(new Intent(WelcomeActivity.this, VPNLoginActivity.class));

//        finish();
    }

    private void startTicketLogin() {
        initLoginParms();
        //判断是否开启免密，如果免密直接进行一次登录，如果无法免密或免密登录失败，通知界面
        if (mSFManager.ticketAuthAvailable(WelcomeActivity.this)) { //允许免密，直接走免密流程
            try {
                addStatusChangedListener(); //添加vpn状态变化监听器
                mSFManager.startTicketAuthLogin(getApplication(), WelcomeActivity.this, mVpnMode);
            } catch (SFException e) {
                //关闭登录进度框
            }
        } else {
//            Toast.makeText(WelcomeActivity.this, R.string.str_ticket_not_support, Toast.LENGTH_SHORT).show();
        }
    }

    private String mVpnAddress, mUserName, mUserPassword;

    private void initLoginParms() {

        // 1.构建SangforAuthManager对象
        mSFManager = SangforAuthManager.getInstance();

        // 2.设置VPN认证结果回调
        try {
            mSFManager.setLoginResultListener(this);
        } catch (SFException e) {
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
            if (TextUtils.isEmpty(mVpnAddressURL.getHost())) {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            Toast.makeText(WelcomeActivity.this, R.string.str_url_error, Toast.LENGTH_LONG).show();
        }

        try {
            mSFManager.startPasswordAuthLogin(getApplication(), WelcomeActivity.this, mVpnMode,
                    mVpnAddressURL, mUserName, mUserPassword);
        } catch (SFException e) {
            e.printStackTrace();
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
                switch (vpnStatus) {
                    case VPNONLINE:
                        status = getString(R.string.str_vpn_online);

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
                Toast.makeText(WelcomeActivity.this, status, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onLoginFailed(ErrorCode errorCode, String s) {
        if (!TextUtils.isEmpty(s)) {

            MyToast.show("VPN登录失败"+s);

        } else {
            MyToast.show("VPN登录失败");

        }
        VPNLoginActivity.start(me,true);
        finish();
    }

    @Override
    public void onLoginProcess(int i, BaseMessage baseMessage) {

    }

    @Override
    public void onLoginSuccess() {
        LoginActivity.start(WelcomeActivity.this);
        finish();
    }
}
