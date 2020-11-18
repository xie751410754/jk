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
        backgroundColour();
        backgroundImg();
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

    /**
     * 查询背景颜色
     */
    private static void backgroundColour() {
        RequestUtils.doAny(BackgroundColourDto.class, api -> api.backgroundColour(User.getToken()))
                .subscribe(new NetCallback<BackgroundColourDto>() {
                    @Override
                    protected void onSuccess(NetBean<BackgroundColourDto> response, BackgroundColourDto data, int page) {
                        ConfigSPManager.putLastColour(data.colorSystem);
                        EventUtil.post(EventAction.STICKY_COLOR_GOT);
                    }
                });
    }

    /**
     * 查询背景图片
     */
    private static void backgroundImg() {
        RequestUtils.doAny(BackgroundImgDto.class, api -> api.backgroundImg(User.getToken()))
                .subscribe(new NetCallback<BackgroundImgDto>() {
                    @Override
                    protected void onSuccess(NetBean<BackgroundImgDto> response, BackgroundImgDto data, int page) {
                        EventUtil.post(EventAction.STICKY_IMG_GOT, data.url);
                    }
                });
    }
}
