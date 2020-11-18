package com.xzq.module_base.bean;

import java.util.Locale;

/**
 * CollDto
 * Created by xzq on 2019/8/27.
 */
public class CollDto {
    public int id;
    public int collId;//商品id
    public String goodsName;
    public String goodsRemark;
    public double shopPrice;
    public double collPrice;
    public String goodsUrl;
    private int isOnSale;//是否上架0否1是

    public boolean isOnSale() {
        return isOnSale == 1;
    }

    public boolean isUp() {
        return shopPrice > collPrice;
    }

    public boolean showDiffPrice() {
        return shopPrice != collPrice;
    }

    public String getDiffPrice() {
        if (!showDiffPrice()) {
            return null;
        }
        String target = isUp() ? "上升" : "降低";
        String priceStr = String.format(Locale.getDefault(), "%1$s%2$.2f", "¥", Math.abs(shopPrice - collPrice));
        return String.format(Locale.getDefault(), "比加入时%1$s%2$s", target, priceStr);
    }

}
