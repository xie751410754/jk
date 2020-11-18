package com.xzq.module_base.bean;

import java.util.List;

/**
 * BulletinDto
 * Created by xzq on 2019/9/6.
 */
public class BulletinDto {

    /**
     * bonus : {"bonus":200,"bonussum":200}
     * bulletinList : ["恭喜测试账号01注册用户达到100人，获得200元奖励！"]
     */

    private BonusBean bonus;
    private List<String> bulletinList;

    public List<String> getBulletinList() {
        return bulletinList;
    }

    public String getBonus() {
        return bonus != null ? bonus.bonussum : null;
    }

    public String getBonusSum() {
        return bonus != null ? bonus.bonus : null;
    }

    private static class BonusBean {
        /**
         * bonus : 200
         * bonussum : 200
         */

        private String bonus;
        private String bonussum;
    }
}
