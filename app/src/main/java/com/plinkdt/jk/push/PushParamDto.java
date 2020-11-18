package com.plinkdt.jk.push;

import com.google.gson.annotations.SerializedName;

/**
 * PushParamDto
 * Created by xzq on 2019/6/24.
 */
public class PushParamDto {
    @SerializedName(value = "snType", alternate = {"type"})
    public String snType;
    @SerializedName(value = "snVal", alternate = {"value"})
    public String snVal;

    @Override
    public String toString() {
        return "PushParamDto{" +
                "snType='" + snType + '\'' +
                ", snVal='" + snVal + '\'' +
                '}';
    }
}
