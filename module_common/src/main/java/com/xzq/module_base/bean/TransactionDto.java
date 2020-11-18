package com.xzq.module_base.bean;

import android.text.TextUtils;

/**
 * TransactionDto
 * Created by xzq on 2019/8/28.
 */
public class TransactionDto {
    public int id;
    public int moneyType;
    private int status;//状态(0：入,1：出)
    public String price;
    public int type;
    public String title;
    public String sn;
    public String createDate;

    public String getMoney() {
        return (isIncome() ? "+" : "-") + price;
    }

    public boolean isIncome() {
        return status == 0;
    }

    public String getTitle() {
        return getTypeName() + getMoneyTypeName();
    }

    //交易方式（0：代理商下单,4:积分,7：库存，8：余额；9：奖金；10:线下支付）
    public String getMoneyTypeName() {
        String typeStr;
        switch (moneyType) {
            default:
                typeStr = "";
                break;
            case 0:
                typeStr = "";
                break;
            case 4:
                typeStr = "积分";
                break;
            case 7:
                typeStr = "库存";
                break;
            case 8:
                typeStr = "余额";
                break;
            case 9:
                typeStr = "奖金";
                break;
            case 10:
                typeStr = "线下支付";
                break;
        }
        return TextUtils.isEmpty(typeStr) ? typeStr : ("(" + typeStr + ")");
    }

    //交易类型(2：购物消费,4:申请退货，5：押金，6：提现)
    public String getTypeName() {
        String typeStr;
        switch (type) {
            default:
                typeStr = "";
                break;
            case 2:
                typeStr = "购物消费";
                break;
            case 4:
                typeStr = "申请退货";
                break;
            case 5:
                typeStr = "押金";
                break;
            case 6:
                typeStr = "提现";
                break;
        }
        return typeStr;
    }
}
