package com.xzq.module_base.bean;

/**
 * StockDto
 * Created by xzq on 2019/10/10.
 */
public class StockDto {

    /**
     * purchaseNum	number
     * 必须
     * 采购数量
     * salesNum	number
     * 必须
     * 销售数量
     * goodsName	string
     * 必须
     * 商品名称
     * goodsTitle	string
     * 必须
     * sku标题
     * stockNum	number
     * 必须
     * 库存数量
     * afterSaleShip	number
     * 必须
     * 售后发货
     * afterSaleHandle	number
     * 必须
     * 售后处理
     */

    public String nickname;
    public String purchaseNum;
    public String salesNum;
    //public int skuPriceId;
    //public int goodsId;
    public String goodsName;
    public String goodsTitle;
    public String stockNum;
    //public int userId;
    public String afterSaleShip;
    public String afterSaleHandle;
    public String scrappedNum;
}
