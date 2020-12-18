package com.plinkdt.jk.main.adressbook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.plinkdt.jk.R;
import com.plinkdt.jk.VPNLoginActivity;
import com.xzq.module_base.User;
import com.xzq.module_base.adapter.BaseRecyclerViewHolder;
import com.xzq.module_base.base.BaseListFragment;
import com.xzq.module_base.bean.AddressBookItem;
import com.xzq.module_base.bean.NoticeDto;
import com.xzq.module_base.bean.NoticeListDto;
import com.xzq.module_base.bean.StaffDto;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.MessageEvent;
import com.xzq.module_base.mvp.MvpContract;
import com.xzq.module_base.utils.AppUtils;
import com.xzq.module_base.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressBookFragment extends BaseListFragment<MvpContract.CommonPresenter, NoticeListDto.DetailsBean> {

    private final AddressBookAdapter addressBookAdapter = new AddressBookAdapter();


    @BindView(R.id.et_search)
    EditText mSearch;

    @Override
    protected int getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_addressbook;
    }




    @OnClick(R.id.img_search)
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.img_search:

                presenter.getNoticeUserList(keyword);

                break;


        }
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

    String keyword ="";
    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //回车等操作
                if (i == EditorInfo.IME_ACTION_SEND
                        || i == EditorInfo.IME_ACTION_DONE
                        || i == EditorInfo.IME_ACTION_SEARCH
                        || i == EditorInfo.IME_ACTION_GO
                        || (keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode()
                        && KeyEvent.ACTION_DOWN == keyEvent.getAction())) {
                    // 搜索
                    keyword = textView.getText().toString();
                    presenter.getNoticeUserList(keyword);
                }
                return true;
            }
        });
    }


    @Override
    protected RecyclerView.Adapter getPageAdapter() {
        return addressBookAdapter;
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onStateEmpty() {

    }

    private boolean isUseTestData = false;

    @Override
    public void setData(List<NoticeListDto.DetailsBean> list, int page, boolean hasNextPage, int totalCount) {

        List<NoticeDto> result =  getNotices(list);

        //添加测试数据
//        if (isUseTestData) {
//            for (int i = 0; i < 10; i++) {
//                NoticeDto dto = new NoticeDto();
//                dto.setDept("财务部" + i);
//                List<StaffDto> staffList = new ArrayList<>();
//                if (i > 0) {
//                    for (int j = 0; j < 5; j++) {
//                        StaffDto staff = new StaffDto();
//                        staff.setName("王琦" + i + "-" + j);
//                        staff.setMobile("12345678910");
//                        staffList.add(staff);
//                    }
//                }
//                dto.setStaff(staffList);
//                list.add(dto);
//            }
//        }


        //二次处理数据
        List<AddressBookItem> bookItems = mapResult(result);

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
    public void addData(List<NoticeListDto.DetailsBean> list, int page, boolean hasNextPage, int totalCount) {

//        if (isUseTestData) {
//            int startIndex = addressBookAdapter.getTitleCount();
//            for (int i = 0; i < 5; i++) {
//                NoticeDto dto = new NoticeDto();
//                int id = i + startIndex;
//                dto.setDept("财务部" + id);
//                List<StaffDto> staffList = new ArrayList<>();
//                for (int j = 0; j < 8; j++) {
//                    StaffDto staff = new StaffDto();
//                    staff.setName("王琦" + id + "-" + j);
//                    staff.setMobile("12345678910");
//                    staffList.add(staff);
//                }
//                dto.setStaff(staffList);
//                list.add(dto);
//            }
//        }
        List<NoticeDto> result =  getNotices(list);
        List<AddressBookItem> bookItems = mapResult(result);

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

    private  List<NoticeDto> getNotices(List<NoticeListDto.DetailsBean> list){
        List<NoticeDto> result = new ArrayList<>();
        if (list!=null){
            for (int i = 0; i < list.size(); i++) {
                NoticeListDto.DetailsBean bean =  list.get(i);
              if ( bean.getAddressBook()!=null){
                  result.addAll(  bean.getAddressBook());
              }

            }

        }
        return result;
    }


    @Override
    public void onMessageEvent(@NonNull MessageEvent event) {
        super.onMessageEvent(event);
        if (event.equals(EventAction.ADRESSBOOK)) {
            presenter.getNoticeList(User.getUser().getOrgId());
        }
    }
}
