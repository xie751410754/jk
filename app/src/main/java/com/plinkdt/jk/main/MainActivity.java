package com.plinkdt.jk.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.jpeng.jptabbar.JPTabBar;
import com.plinkdt.jk.R;
import com.xzq.module_base.base.BaseActivity;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.MessageEvent;
import com.xzq.module_base.views.NoScrollViewPager;

import butterknife.BindView;

/**
 *
 */
public class MainActivity extends BaseActivity {

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
    }


    @Override
    protected void onRightClick(View v) {

    }




}
