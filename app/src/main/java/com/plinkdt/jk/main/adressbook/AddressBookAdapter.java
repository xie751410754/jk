package com.plinkdt.jk.main.adressbook;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;

import com.plinkdt.jk.R;
import com.xzq.module_base.adapter.BaseRecyclerAdapter;
import com.xzq.module_base.bean.AddressBookItem;
import com.xzq.module_base.bean.StaffDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * AddressBookAdapter
 * Created by Tse on 2020/11/9.
 */
public class AddressBookAdapter extends BaseRecyclerAdapter<AddressBookItem, AddressBookViewHolder> {


    @NonNull
    @Override
    public AddressBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @Nullable View itemView, int viewType) {
        return new AddressBookViewHolder(itemView);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        switch (viewType) {
            default:
            case AddressBookViewHolder.TYPE_TITLE:
                return R.layout.item_address_book_title;
            case AddressBookViewHolder.TYPE_NORMAL:
                return R.layout.item_address_book_normal;
        }
    }

    @Override
    public int getItemViewType(int position) {
        AddressBookItem item = getDataAt(position);
        return item.isTitle() ? AddressBookViewHolder.TYPE_TITLE
                : AddressBookViewHolder.TYPE_NORMAL;
    }

    @Override
    public void onConvert(@NonNull AddressBookViewHolder holder, AddressBookItem data, int position, @NonNull List<Object> payload) {
        holder.setData(data);
    }

    @Override
    public void setData(List<AddressBookItem> data) {
        if (data != null) {
            this.mData.clear();
            this.mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public void setData(List<AddressBookItem> data, boolean hasNext) {
        //super.setData(data, hasNext);
    }

    @Override
    public boolean addData(List<AddressBookItem> data, boolean hasNext) {
        return true;
    }

    /**
     * 添加数据
     *
     * @param data .
     * @return .
     */
    boolean addData(List<AddressBookItem> data) {
        boolean hasChange = false;
        if (data != null) {
            int positionStart = getItemCount();
            int itemCount = data.size();
            if (mData.addAll(data)) {
                hasChange = true;
                notifyItemRangeChanged(positionStart, itemCount);
            }
        }
        return hasChange;
    }

    //正常的items，用于展开或者隐藏
    private final Map<Integer, List<AddressBookItem>> mNormalItems = new ArrayMap<>();

    /**
     * 隐藏
     *
     * @param titleItem 标题
     */
    void hide(AddressBookItem titleItem) {
        int titlePos = indexOf(titleItem);
        List<AddressBookItem> hideItems = mNormalItems.get(titleItem.getId());
        if (hideItems != null) {
            Iterator<AddressBookItem> iterator = getData().iterator();
            while (iterator.hasNext()) {
                AddressBookItem item = iterator.next();
                if (item.isNormal() && item.getId() == titleItem.getId()) {
                    iterator.remove();
                }
            }
            notifyItemRangeRemoved(titlePos + 1, hideItems.size());
        }
        titleItem.setShowNormal(false);
        notifyItemChanged(titlePos);
    }

    /**
     * 展开
     *
     * @param titleItem 标题
     */
    void show(AddressBookItem titleItem) {
        int titlePos = indexOf(titleItem);
        List<AddressBookItem> showItems = mNormalItems.get(titleItem.getId());
        if (showItems != null) {
            int posStart = titlePos + 1;
            getData().addAll(posStart, showItems);
            notifyItemRangeInserted(posStart, showItems.size());
        }
        titleItem.setShowNormal(true);
        notifyItemChanged(titlePos);
    }

    /**
     * 获取标题数量
     *
     * @return .
     */
    public int getTitleCount() {
        List<AddressBookItem> datas = getData();
        int total = 0;
        if (datas != null) {
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).isTitle()) {
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * 添加正常数据
     *
     * @param staffList 正常数据列表
     * @param titleItem 标题
     * @return .
     */
    List<AddressBookItem> addNormalItems(List<StaffDto> staffList, AddressBookItem titleItem) {
        final List<AddressBookItem> normalItems = new ArrayList<>();
        for (int j = 0, count = staffList.size(); j < count; j++) {
            StaffDto staff = staffList.get(j);
            AddressBookItem normalItem = new AddressBookItem();
            normalItem.setName(staff.getName());
            normalItem.setPhone(staff.getMobile());
            normalItem.setType(2);
            normalItem.setId(titleItem.getId());//绑定标题id
            normalItems.add(normalItem);
        }
        mNormalItems.put(titleItem.getId(), normalItems);
        return normalItems;
    }
}
