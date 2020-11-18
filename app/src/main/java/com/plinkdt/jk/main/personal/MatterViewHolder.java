package com.plinkdt.jk.main.personal;

import android.view.View;
import android.widget.TextView;

import com.plinkdt.jk.R;
import com.xzq.module_base.adapter.BaseRecyclerViewHolder;
import com.xzq.module_base.bean.MatterDto;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MatterViewHolder
 * Created by Tse on 2020/11/11.
 */
public class MatterViewHolder extends BaseRecyclerViewHolder<MatterDto> {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.btn_oa)
    TextView btnOa;
    @BindView(R.id.btn_approve)
    TextView btnApprove;

    public MatterViewHolder(View itemView,OnHolderClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        btnOa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOaClicked(data);
            }
        });
        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onApproveClicked(data);
            }
        });
    }

    @Override
    public void setData(MatterDto data) {
        tvName.setText(data.getTitle());
        tvDate.setText(data.getCreateTime());
    }

    public interface OnHolderClickListener {
        //呈批
        void onApproveClicked(MatterDto data);

        //OA
        void onOaClicked(MatterDto data);
    }
}
