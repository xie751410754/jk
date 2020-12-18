package com.plinkdt.jk.main;

import android.view.View;
import android.widget.TextView;

import com.xzq.module_base.adapter.BaseRecyclerViewHolder;
import com.xzq.module_base.dto.TabDto;
import com.plinkdt.jk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TabViewHolder
 * Created by xzq on 2019/11/12.
 */
public class TabViewHolder extends BaseRecyclerViewHolder<TabDto> {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_msg_count)
    TextView tvMsgCount;

    public TabViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(TabDto data) {
        tvName.setText(data.name);
//        tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0, data.resId, 0, 0);
        updateUnreadNum(data);
    }

    void updateUnreadNum(TabDto data) {
//        String msgCount = data.getMsgCount();
//        tvMsgCount.setVisibility(data.hasMsg() ? View.VISIBLE : View.GONE);
//        tvMsgCount.setText(msgCount);
    }
}
