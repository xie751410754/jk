package com.xzq.module_base.bean;

/**
 * LoginDto
 * Created by xzq on 2019/8/13.
 */
public class LoginDto {

    /**
     * id : 137
     * username : 13145790175
     * phone : 13145790175
     * token : null
     * headImgUrl : http://129.204.156.192:80/qixing/attachment/lackImg.png
     * nickname : 昵称哈哈哈
     * type : 2
     * regions : 151,152,153
     * regionsName : null
     */

    public String id;
    public String username;
    public String phone;
    public String token;
    public String headImgUrl;
    public String nickname;
    public int type;//用户类型（1代理商2网点）
    public String regions;
    public String regionsName;

    public boolean isAgent() {
        return type == 1;
    }

    public boolean isVisitor() {
        return type == -1;
    }

    public String getTags() {
        return isAgent() ? "dls" : "wd";
    }

}
