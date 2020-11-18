package com.plinkdt.jk.main.personal;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xzq.module_base.User;
import com.xzq.module_base.adapter.BaseRecyclerViewHolder;
import com.xzq.module_base.base.BaseListFragment;
import com.xzq.module_base.bean.MatterDto;
import com.xzq.module_base.mvp.MvpContract;
import com.xzq.module_base.utils.EntitySerializerUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.XZQLog;

import java.util.List;

/**
 * MatterFragment
 * Created by Tse on 2020/11/10.
 */
public class MatterFragment extends BaseListFragment<MvpContract.CommonPresenter, MatterDto> implements MatterViewHolder.OnHolderClickListener, MvpContract.MobileAssignURLView {

    public static final String JSON = "{\n" +
            "    \"count\": 6,\n" +
            "    \"pages\": null,\n" +
            "    \"current\": null,\n" +
            "    \"code\": 200,\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"tid\": null,\n" +
            "            \"title\": \"请假-李燕-2020-07-10\",\n" +
            "            \"content\": \"\",\n" +
            "            \"createTime\": \"20200731 12:07:49\",\n" +
            "            \"workUrl\": \"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=173\",\n" +
            "            \"appUrl\": \"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=173\",\n" +
            "            \"billId\": null,\n" +
            "            \"fprocinstId\": null,\n" +
            "            \"tdType\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"tid\": null,\n" +
            "            \"title\": \"请假-李燕-2020-01-21\",\n" +
            "            \"content\": \"\",\n" +
            "            \"createTime\": \"20200710 14:29:47\",\n" +
            "            \"workUrl\": \"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=169\",\n" +
            "            \"appUrl\": \"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=169\",\n" +
            "            \"billId\": null,\n" +
            "            \"fprocinstId\": null,\n" +
            "            \"tdType\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"tid\": null,\n" +
            "            \"title\": \"请假-王雅丹-2020-05-11\",\n" +
            "            \"content\": \"\",\n" +
            "            \"createTime\": \"20200511 11:43:48\",\n" +
            "            \"workUrl\": \"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=172\",\n" +
            "            \"appUrl\": \"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=172\",\n" +
            "            \"billId\": null,\n" +
            "            \"fprocinstId\": null,\n" +
            "            \"tdType\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"tid\": null,\n" +
            "            \"title\": \"云南省人民政府办公厅关于印发“数字云南”信息通信基础设施建设三年行动计划（2019—2021年）的通知\",\n" +
            "            \"content\": \"\",\n" +
            "            \"createTime\": \"20191229 21:46:30\",\n" +
            "            \"workUrl\": \"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=167\",\n" +
            "            \"appUrl\": \"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=167\",\n" +
            "            \"billId\": null,\n" +
            "            \"fprocinstId\": null,\n" +
            "            \"tdType\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"tid\": null,\n" +
            "            \"title\": \"云南省人民政府办公厅关于建立营商环境“红黑榜”制度的通知\",\n" +
            "            \"content\": \"\",\n" +
            "            \"createTime\": \"20191229 21:43:01\",\n" +
            "            \"workUrl\": \"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=166\",\n" +
            "            \"appUrl\": \"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=166\",\n" +
            "            \"billId\": null,\n" +
            "            \"fprocinstId\": null,\n" +
            "            \"tdType\": 2\n" +
            "        },\n" +
            "        {\n" +
            "            \"tid\": null,\n" +
            "            \"title\": \"云南省人民政府关于推进内贸流通现代化建设法治化营商环境的实施意见\",\n" +
            "            \"content\": \"\",\n" +
            "            \"createTime\": \"20191229 21:40:13\",\n" +
            "            \"workUrl\": \"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=164\",\n" +
            "            \"appUrl\": \"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=164\",\n" +
            "            \"billId\": null,\n" +
            "            \"fprocinstId\": null,\n" +
            "            \"tdType\": 2\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static MatterFragment newInstance(int pos) {

        Bundle args = new Bundle();
        args.putInt("pos",pos);
        MatterFragment fragment = new MatterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private final MatterAdapter matterAdapter = new MatterAdapter(this);

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


        XZQLog.debug("login error = " + getArguments().getInt("pos",0));

        switch (getArguments().getInt("pos",0)){
            case 0:
                presenter.getWaitDeal();

                break;
            case 1:
                presenter.getnoticeForm();
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

    @Override
    public void setData(List<MatterDto> list, int page, boolean hasNextPage, int totalCount) {
        super.setData(list, page, hasNextPage, totalCount);
        //matterAdapter.setData(list);
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        matterAdapter.setOnItemClickListener(new BaseRecyclerViewHolder.OnItemClickListener<MatterDto>() {
            @Override
            public void onItemClicked(View v, MatterDto data, int pos) {

                if (data.getTdType()==2){
                    WebActivity.start(me,data.getWorkUrl()+"&token="+ User.getToken());

                }else if (data.getTdType()==0||data.getTdType()==1){
                    presenter.getMobileAssignURL(data.getTdType(),data.getTid());
                }

            }
        });
    }

    @Override
    public void onApproveClicked(MatterDto data) {
        MyToast.show("呈批");
    }

    @Override
    public void onOaClicked(MatterDto data) {
        MyToast.show("OA");
    }

    @Override
    public void onMobileAssignURLSucceed(String data) {
        WebActivity.start(me,data);
    }
}
