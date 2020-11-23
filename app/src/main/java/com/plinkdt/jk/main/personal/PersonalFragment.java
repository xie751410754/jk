package com.plinkdt.jk.main.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luck.picture.lib.config.PictureConfig;
import com.plinkdt.jk.R;
import com.plinkdt.jk.main.SettingActivity;
import com.plinkdt.jk.utils.CommonUtils;
import com.xzq.module_base.User;
import com.xzq.module_base.base.BaseFragment;
import com.xzq.module_base.base.BasePresenterFragment;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.MessageEvent;
import com.xzq.module_base.mvp.MvpContract;
import com.xzq.module_base.utils.GlideUtils;
import com.xzq.module_base.utils.XZQLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalFragment extends BasePresenterFragment implements MvpContract.UploadImgView {

    List<Integer> images = new ArrayList<>();



    @BindView(R.id.user)
    TextView user;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.jobName)
    TextView jobName;
    @BindView(R.id.postName)
    TextView postName;
    @BindView(R.id.headImg)
    ImageView ivHead;
    @BindView(R.id.vg_tabs)
    ViewGroup vgTabs;
    @BindView(R.id.tv_msg_count_todo)
    TextView tvMsgCountTodo;
    @BindView(R.id.btn_todo)
    LinearLayout btnTodo;
    @BindView(R.id.tv_msg_count_notice)
    TextView tvMsgCountNotice;
    @BindView(R.id.btn_notice)
    LinearLayout btnNotice;
    @BindView(R.id.tv_msg_count_done)
    TextView tvMsgCountDone;
    @BindView(R.id.btn_done)
    LinearLayout btnDone;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @OnClick({R.id.headImg,R.id.img_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.headImg:

                CommonUtils.selectHead(this);


                break;

            case R.id.img_setting:

                SettingActivity.start(me);
                break;

        }
    }


    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {


        user.setText(User.getUser().getNickname());
        mobile.setText(User.getUser().getMobile()+"");
        postName.setText(User.getUser().getPost());
        jobName.setText(User.getUser().getJobName());
        GlideUtils.loadImage(ivHead, User.getUser().getHeadImgUrl());


        MatterPagerAdapter pagerAdapter = new MatterPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(pagerAdapter.getCount());
        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                CommonUtils.selectThis(vgTabs.getChildAt(position));
            }
        };
        viewPager.addOnPageChangeListener(pageChangeListener);
        pageChangeListener.onPageSelected(0);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        XZQLog.debug("photo==",CommonUtils.getPath(data)+requestCode+resultCode);
        if (resultCode == Activity.RESULT_OK && requestCode == PictureConfig.CHOOSE_REQUEST) {

            GlideUtils.loadImage(ivHead, CommonUtils.getPath(data));
            presenter.uploadImg(CommonUtils.getPath(data),requestCode);
        }

    }

    @OnClick({R.id.btn_todo, R.id.btn_notice, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_todo:
                viewPager.setCurrentItem(0);
                break;
            case R.id.btn_notice:
                viewPager.setCurrentItem(1);
                break;
            case R.id.btn_done:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onUploadImgSucceed(String remotePath, int... code) {
        GlideUtils.loadImage(ivHead, remotePath);

    }

    @Override
    public void onMessageEvent(@NonNull MessageEvent event) {
        super.onMessageEvent(event);

        if (event.equals(EventAction.WAITDEAL)){
            tvMsgCountTodo.setText(event.getData()+"");
        }
        if (event.equals(EventAction.NOTICE)){
            tvMsgCountNotice.setText(event.getData()+"");
        }
        if (event.equals(EventAction.FINISHDEAL)){
            tvMsgCountDone.setText(event.getData()+"");
        }
    }
}



