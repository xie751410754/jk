package com.plinkdt.jk.main.adressbook;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.plinkdt.jk.R;
import com.xzq.module_base.adapter.BaseRecyclerViewHolder;
import com.xzq.module_base.bean.AddressBookItem;

/**
 * AddressBookViewHolder
 * Created by Tse on 2020/11/9.
 */
public class AddressBookViewHolder extends BaseRecyclerViewHolder<AddressBookItem> {

    public static final int TYPE_TITLE = 1;
    public static final int TYPE_NORMAL = 2;
    private TextView tvName;
    private TextView tvPhone;
    private ImageView ivArrow;

    public AddressBookViewHolder(View itemView) {
        super(itemView);
        ivArrow = itemView.findViewById(R.id.iv_arrow);
        tvName = itemView.findViewById(R.id.tv_name);
        tvPhone = itemView.findViewById(R.id.tv_phone);
    }

    @Override
    public void setData(AddressBookItem data) {
        tvName.setText(data.getName());
        if (tvPhone != null) {
            tvPhone.setText(data.getPhone());
        }
        if (ivArrow!=null){
            ivArrow.setSelected(data.isShowNormal());
        }
    }
}
