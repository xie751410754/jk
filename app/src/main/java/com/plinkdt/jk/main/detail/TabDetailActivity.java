package com.plinkdt.jk.main.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xzq.module_base.arouter.LoginPath;
import com.xzq.module_base.base.BaseActivity;
import com.xzq.module_base.managers.MyGridLayoutManager;
import com.plinkdt.jk.R;
import com.plinkdt.jk.main.TabAdapter;
import com.plinkdt.jk.main.TabUtil;

import butterknife.BindView;

@Route(path = LoginPath.TAB_DETAIL)
public class TabDetailActivity extends BaseActivity {

    @BindView(R.id.rv_detail)
    RecyclerView rvDetail;
    private final TabAdapter tabAdapter = new TabAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_detail;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        int type = getIntent().getIntExtra("type", 0);
        setToolbar(TabUtil.getNameByType(type));
        rvDetail.setLayoutManager(new MyGridLayoutManager(me, TabUtil.SPAN_COUNT));
        rvDetail.setAdapter(tabAdapter);
        tabAdapter.setData(TabUtil.getLisByType(type));
    }
}
