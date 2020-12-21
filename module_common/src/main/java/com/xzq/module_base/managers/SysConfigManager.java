package com.xzq.module_base.managers;

import android.graphics.Color;
import android.support.annotation.ColorInt;

import com.xzq.module_base.User;
import com.xzq.module_base.api.NetBean;
import com.xzq.module_base.api.NetCallback;
import com.xzq.module_base.api.RequestUtils;
import com.xzq.module_base.bean.BackgroundColourDto;
import com.xzq.module_base.bean.BackgroundImgDto;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.EventUtil;
import com.xzq.module_base.sp.ConfigSPManager;

/**
 * SysConfigManager
 * Created by xzq on 2019/10/9.
 */
public class SysConfigManager {

    public static void init() {

    }

    @ColorInt
    public static int getBackgroundColour() {
        String lastColour = ConfigSPManager.getLastColour();
        try {
            return Color.parseColor(lastColour);
        } catch (Exception e) {
            return Color.WHITE;
        }
    }




}
