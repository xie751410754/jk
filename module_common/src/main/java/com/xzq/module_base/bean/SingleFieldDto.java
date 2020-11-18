package com.xzq.module_base.bean;

import com.google.gson.annotations.SerializedName;

/**
 * SingleFieldDto
 * Created by xzq on 2019/9/25.
 */
public class SingleFieldDto {
    @SerializedName(value = "snValue", alternate = {"integral"})
    private String snValue;

    public String getValue() {
        return snValue;
    }
}
