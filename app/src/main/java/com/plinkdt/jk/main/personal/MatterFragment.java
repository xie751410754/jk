package com.plinkdt.jk.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.xzq.module_base.User;
import com.xzq.module_base.adapter.BaseRecyclerViewHolder;
import com.xzq.module_base.base.BaseListFragment;
import com.xzq.module_base.bean.MatterDto;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.EventUtil;
import com.xzq.module_base.eventbus.MessageEvent;
import com.xzq.module_base.mvp.AbsPresenter;
import com.xzq.module_base.mvp.MvpContract;
import com.xzq.module_base.utils.EntitySerializerUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.XZQLog;

import java.util.ArrayList;
import java.util.List;

/**
 * MatterFragment
 * Created by  on 2020/11/10.
 */
public class MatterFragment extends BaseListFragment<MvpContract.CommonPresenter, MatterDto> implements MatterViewHolder.OnHolderClickListener, MvpContract.MobileAssignURLView {


    public static MatterFragment newInstance(int pos) {

        Bundle args = new Bundle();
        args.putInt("pos", pos);


        MatterFragment fragment = new MatterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private  MatterAdapter matterAdapter = new MatterAdapter(this);

    @Override
    protected RecyclerView.Adapter getPageAdapter() {
        return matterAdapter;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void getList() {
        super.getList();


        XZQLog.debug("login error = " + getArguments().getInt("pos", 0));

        switch (getArguments().getInt("pos", 0)) {
            case 0:
                presenter.getWaitDeal(0,countNum);

                break;
            case 1:
                presenter.getnoticeForm(noticeSize);
                break;

            case 2:
                presenter.getFinishDeal();

                break;

        }

//        try {
//            JsonBean jsonBean =  EntitySerializerUtils.deserializerEntity(JSON,JsonBean.class);
//            setData(jsonBean.getData(),1,false,0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    List<MatterDto> list = new ArrayList<>();
    private int countNum = 10;
    private int noticeSize = 1000;
    @Override
    public void setData(List<MatterDto> list, int page, boolean hasNextPage, int totalCount) {
        super.setData(list, page, hasNextPage, totalCount);
        //matterAdapter.setData(list);


        countNum = totalCount;

        switch (getArguments().getInt("pos", 0)) {
            case 0:
                EventUtil.post(EventAction.WAITDEAL, totalCount);

                break;
            case 1:
                EventUtil.post(EventAction.NOTICE, totalCount);

                break;
            case 2:
                EventUtil.post(EventAction.FINISHDEAL, totalCount);

                break;

        }



    }

    @Override
    public void addData(List<MatterDto> list, int page, boolean hasNextPage, int totalCount) {
        super.addData(list, page, hasNextPage, totalCount);

        matterAdapter.setData(list);

    }
    private int type;

    String titleName;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        XZQLog.debug("token===",User.getToken());
        type = getArguments().getInt("pos", 0);
        matterAdapter.setType(type);
        matterAdapter.setOnItemClickListener(new BaseRecyclerViewHolder.OnItemClickListener<MatterDto>() {
            @Override
            public void onItemClicked(View v, MatterDto data, int pos) {

                if (data.getTdType() == 2) {
                    WebActivity.start(me, data.getAppUrl() + "&token=" + User.getToken(),data.getTitle());
                } else if (data.getTdType() == 0 || data.getTdType() == 1) {

                    if (data.getTdType() == 0){
                        titleName = data.getTitle();
                    }else {
                        titleName = data.getTitle();
                    }
                    presenter.getMobileAssignURL(data.getTdType(), data.getTid());
                }

            }
        });
    }



    @Override
    protected void isVisibleToUser(boolean isVisibleToUser) {
        XZQLog.debug("visible=="+isVisibleToUser);
        if (isVisibleToUser){
            if (getArguments().getInt("pos")==0){
                if (presenter!=null){
                    presenter.getWaitDeal(0,countNum);
                }
            }else if (getArguments().getInt("pos")==1){
                if (presenter!=null){
                    presenter.getnoticeForm(noticeSize);

                }
            }else if (getArguments().getInt("pos")==2){
                if (presenter!=null){
                    presenter.getFinishDeal();

                }
            }
        }

    }

    @Override
    public void onApproveClicked(MatterDto data) {
//        MyToast.show("呈批");
    }

    @Override
    public void onOaClicked(MatterDto data) {
//        MyToast.show("OA");
    }

    @Override
    public void onMobileAssignURLSucceed(String data) {
        if (data!=null){
            WebActivity.start(me, data,titleName);
        }
    }

    @SuppressWarnings("all")
    @Override
    public void onMessageEvent(@NonNull MessageEvent event) {
        super.onMessageEvent(event);
        if (event.equals(EventAction.MY)) {
            switch (getArguments().getInt("pos", 0)) {
                case 0:
                    presenter.getWaitDeal(0,countNum);

                    break;
                case 1:
                    presenter.getnoticeForm(noticeSize);
                    break;

                case 2:
                    presenter.getFinishDeal();

                    break;

            }

        }

        if (event.equals(EventAction.UPDATE_Matter_LIST)){
            list = (List<MatterDto>)event.getData();
            matterAdapter.setData(list);
        }
    }

}
