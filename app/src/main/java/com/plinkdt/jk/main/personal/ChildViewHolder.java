package com.plinkdt.jk.main.personal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.plinkdt.jk.R;
import com.xzq.module_base.adapter.BaseRecyclerViewHolder;
import com.xzq.module_base.dto.TabDto;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ChildViewHolder
 * Created by xzq on 2019/12/2.
 */
public class ChildViewHolder extends BaseRecyclerViewHolder<TabDto> {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.iv_checked)
    ImageView ivChecked;

    public ChildViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(TabDto data) {
        tvName.setText(data.name);
        ivArrow.setImageResource(data.arrowId);
        tvName.setSelected(data.isSelected);
        ivArrow.setSelected(data.isSelected);
        ivChecked.setImageResource(data.isSelected ? R.drawable.icon_drop_downlist_selected : 0);
    }
}





