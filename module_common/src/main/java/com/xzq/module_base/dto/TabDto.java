package com.xzq.module_base.dto;

import android.support.annotation.DrawableRes;

import com.xzq.module_base.utils.DateUtils;

import java.util.Objects;

/**
 * TabDto
 * Created by xzq on 2019/11/12.
 */
public class TabDto {
    public String name;
    public boolean isSelected;
    public int arrowId;
    public int id;

    public String sort;
    public String orderBy;

    public long timeMs;

    public boolean isCustomTime() {
        return id == -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabDto tabDto = (TabDto) o;
        return arrowId == tabDto.arrowId &&
                Objects.equals(name, tabDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, arrowId);
    }


}
