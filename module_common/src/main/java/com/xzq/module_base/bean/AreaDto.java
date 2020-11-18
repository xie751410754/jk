package com.xzq.module_base.bean;

import android.text.TextUtils;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.ArrayList;
import java.util.List;

/**
 * AreaDto
 * Created by xzq on 2019/8/21.
 */
public class AreaDto implements IPickerViewData {

    /**
     * code : 151.152.155
     * name : 罗湖区
     * id : 155
     * childs : []
     */

    public String code;
    public String name;
    public int id;
    private List<AreaDto> childs;

    public int childCount() {
        return childs != null ? childs.size() : 0;
    }

    public AreaDto get(int index) {
        return childs.get(index);
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(name);
    }

    @Override
    public String getPickerViewText() {
        return name;
    }

    public List<AreaDto> getChilds() {
        if (childCount() > 0) {
            return childs;
        }
        AreaDto empty = new AreaDto();
        empty.name = "";
        List<AreaDto> list = new ArrayList<>();
        list.add(empty);
        return list;
    }

    public List<List<AreaDto>> getAreaList() {
        List<List<AreaDto>> areaList = new ArrayList<>();
        areaList.add(getChilds());
        return areaList;
    }
}
