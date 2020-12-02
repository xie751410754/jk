package com.plinkdt.jk.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ActivityUtils;
import com.plinkdt.jk.R;
import com.plinkdt.jk.utils.AppUtils;
import com.xzq.module_base.User;
import com.xzq.module_base.api.NetManager;
import com.xzq.module_base.arouter.RouterPath;
import com.xzq.module_base.arouter.RouterUtils;
import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.EventUtil;
import com.xzq.module_base.mvp.MvpContract;
import com.xzq.module_base.sp.UserSPManager;
import com.xzq.module_base.utils.XZQLog;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = RouterPath.LOGIN)
public class SettingActivity extends BasePresenterActivity implements MvpContract.DoneView {


    public static void start(Context context) {
        Intent starter = new Intent(context, SettingActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.versionBuild)
    TextView versionBuild;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        setToolbar("设置");

        versionBuild.setText("版本:"+ AppUtils.getAppVersionName(me));
    }

    @OnClick({R.id.btn_logout})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_logout:

//                presenter.logOut();
//               ActivityUtils.finishAllActivities();
               User.logout();




                break;


        }
    }


    @Override
    public void onDone(Object... obj) {
//        User.logout();
    }
}
