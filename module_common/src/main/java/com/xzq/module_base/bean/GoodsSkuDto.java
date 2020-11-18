package com.xzq.module_base.bean;

import java.util.List;

/**
 * GoodsSkuDto
 * Created by xzq on 2019/9/3.
 */
public class GoodsSkuDto {

    /**
     * skuName : 颜色
     * sku_item : [{"item":"白色","id":6325,"url":""},{"item":"黑色","id":6326,"url":""}]
     */

    public String skuName;
    public List<SkuItemBean> sku_item;

    public static class SkuItemBean {
        /**
         * item : 白色
         * id : 6325
         * url :
         */

        public String item;
        public int id;
        public String url;

        public boolean isSeleected = false;
    }
}
