package com.xzq.module_base.bean;

import android.text.TextUtils;

/**
 * StoreDFto
 * Created by xzq on 2019/9/5.
 */
public class StoreDto {
    public int goodsId;
    public String goodsName;
    public String goodsTitle;
    public String stockNum;
    public int userId;

    public String getGoodsName() {
        if (!TextUtils.isEmpty(goodsTitle)) {
            return goodsName + "    " + goodsTitle;
        }
        return goodsName;
    }
}
