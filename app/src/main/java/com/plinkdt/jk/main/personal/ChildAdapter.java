package com.plinkdt.jk.main.personal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.plinkdt.jk.R;
import com.xzq.module_base.adapter.BaseRecyclerAdapter;
import com.xzq.module_base.adapter.BaseRecyclerViewHolder;
import com.xzq.module_base.dto.TabDto;

import java.util.List;

/**
 * ChildAdapter
 * Created by xzq on 2019/12/2.
 */
public class ChildAdapter extends BaseRecyclerAdapter<TabDto, ChildViewHolder> {

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @Nullable View itemView, int viewType) {
        return new ChildViewHolder(itemView);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_tab;
    }

    @Override
    public void onConvert(@NonNull ChildViewHolder holder, TabDto data, int position, @NonNull List<Object> payload) {
        holder.setData(data);
    }

    public void checkTab(TabDto data) {
        if (data.isSelected) {
            return;
        }
        List<TabDto> list = getData();
        if (list != null) {
            for (TabDto dto : list) {
                dto.isSelected = false;
            }
        }
        data.isSelected = true;
        if (listener != null) {
            listener.onTabChange(data);
        }
        notifyDataSetChanged();
    }

    public void setListener(OnTabChangeListener listener) {
        this.listener = listener;
        setOnItemClickListener(listener);
    }

    private OnTabChangeListener listener;

    public interface OnTabChangeListener extends BaseRecyclerViewHolder.OnItemClickListener<TabDto> {
        void onTabChange(TabDto tab);
    }
}
