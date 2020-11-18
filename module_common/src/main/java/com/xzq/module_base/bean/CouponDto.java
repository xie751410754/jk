package com.xzq.module_base.bean;

import com.xzq.module_base.utils.StringUtils;

/**
 * CouponDto
 * Created by xzq on 2019/8/28.
 */
public class CouponDto {
    public int id;
    public String money;
    public String name;
    public String cause;
    public String gmtCreate;
    public int applyType;//使用状态0未使用1已使用

    public boolean isSelected;

    public boolean isUsed() {
        return applyType == 1;
    }

    public int getMoney() {
        return StringUtils.toInt(money);
    }
}
