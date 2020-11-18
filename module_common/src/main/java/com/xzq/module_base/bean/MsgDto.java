package com.xzq.module_base.bean;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * MsgDto
 * Created by xzq on 2019/8/27.
 */
public class MsgDto {

    public int id;
    public String newsTitle;
    public String newsContent;
    public String newsTyp;//消息类型1：订单通知2：系统消息3：公司推送消息
    public String newsState;//消息状态0未读1已读
    public String createDate;

    public boolean isRead() {
        return TextUtils.equals(newsState, "0");
    }

    public void readMsg() {
        newsState = "0";
    }

    public static List<MsgDto> getTest() {
        List<MsgDto> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            MsgDto dto = new MsgDto();
            dto.id = i;
            dto.newsTitle = "优惠券即将过期 " + i;
            dto.newsContent = "你有123元优惠券将于08.10到期，请及时使用。你有123元优惠券将于08.10到期，请及时使用。";
            dto.newsState = String.valueOf(i);
            dto.createDate = "2017/11/07";
            list.add(dto);
        }
        return list;
    }

}
