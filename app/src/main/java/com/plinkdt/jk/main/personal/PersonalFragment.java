package com.plinkdt.jk.main.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
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
import com.xzq.module_base.bean.MatterDto;
import com.xzq.module_base.dto.TabDto;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.EventUtil;
import com.xzq.module_base.eventbus.MessageEvent;
import com.xzq.module_base.mvp.MvpContract;
import com.xzq.module_base.utils.GlideUtils;
import com.xzq.module_base.utils.XZQLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalFragment extends BasePresenterFragment implements MvpContract.UploadImgView , MvpContract.SearchView {


    private final static List<TabDto> orderList = new ArrayList<>();
    private final static List<TabDto> searchTypeList = new ArrayList<>();


    private DownwardManager downwardManager;

    @BindView(R.id.et_search)
    EditText mSearch;
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

    @BindView(R.id.flyt_parent)
    ViewGroup flytParent;
    @BindView(R.id.tv_type)
    TextView tv_type;

    @OnClick({R.id.headImg,R.id.tv_type,R.id.img_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.headImg:

                CommonUtils.selectHead(this);
                break;
            case R.id.tv_type:
                downwardManager.show(getBnt3List(), getSeleSearchType(), view);

                break;
            case R.id.img_search:
                if (type==0){
                    presenter.getSearchWaitDeal(keyword,countNum,searchScope);
                }else if (type==1){
                    presenter.getSearchNoticeForm(keyword,countNum,searchScope);
                }else {
                    presenter.getsearchFinishDeal(keyword,countNum,searchScope);
                }
                break;


        }
    }

    private List<TabDto> getBnt3List() {


        if (!searchTypeList.isEmpty()) {
            return searchTypeList;
        }

        TabDto tab = new TabDto();
        tab.name = "人资";
        tab.isSelected = true;
        searchTypeList.add(tab);

        tab = new TabDto();
        tab.name = "财务";
        searchTypeList.add(tab);

        tab = new TabDto();
        tab.name = "OA";
        searchTypeList.add(tab);


        return searchTypeList;
    }

    private TabDto getSeleSearchType() {

        if (searchTypeList.isEmpty()) {
            getBnt3List();
        }
        for (TabDto t :
                searchTypeList) {
            if (t.isSelected) {
                return t;
            }
        }
        return new TabDto();
    }


    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_personal;
    }


    String keyword="";
    @Override
    protected void initViews(Bundle savedInstanceState) {

        downwardManager = new DownwardManager(me, flytParent, tabSelectedListener);

        user.setText(User.getUser().getNickname());
        mobile.setText(User.getUser().getMobile()+"");
        postName.setText(User.getUser().getPost());
        jobName.setText(User.getUser().getJobName());
        if (!TextUtils.isEmpty(User.getUser().getHeadImgUrl())){
            GlideUtils.loadImage(ivHead, User.getUser().getHeadImgUrl());
        }


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


        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //回车等操作
                if (i == EditorInfo.IME_ACTION_SEND
                        || i == EditorInfo.IME_ACTION_DONE
                        || i == EditorInfo.IME_ACTION_SEARCH
                        || i == EditorInfo.IME_ACTION_GO
                        || (keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode()
                        && KeyEvent.ACTION_DOWN == keyEvent.getAction())) {
                    // 搜索
                     keyword = textView.getText().toString();

                    if (type==0){
                        presenter.getSearchWaitDeal(keyword,countNum,searchScope);
                    }else if (type==1){
                        presenter.getSearchNoticeForm(keyword,countNum,searchScope);
                    }else {
                        presenter.getsearchFinishDeal(keyword,countNum,searchScope);
                    }
                }
                return true;
            }
        });
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

    private int countNum;
    private int type = 0;//待办,通知,已办
    @Override
    public void onMessageEvent(@NonNull MessageEvent event) {
        super.onMessageEvent(event);

        if (event.equals(EventAction.WAITDEAL)){
            countNum = (int)event.getData();
            tvMsgCountTodo.setText(countNum+"");
            type = 0;
        }
        if (event.equals(EventAction.NOTICE)){
            countNum = (int)event.getData();
            tvMsgCountNotice.setText(countNum+"");
            type = 1;
        }
        if (event.equals(EventAction.FINISHDEAL)){
            countNum = (int)event.getData();
            tvMsgCountDone.setText(countNum+"");
            type = 2;
        }
    }

    @Override
    public void getSearchListSucceed(List<MatterDto> list) {
        if (list!=null){
            EventUtil.post(EventAction.UPDATE_Matter_LIST);
        }
    }



    @Override
    public void onStateEmpty() {

    }

    int searchScope = 0;
    private final DownwardManager.OnTabSelectedListener tabSelectedListener = new DownwardManager.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabDto seleTab, View v) {
            downwardManager.hide();
            switch (seleTab.name) {
                default:
                case "人资":
                    searchScope = 0;
                    break;
                case "财务":
                    searchScope = 1;
                    break;
                case "OA":
                    searchScope = 2;
                    break;
            }
            tv_type.setText(seleTab.name);

        }

        @Override
        public void onHide() {
        }

        @Override
        public void onCustomTime(String startTime, String endTime) {

        }
    };
}



