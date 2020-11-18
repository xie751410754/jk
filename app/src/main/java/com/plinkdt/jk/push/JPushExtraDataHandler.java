package com.plinkdt.jk.push;

import android.content.Context;

import com.xzq.module_base.arouter.RouterUtils;
import com.xzq.module_base.utils.EntitySerializerUtils;
import com.xzq.module_base.utils.XZQLog;
import com.plinkdt.jk.utils.AppUtils;


/**
 * 推送意图处理器
 * Created by xzq on 2018/9/13.
 */

class JPushExtraDataHandler {

    /**
     * 0  平台确认订单（代理商订单待付款）
     * 1  平台确认发货（代理商订单待收货）
     * 2   网点下单（代理商下线网点订单待确认）
     * 3   网点确认订单（代理商下线网点订单待发货）
     * 4  代理商确认发货（网点订单待收货）
     * 5  网点确认收货（代理商下线网点订单待收款）
     * 6   售后发货（售后列表）
     * <p>
     * tag  代理商 dls  网点  wd
     * 7   系统消息
     *
     * @param context .
     * @param json    .
     */
    static void doOpen(Context context, String json) {
        try {
            if (!AppUtils.isMainExist()) {
                RouterUtils.openMain();
            }
            PushParamDto param = EntitySerializerUtils.deserializerEntity(json, PushParamDto.class);
            XZQLog.debug("param = " + param);
        } catch (Exception e) {
            //nothing to do
        }
    }

}
