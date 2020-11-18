package com.xzq.module_base.dto;

import android.support.annotation.DrawableRes;

/**
 * TabDto
 * Created by xzq on 2019/11/12.
 */
public class TabDto {
    @DrawableRes
    public int resId;
    public int msgCount;
    public String name;

    public boolean hasMsg() {
        return msgCount > 0;
    }

    public String getMsgCount() {
        if (hasMsg()) {
            return msgCount > 99 ? "99+" : String.valueOf(msgCount);
        }
        return null;
    }

}
