package com.xzq.module_base.bean;

import java.util.List;

public class MyJsonDto {

    /**
     * count : 6
     * pages : null
     * current : null
     * code : 200
     * data : [{"tid":null,"title":"请假-李燕-2020-07-10","content":"","createTime":"20200731 12:07:49","workUrl":"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=173","appUrl":"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=173","billId":null,"fprocinstId":null,"tdType":2},{"tid":null,"title":"请假-李燕-2020-01-21","content":"","createTime":"20200710 14:29:47","workUrl":"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=169","appUrl":"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=169","billId":null,"fprocinstId":null,"tdType":2},{"tid":null,"title":"请假-王雅丹-2020-05-11","content":"","createTime":"20200511 11:43:48","workUrl":"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=172","appUrl":"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=172","billId":null,"fprocinstId":null,"tdType":2},{"tid":null,"title":"云南省人民政府办公厅关于印发\u201c数字云南\u201d信息通信基础设施建设三年行动计划（2019\u20142021年）的通知","content":"","createTime":"20191229 21:46:30","workUrl":"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=167","appUrl":"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=167","billId":null,"fprocinstId":null,"tdType":2},{"tid":null,"title":"云南省人民政府办公厅关于建立营商环境\u201c红黑榜\u201d制度的通知","content":"","createTime":"20191229 21:43:01","workUrl":"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=166","appUrl":"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=166","billId":null,"fprocinstId":null,"tdType":2},{"tid":null,"title":"云南省人民政府关于推进内贸流通现代化建设法治化营商环境的实施意见","content":"","createTime":"20191229 21:40:13","workUrl":"https://oa.yngygq.com/login/OAuth2Callback.jsp?gotoUrl=/workflow/request/ViewRequest.jsp?requestid=164","appUrl":"https://oawap.yngygq.com/wxapi/wxclientmenu/ab66d027d34244a39aefc6a35ee02163?targeturl=/mobile/plugin/1/view.jsp?detailid=164","billId":null,"fprocinstId":null,"tdType":2}]
     */

    private int count;
    private Object pages;
    private Object current;
    private int code;
    private List<MatterDto> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getPages() {
        return pages;
    }

    public void setPages(Object pages) {
        this.pages = pages;
    }

    public Object getCurrent() {
        return current;
    }

    public void setCurrent(Object current) {
        this.current = current;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<MatterDto> getData() {
        return data;
    }

    public void setData(List<MatterDto> data) {
        this.data = data;
    }
}
