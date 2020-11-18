package com.xzq.module_base.bean;

import com.google.gson.annotations.SerializedName;

/**
 * CauseDto
 * Created by xzq on 2019/9/23.
 */
public class CauseDto {
    @SerializedName(value = "snName", alternate = {"content"})
    public String snName;
    public boolean checked;

    public String getName() {
        return snName;
    }
}
