package com.plinkdt.jk.main.applicationcenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.plinkdt.jk.R;
import com.plinkdt.jk.main.TabUtil;
import com.plinkdt.jk.main.TabViewHolder;
import com.xzq.module_base.adapter.BaseRecyclerAdapter;
import com.xzq.module_base.bean.ApplicationCenterDto;
import com.xzq.module_base.dto.TabDto;

import java.util.List;

/**
 * TabAdapter
 * Created by xzq on 2019/11/12.
 */
public class ApplicationCenterAdapter extends BaseRecyclerAdapter<ApplicationCenterDto, ApplicationCenterViewHolder> {

    @NonNull
    @Override
    public ApplicationCenterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @Nullable View itemView, int viewType) {
        return new ApplicationCenterViewHolder(itemView);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_application_center;
    }

    @Override
    public void onConvert(@NonNull ApplicationCenterViewHolder holder, ApplicationCenterDto data, int position, @NonNull List<Object> payload) {
        holder.setData(data);
    }
}
