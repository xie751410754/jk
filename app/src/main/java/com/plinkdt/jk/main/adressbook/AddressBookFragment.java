package com.plinkdt.jk.main.adressbook;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.plinkdt.jk.R;
import com.xzq.module_base.User;
import com.xzq.module_base.adapter.BaseRecyclerViewHolder;
import com.xzq.module_base.base.BaseListFragment;
import com.xzq.module_base.bean.AddressBookItem;
import com.xzq.module_base.bean.NoticeDto;
import com.xzq.module_base.bean.StaffDto;
import com.xzq.module_base.mvp.MvpContract;
import com.xzq.module_base.utils.AppUtils;
import com.xzq.module_base.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

public class AddressBookFragment extends BaseListFragment<MvpContract.CommonPresenter, NoticeDto> {

    private final AddressBookAdapter addressBookAdapter = new AddressBookAdapter();


    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_addressbook;
    }


    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        super.initRecyclerView(recyclerView);
        addressBookAdapter.setOnItemClickListener(new BaseRecyclerViewHolder.OnItemClickListener<AddressBookItem>() {
            @Override
            public void onItemClicked(View v, AddressBookItem data, int pos) {
                //MyToast.show(data.isTitle() ? "部门" : "员工");
                if (data.isTitle()) {
                    if (data.isShowNormal()) {
                        //隐藏
                        addressBookAdapter.hide(data);
                    } else {
                        //显示
                        addressBookAdapter.show(data);
                    }
                } else {
                    if(TextUtils.isEmpty(data.getPhone())){
                        MyToast.show("无号码");
                    }else {
                        if (AppUtils.isMobileNO(data.getPhone())){
                            AppUtils.openDial(me, data.getPhone());
                        }else {
                            MyToast.show("异常号码");
                        }

                    }
                }
            }
        });
    }

    @Override
    protected void getList() {
        super.getList();
        presenter.getNoticeList(User.getUser().getOrgId());
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
    }


    @Override
    protected RecyclerView.Adapter getPageAdapter() {
        return addressBookAdapter;
    }

    @Override
    protected void loadData() {
    }

    private boolean isUseTestData = false;

    @Override
    public void setData(List<NoticeDto> list, int page, boolean hasNextPage, int totalCount) {

        //添加测试数据
        if (isUseTestData) {
            for (int i = 0; i < 10; i++) {
                NoticeDto dto = new NoticeDto();
                dto.setDept("财务部" + i);
                List<StaffDto> staffList = new ArrayList<>();
                if (i > 0) {
                    for (int j = 0; j < 5; j++) {
                        StaffDto staff = new StaffDto();
                        staff.setName("王琦" + i + "-" + j);
                        staff.setMobile("12345678910");
                        staffList.add(staff);
                    }
                }
                dto.setStaff(staffList);
                list.add(dto);
            }
        }


        //二次处理数据
        List<AddressBookItem> bookItems = mapResult(list);

        if (isUseTestData) {
            hasNextPage = true;
            if (hasNextPage) {
                mPage++;
            }
        }

        super.setData(list, page, hasNextPage, totalCount);
        addressBookAdapter.setData(bookItems);
    }

    @Override
    public void addData(List<NoticeDto> list, int page, boolean hasNextPage, int totalCount) {

        if (isUseTestData) {
            int startIndex = addressBookAdapter.getTitleCount();
            for (int i = 0; i < 5; i++) {
                NoticeDto dto = new NoticeDto();
                int id = i + startIndex;
                dto.setDept("财务部" + id);
                List<StaffDto> staffList = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    StaffDto staff = new StaffDto();
                    staff.setName("王琦" + id + "-" + j);
                    staff.setMobile("12345678910");
                    staffList.add(staff);
                }
                dto.setStaff(staffList);
                list.add(dto);
            }
        }

        List<AddressBookItem> bookItems = mapResult(list);

        if (isUseTestData) {
            hasNextPage = true;
            if (hasNextPage) {
                mPage++;
            }
        }

        super.addData(list, page, hasNextPage, totalCount);
        addressBookAdapter.addData(bookItems);
    }

    //二次处理数据
    private List<AddressBookItem> mapResult(List<NoticeDto> list) {
        int titleCount = addressBookAdapter.getTitleCount();
        List<AddressBookItem> bookItems = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int titleId = titleCount + i;//为标题生成id
            NoticeDto dto = list.get(i);
            AddressBookItem titleItem = new AddressBookItem();
            titleItem.setName(dto.getDept());
            titleItem.setType(1);
            titleItem.setId(titleId);
            titleItem.setShowNormal(dto.isExpand());
            bookItems.add(titleItem);

            if (dto.hasStaffList()) {
                List<AddressBookItem> normalItems = addressBookAdapter.addNormalItems(dto.getStaff(), titleItem);
                if (titleItem.isShowNormal()) {
                    bookItems.addAll(normalItems);
                }
            }
        }
        return bookItems;
    }
}
