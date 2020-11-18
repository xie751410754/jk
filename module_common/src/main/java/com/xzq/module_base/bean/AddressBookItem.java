package com.xzq.module_base.bean;

import java.util.Objects;

/**
 * AddressBookItem
 * Created by Tse on 2020/11/9.
 */
public class AddressBookItem {
    private int type;//1为 部门 2为员工
    private String name;//当type=1时name为部门，等于2时name为员工姓名
    private String phone;//员工电话
    private boolean isShowNormal = false;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isShowNormal() {
        return isShowNormal;
    }

    public void setShowNormal(boolean showNormal) {
        isShowNormal = showNormal;
    }

    /**
     * 当type=1时为标题（部门）
     *
     * @return 是否为标题
     */
    public boolean isTitle() {
        return type == 1;
    }

    public boolean isNormal() {
        return type == 2;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBookItem that = (AddressBookItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
