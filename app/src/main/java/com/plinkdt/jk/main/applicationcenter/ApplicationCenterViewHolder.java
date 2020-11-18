package com.plinkdt.jk.main.applicationcenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.plinkdt.jk.R;
import com.xzq.module_base.adapter.BaseRecyclerViewHolder;
import com.xzq.module_base.bean.ApplicationCenterDto;
import com.xzq.module_base.dto.TabDto;
import com.xzq.module_base.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TabViewHolder
 * Created by xzq on 2019/11/12.
 */
public class ApplicationCenterViewHolder extends BaseRecyclerViewHolder<ApplicationCenterDto> {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.img_imageview)
    ImageView imageView;

    public ApplicationCenterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(ApplicationCenterDto data) {
        tvName.setText(data.getClientName());
        GlideUtils.loadImage(imageView,data.getImage());

    }


}
