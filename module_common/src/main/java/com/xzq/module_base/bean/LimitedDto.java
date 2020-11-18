package com.xzq.module_base.bean;

import com.xzq.module_base.api.BaseListBean;

/**
 * LimitedDto
 * Created by xzq on 2019/9/18.
 */
public class LimitedDto<T> extends BaseListBean<T> {
    public String activityName;
    public long activityTimeId;
}
