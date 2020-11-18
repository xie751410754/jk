package com.xzq.module_base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.blankj.utilcode.util.AdaptScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * GoodsDetailDto
 * Created by xzq on 2019/9/2.
 */
public class GoodsDetailDto implements Parcelable {
    public int id;
    public String goodsName;
    public String goodsRemark;
    public String goodsContent;
    public double shopPrice;
    public int giveIntegral;
    public int cartCount;
    public String collectState;
    public String imgUrl;
    public List<GoodsAttrDto> goodsProPerts;
    public List<String> goodsImgUrls;
    public List<GoodsSkuDto> goodsSpecifications;
    public GoodsEvaluateDto goodsEvaluate;

    protected GoodsDetailDto(Parcel in) {
        id = in.readInt();
        goodsName = in.readString();
        goodsRemark = in.readString();
        goodsContent = in.readString();
        shopPrice = in.readDouble();
        giveIntegral = in.readInt();
        cartCount = in.readInt();
        collectState = in.readString();
        imgUrl = in.readString();
        goodsImgUrls = in.createStringArrayList();
        goodsNum = in.readInt();
        skuId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(goodsName);
        dest.writeString(goodsRemark);
        dest.writeString(goodsContent);
        dest.writeDouble(shopPrice);
        dest.writeInt(giveIntegral);
        dest.writeInt(cartCount);
        dest.writeString(collectState);
        dest.writeString(imgUrl);
        dest.writeStringList(goodsImgUrls);
        dest.writeInt(goodsNum);
        dest.writeInt(skuId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GoodsDetailDto> CREATOR = new Creator<GoodsDetailDto>() {
        @Override
        public GoodsDetailDto createFromParcel(Parcel in) {
            return new GoodsDetailDto(in);
        }

        @Override
        public GoodsDetailDto[] newArray(int size) {
            return new GoodsDetailDto[size];
        }
    };

    public boolean showCart() {
        return cartCount > 0;
    }

    public String getCartCount(boolean increase) {
        if (increase) {
            cartCount++;
        }
        if (cartCount > 99) {
            return "99+";
        }
        return String.valueOf(cartCount);
    }

    public int getMarginRight() {
        if (cartCount < 10) {
            return AdaptScreenUtils.pt2Px(10);
        } else if (cartCount < 100) {
            return AdaptScreenUtils.pt2Px(5);
        } else {
            return 0;
        }
    }

    public boolean hasEval() {
        return goodsEvaluate != null && goodsEvaluate.hasEval();
    }

    public String getGoodsEvaluateSum() {
        int evalNum = goodsEvaluate != null ? goodsEvaluate.goodsEvaluateSum : 0;
        return String.format(Locale.getDefault(), "用户评价（%1$d）", evalNum);
    }

    public int goodsNum;
    public int skuId = 0;

    public List<String> getTopBannerList() {
        List<String> list = new ArrayList<>();
        list.add(imgUrl);
        if (goodsImgUrls != null) {
            list.addAll(goodsImgUrls);
        }
        return list;
    }

    public String getIntegralStr() {
        return String.format(Locale.getDefault(), "购买可得%1$d积分", giveIntegral);
    }

    public boolean hasProp() {
        return goodsProPerts != null && !goodsProPerts.isEmpty();
    }

    public String getProp() {
        if (hasProp()) {
            GoodsAttrDto first = goodsProPerts.get(0);
            return first.attrName + "  " + first.attrVal;
        }
        return null;
    }

    public boolean isColl() {
        return TextUtils.equals("0", collectState);
    }

    public void negationColl() {
        collectState = isColl() ? "1" : "0";
    }
}
