package com.xzq.module_base.bean;

/**
 * CartDto
 * Created by xzq on 2019/9/3.
 */
public class CartDto {

    /**
     * id : 16
     * num : 10
     * totalPrice : 25000
     * goodsMoney : 2500
     * goodsId : 1136
     * goodsName : 黑鲨游戏手机2
     * url : http://129.204.156.192:80/qixing/attachment/goods/album/20190830-ab026a22-d957-4ec5-93ca-ac473aea6e07.jpg
     * isOnSale : 1
     * goodsTitle : 极夜黑_4GB+64GB
     * skuId : 22
     */

    public int id;
    public int num;
    public double totalPrice;
    public double goodsMoney;
    public int goodsId;
    public String goodsName;
    public String url;
    public int isOnSale;
    public String goodsTitle;
    public int skuId;

    public boolean isSettleSelected = true;

    public boolean isDeleteSelected = false;

    public boolean isSettleMode = true;

    public double getPriceStr() {
        return goodsMoney;
    }

    public boolean getChecked() {
        return isSettleMode ? isSettleSelected : isDeleteSelected;
    }
}
