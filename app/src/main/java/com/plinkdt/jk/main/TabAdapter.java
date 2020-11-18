package com.plinkdt.jk.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.xzq.module_base.adapter.BaseRecyclerAdapter;
import com.xzq.module_base.dto.TabDto;
import com.plinkdt.jk.R;

import java.util.List;

/**
 * TabAdapter
 * Created by xzq on 2019/11/12.
 */
public class TabAdapter extends BaseRecyclerAdapter<TabDto, TabViewHolder> {

    public TabAdapter() {
        setOnItemClickListener((v, data, pos) -> TabUtil.openPage(data));
    }

    @NonNull
    @Override
    public TabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @Nullable View itemView, int viewType) {
        return new TabViewHolder(itemView);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_main_tab;
    }

    @Override
    public void onConvert(@NonNull TabViewHolder holder, TabDto data, int position, @NonNull List<Object> payload) {
        holder.setData(data);
    }
}
