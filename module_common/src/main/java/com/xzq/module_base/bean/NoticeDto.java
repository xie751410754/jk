package com.xzq.module_base.bean;

import com.blankj.utilcode.util.ObjectUtils;

import java.util.List;

public class NoticeDto {
    /**
     * dept : 财务部
     * staff : [{"name":"王琦","mobile":"123456"}]
     */

    private boolean isExpand = false;//是否展开，此字段可以由服务器展开或者不展开
    private String dept;
    private List<StaffDto> staff;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public List<StaffDto> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffDto> staff) {
        this.staff = staff;
    }

    public boolean hasStaffList() {
        return !ObjectUtils.isEmpty(staff);
    }
//
//    /**
//     * id : 363
//     * msg : 欢迎，手机尾号7648的新用户加入博悦会
//     * gmtCreate : 2020-03-19 15:20:42
//     * gmtModified : 2020-03-19 15:20:42
//     * userId : 2209
//     * isFollow : null
//     */
//
//    private int id;
//    private String msg;
//    private String gmtCreate;
//    private String gmtModified;
//    private int userId;
//    private Object isFollow;
//
//    public boolean isFollowing() {
//        return following;
//    }
//
//    public void setFollowing(boolean following) {
//        this.following = following;
//    }
//
//    private boolean following;
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public String getGmtCreate() {
//        return gmtCreate;
//    }
//
//    public void setGmtCreate(String gmtCreate) {
//        this.gmtCreate = gmtCreate;
//    }
//
//    public String getGmtModified() {
//        return gmtModified;
//    }
//
//    public void setGmtModified(String gmtModified) {
//        this.gmtModified = gmtModified;
//    }
//
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public Object getIsFollow() {
//        return isFollow;
//    }
//
//    public void setIsFollow(Object isFollow) {
//        this.isFollow = isFollow;
//    }


}
