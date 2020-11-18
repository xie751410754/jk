package com.xzq.module_base.bean;

/**
 * BillDto
 * Created by xzq on 2019/10/8.
 */
public class BillDto {

    /**
     * id : 1
     * userId : 146
     * userName : 谢振琪
     * status : 1
     * money : 1
     * property : 0
     * createDate : 2019-10-08 15:32:39
     * orderCode : 20191008153238721148767-WITHDRAW
     * error :
     */

    public int id;
    public int userId;
    public String userName;
    public int status;
    public String money;
    public int property;
    public String createDate;
    public String orderCode;
    public String error;
    public String bankNo;

    public String getTitle() {
        String head = property == 0 ? "余额" : "奖金";
        return head + "收入提现";
    }

    public String getBankNumber() {
        final String bankNumber = bankNo;
        String lastNum;
        if (bankNumber != null && bankNumber.length() > 4) {
            lastNum = bankNumber.substring(bankNumber.length() - 4);
        } else {
            lastNum = bankNumber;
        }
        return "提现至尾号" + lastNum + "储蓄卡";
    }

    public String getMoney() {
        return "+" + money;
    }
}
