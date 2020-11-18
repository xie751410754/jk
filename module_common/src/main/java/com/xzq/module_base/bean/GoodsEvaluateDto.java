package com.xzq.module_base.bean;

import java.util.List;

/**
 * GoodsEvaluateDto
 * Created by xzq on 2019/9/5.
 */
public class GoodsEvaluateDto {
    public int goodsEvaluateSum;
    public List<EvalDto> goodsEvaluateList;

    public boolean hasEval() {
        return goodsEvaluateList != null && !goodsEvaluateList.isEmpty();
    }

}
