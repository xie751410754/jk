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
 * Created by  on 2020/11/11.
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

    private int type;

    public MatterViewHolder(View itemView,OnHolderClickListener listener,int type) {
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
        this.type = type;
    }

    @Override
    public void setData(MatterDto data) {

//        if (data.getTdType()==0||data.getTdType()==1){
//            btnOa.setVisibility(View.INVISIBLE);
//            btnApprove.setVisibility(View.INVISIBLE);
//        }else {
//            btnOa.setVisibility(View.VISIBLE);
//            btnApprove.setVisibility(View.VISIBLE);
//        }

   if (type==0){
       btnOa.setVisibility(View.VISIBLE);
       btnApprove.setVisibility(View.VISIBLE);
   }else if (type==1){
       btnOa.setVisibility(View.VISIBLE);
       btnApprove.setVisibility(View.INVISIBLE);
   }else if (type==2){
       btnOa.setVisibility(View.VISIBLE);
       btnApprove.setVisibility(View.VISIBLE);
   }

        tvName.setText("          "+data.getTitle());
        tvDate.setText(data.getCreateTime());
    }

    public interface OnHolderClickListener {
        //呈批
        void onApproveClicked(MatterDto data);

        //OA
        void onOaClicked(MatterDto data);
    }
}
