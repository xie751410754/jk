package com.xzq.module_base.bean;

/**
 * CartNumDto
 * Created by xzq on 2019/9/29.
 */
public class CartNumDto {

    /**
     * code : 0
     * msg : 成功
     * data : {"totalprice":13273.7,"num":10}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * totalprice : 13273.7
         * num : 10
         */

        public double totalprice;
        public int num;
    }

    public double totalprice() {
        return data != null ? data.totalprice : 0;
    }

    public int num() {
        return data != null ? data.num : 0;
    }
}
