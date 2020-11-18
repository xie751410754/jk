package com.xzq.module_base.bean;

/**
 * LimitedTimeDto
 * Created by xzq on 2019/9/18.
 */
public class LimitedTimeDto {

    /**
     * id : 1
     * activityName : 国庆满减活动
     * startTime : 2019-09-18 18:00:00
     * endTime : 2019-09-18 20:00:00
     * activityType : 2
     */

    public int id;
    public String activityName;
    public String startTime;
    public String endTime;
    public int activityType;//活动状态1未开2进行中3已结束
}
