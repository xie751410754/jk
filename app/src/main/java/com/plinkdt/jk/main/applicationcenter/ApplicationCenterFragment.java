package com.plinkdt.jk.main.applicationcenter;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plinkdt.jk.R;
import com.plinkdt.jk.main.personal.WebActivity;
import com.xzq.module_base.User;
import com.xzq.module_base.adapter.BaseRecyclerViewHolder;
import com.xzq.module_base.base.BaseListFragment;
import com.xzq.module_base.bean.ApplicationCenterDto;
import com.xzq.module_base.mvp.MvpContract;

import java.util.ArrayList;
import java.util.List;

public class ApplicationCenterFragment extends BaseListFragment<MvpContract.CommonPresenter, ApplicationCenterDto> implements MvpContract.ApplicationView {

    List<Integer> images = new ArrayList<>();
    ApplicationCenterAdapter applicationCenterAdapter = new ApplicationCenterAdapter();


    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_applicationcenter;
    }


    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
//        super.initRecyclerView(recyclerView);

        GridLayoutManager layoutManage = new GridLayoutManager(me, 4);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(me);
        recyclerView.setLayoutManager(layoutManage);
    }

    @Override
    protected void getList() {
        super.getList();
        presenter.findAllApplication();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        applicationCenterAdapter.setOnItemClickListener(new BaseRecyclerViewHolder.OnItemClickListener<ApplicationCenterDto>() {
            @Override
            public void onItemClicked(View v, ApplicationCenterDto data, int pos) {
                presenter.applicationUrl(data.getClientId());
            }
        });


    }

    @Override
    public void setData(List<ApplicationCenterDto> list, int page, boolean hasNextPage, int totalCount) {
        super.setData(list, page, hasNextPage, totalCount);
    }

    @Override
    protected RecyclerView.Adapter getPageAdapter() {
        return applicationCenterAdapter;
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onGetApplicationView(String user) {
        WebActivity.start(me,user+ User.getToken());
    }
}
