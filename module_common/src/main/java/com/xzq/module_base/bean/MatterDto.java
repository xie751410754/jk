package com.xzq.module_base.bean;

/**
 * MatterDto
 * Created by Tse on 2020/11/10.
 */
public class MatterDto {

    /**
     * tid : null
     * title : 云南省国有股权运营管理有限公司发文测试
     * content :
     * createTime : 20200731 11:58:46
     * workUrl : https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=149
     * appUrl : https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=149
     * billId : null
     * fprocinstId : null
     * tdType : 2
     */

    private String tid;
    private String title;
    private String content;
    private String createTime;
    private String workUrl;
    private String appUrl;
    private String billId;
    private String fprocinstId;
    private int tdType;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getWorkUrl() {
        return workUrl;
    }

    public void setWorkUrl(String workUrl) {
        this.workUrl = workUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getFprocinstId() {
        return fprocinstId;
    }

    public void setFprocinstId(String fprocinstId) {
        this.fprocinstId = fprocinstId;
    }

    public int getTdType() {
        return tdType;
    }

    public void setTdType(int tdType) {
        this.tdType = tdType;
    }
}
