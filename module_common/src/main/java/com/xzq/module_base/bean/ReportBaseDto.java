package com.xzq.module_base.bean;

import com.xzq.module_base.api.BaseListBean;

/**
 * ReportBaseDto
 * Created by xzq on 2019/10/10.
 */
public class ReportBaseDto<T> extends BaseListBean<T> {
    public String salesPrice;//销售总价
    public String agentPrice;//成本总价
    public String profitPrice;//利润

    public String getSalesPrice() {
        return "销售总价：" + salesPrice;
    }

    public String getAgentPrice() {
        return "成本总价：" + agentPrice;
    }

    public String getProfitPrice() {
        return "利润：" + profitPrice;
    }
}
