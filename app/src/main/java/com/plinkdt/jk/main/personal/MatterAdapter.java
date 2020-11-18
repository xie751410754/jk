package com.plinkdt.jk.main.personal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.plinkdt.jk.R;
import com.xzq.module_base.adapter.BaseRecyclerAdapter;
import com.xzq.module_base.bean.MatterDto;

import java.util.List;

/**
 * MatterAdapter
 * Created by Tse on 2020/11/11.
 */
public class MatterAdapter extends BaseRecyclerAdapter<MatterDto, MatterViewHolder> {

    private final MatterViewHolder.OnHolderClickListener listener;

    public MatterAdapter(MatterViewHolder.OnHolderClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MatterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @Nullable View itemView, int viewType) {
        return new MatterViewHolder(itemView, listener);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_matter;
    }

    @Override
    public void onConvert(@NonNull MatterViewHolder holder, MatterDto data, int position, @NonNull List<Object> payload) {
        holder.setData(data);
    }
}
