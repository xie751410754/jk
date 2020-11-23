package com.plinkdt.jk.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jpeng.jptabbar.JPTabBar;
import com.plinkdt.jk.R;
import com.plinkdt.jk.dialog.UpdateAppDialog;
import com.xzq.module_base.base.BaseActivity;
import com.xzq.module_base.base.BasePresenterActivity;
import com.xzq.module_base.bean.UpdateAppEntity;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.MessageEvent;
import com.xzq.module_base.mvp.MvpContract;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.PermissionUtil;
import com.xzq.module_base.views.NoScrollViewPager;

import butterknife.BindView;

/**
 *
 */
public class MainActivity extends BasePresenterActivity implements MvpContract.UpdateAppView {

    private HomePageAdapter mPageAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    @BindView(R.id.tabbar)
    JPTabBar tabbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
//        hideToolbar();
        setToolbar("通讯录");
        ImageView btn_back = findViewById(R.id.toolbar_btn_back);
        btn_back.setVisibility(View.GONE);
        mPageAdapter = new HomePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPageAdapter);
        viewPager.setOffscreenPageLimit(mPageAdapter.getCount());
        tabbar.setTitles(mPageAdapter.getTitles());
        tabbar.setNormalIcons(mPageAdapter.getNorIcons());
        tabbar.setSelectedIcons(mPageAdapter.getSeleIcons());
        tabbar.generate();
        tabbar.setContainer(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setToolbar("通讯录");

                        break;
                    case 1:
                        setToolbar("我的");

                        break;
                    case 2:

                        setToolbar("应用中心");

                        break;

                }
            }
        });

        presenter.updateApp(AppUtils.getAppVersionCode()+"");
    }


    @Override
    protected void onRightClick(View v) {

    }


    @Override
    protected void activityExit() {
    }

    private boolean canFinish = false;
    private final Runnable mCancelFinishTask = () -> canFinish = false;
    private final Handler mHandler = new Handler();

    @Override
    public void onBackPressed() {
        if (canFinish) {
            mHandler.removeCallbacksAndMessages(null);
            ToastUtils.cancel();
            super.onBackPressed();
        } else {
            canFinish = true;
            MyToast.show("再按一次退出程序~");
            mHandler.postDelayed(mCancelFinishTask, 2000);
        }
    }


    @Override
    public void onGetUpdateAppSucceed(UpdateAppEntity updateAppEntity) {

        if (updateAppEntity.isUpdated()){
            PermissionUtil.requestStorage(new PermissionUtil.PermissionCallback() {
                @Override
                public void onGotPermission() {
                    UpdateAppDialog  updateAppDialog = new UpdateAppDialog(true, true, updateAppEntity);
                    updateAppDialog.show(getSupportFragmentManager(), UpdateAppDialog.TAG);
                }
            },"打开存储权限才可以下载新版本");
        }


    }
}
