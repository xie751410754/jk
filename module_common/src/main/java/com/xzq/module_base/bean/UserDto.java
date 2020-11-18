package com.xzq.module_base.bean;

import java.util.List;

public class UserDto {


    /**
     * id : 1
     * username : admin
     * orgId : 0
     * password : null
     * nickname : admin1
     * headImgUrl : http://192.168.1.108:10000/api-file/downloadFile-anon?path=/Users/zhicheng.zhang/Desktop/jk-central-platform-ua/data/file/2020-11-05/e296288e68544038976701efb8edaa10.jpg
     * mobile : 18888888888
     * officePhone : null
     * otherPhone : null
     * serialNumber : null
     * post : null
     * postResponsibility : null
     * status : null
     * job : null
     * jobName : null
     * jobLevel : null
     * theOffice : null
     * officeAddress : null
     * speciality : null
     * hobby : null
     * sex : 0
     * enabled : true
     * openId : 123
     * createTime : 1510909019000
     * updateTime : 1587894428000
     * orgName : null
     * roles : [{"id":"1","createTime":1510909019000,"updateTime":1537321150000,"code":"ADMIN","name":"管理员","userId":null}]
     * roleId : null
     * oldPassword : null
     * newPassword : null
     * permissions : null
     * userId : 123
     * accountNonExpired : true
     * accountNonLocked : true
     * credentialsNonExpired : true
     * del : false
     */

    private String id;
    private String username;
    private String orgId;
    private Object password;
    private String nickname;
    private String headImgUrl;
    private String mobile;
    private Object officePhone;
    private Object otherPhone;
    private Object serialNumber;
    private String post;
    private Object postResponsibility;
    private Object status;
    private Object job;
    private String jobName;
    private Object jobLevel;
    private Object theOffice;
    private Object officeAddress;
    private Object speciality;
    private Object hobby;
    private int sex;
    private boolean enabled;
    private String openId;
    private long createTime;
    private long updateTime;
    private Object orgName;
    private Object roleId;
    private Object oldPassword;
    private Object newPassword;
    private Object permissions;
    private String userId;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean del;
    private List<RolesDto> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getMobile() {
        return mobile==null?"号码正在录入...":mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(Object officePhone) {
        this.officePhone = officePhone;
    }

    public Object getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(Object otherPhone) {
        this.otherPhone = otherPhone;
    }

    public Object getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Object serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Object getPostResponsibility() {
        return postResponsibility;
    }

    public void setPostResponsibility(Object postResponsibility) {
        this.postResponsibility = postResponsibility;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getJob() {
        return job;
    }

    public void setJob(Object job) {
        this.job = job;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Object getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(Object jobLevel) {
        this.jobLevel = jobLevel;
    }

    public Object getTheOffice() {
        return theOffice;
    }

    public void setTheOffice(Object theOffice) {
        this.theOffice = theOffice;
    }

    public Object getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Object officeAddress) {
        this.officeAddress = officeAddress;
    }

    public Object getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Object speciality) {
        this.speciality = speciality;
    }

    public Object getHobby() {
        return hobby;
    }

    public void setHobby(Object hobby) {
        this.hobby = hobby;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public Object getOrgName() {
        return orgName;
    }

    public void setOrgName(Object orgName) {
        this.orgName = orgName;
    }

    public Object getRoleId() {
        return roleId;
    }

    public void setRoleId(Object roleId) {
        this.roleId = roleId;
    }

    public Object getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(Object oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Object getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(Object newPassword) {
        this.newPassword = newPassword;
    }

    public Object getPermissions() {
        return permissions;
    }

    public void setPermissions(Object permissions) {
        this.permissions = permissions;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public List<RolesDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesDto> roles) {
        this.roles = roles;
    }

    public static class RolesDto {
        /**
         * id : 1
         * createTime : 1510909019000
         * updateTime : 1537321150000
         * code : ADMIN
         * name : 管理员
         * userId : null
         */

        private String id;
        private long createTime;
        private long updateTime;
        private String code;
        private String name;
        private Object userId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }
    }
}
