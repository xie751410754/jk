package com.xzq.module_base.eventbus;

/**
 * EventAction
 * Created by xzq on 2018/12/29.
 */
public interface EventAction {
    String MAIN_ACTIVITY_RESTART = "main_activity_restart";
    String UPDATE_ADDRESS_DONE = "update_address_done";
    String STICKY_CONFIRM_ORDER = "sticky_confirm_order";
    String SELECT_ORDER_ADDRESS = "select_order_address";
    String REMOVE_ORDER_ADDRESS = "remove_order_address";
    String UPDATE_BLANK_LIST = "update_blank_list";
    String CREATE_ORDER_DONE = "create_order_done";
    String STICKY_EVAL_ORDER = "sticky_eval_order";
    String STICKY_REFUND_ORDER = "sticky_refund_order";
    String UPDATE_ORDER_LIST = "update_order_list";
    String UPDATE_REFUND_LIST = "update_refund_list";
    String UPDATE_CART_LIST = "update_cart_list";
    String UPDATE_Matter_LIST = "update_matter_list";
    String SELECT_BLANK = "select_blank";
    String REMOVE_BLANK = "remove_blank";
    String STICKY_COLOR_GOT = "sticky_color_got";
    String STICKY_IMG_GOT = "sticky_img_got";
    String UPDATE_COLL_LIST = "update_coll_list";
    String WAITDEAL = "wait_deal";
    String NOTICE = "notice";
    String FINISHDEAL = "deal_finish";
    String OA = "OA系统";
    String HR = "人资系统";
    String FIN = "财务系统";
    String ADRESSBOOK = "通讯录";
    String MY = "我的";
    String APPLICATIONCENTER = "应用中心";
    String LOGIN_VPN = "login_vpn";
}
