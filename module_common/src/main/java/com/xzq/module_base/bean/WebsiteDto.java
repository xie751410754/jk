package com.xzq.module_base.bean;

import com.blankj.utilcode.util.ObjectUtils;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Locale;

/**
 * WebsiteDto
 * Created by xzq on 2019/9/6.
 */
public class WebsiteDto {
    public int standardAmount;//应发展网点数
    public int networkSum;//已发展网点数
    public String name;
    public int id;
    public List<UserListBean> userList;//网点列表
    public List<WebsiteDto> childs;

    public String city;
    public WebsiteDto.UserListBean website;

    public boolean isWebsite() {
        return website != null;
    }

    //审批状态0已审批1未审批
    public String getName(int type) {
        if (type == 1) {
            return name;
        }
        return String.format(Locale.getDefault(), "%1$s(%2$d)", name, networkSum);
    }

    public boolean hasChilds() {
        return childs != null && !childs.isEmpty();
    }

    public boolean hasUserList(int type) {
        return !ObjectUtils.isEmpty(userList);
    }

    public int userCount() {
        return ObjectUtils.isEmpty(userList) ? 0 : userList.size();
    }

    public static class UserListBean {
        /**
         * id : 134
         * nickname : 参数网点账号
         * headImgUrl : http://129.204.156.192:80/qixing/attachment/lackImg.png
         * phone : 18258949999
         * regions : null
         */

        public int id;
        @SerializedName(value = "snNickname", alternate = {"nickname","nickName"})
        public String snNickname;
        @SerializedName(value = "snHeadImgUrl", alternate = {"headImgUrl","headImgUr"})
        public String snHeadImgUrl;
        public String phone;
        public String storeName;
        public String storeAddress;
        public double price;//订货量金额
    }
}
