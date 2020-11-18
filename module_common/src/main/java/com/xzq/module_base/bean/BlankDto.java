package com.xzq.module_base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * BlankDto
 * Created by xzq on 2019/9/6.
 */
public class BlankDto implements Parcelable {

    /**
     * id : 1
     * bankName : 招商银行
     * bankNumber : 622
     * cardHolder : 谢振琪
     * branchBankName : 招商银行支行
     * bankCellphone : 13145790175
     */

    public int id;
    public String bankName;
    public String bankNumber;
    public String cardHolder;
    public String branchBankName;
    public String bankCellphone;
    public int state;//0默认银行卡  1非默认

    public BlankDto() {
    }

    public boolean isAdd() {
        return id == 0;
    }

    public boolean isDef() {
        return state == 0;
    }

    public String getBankNumber() {
        String lastNum;
        if (bankNumber != null && bankNumber.length() > 4) {
            lastNum = bankNumber.substring(bankNumber.length() - 4);
        } else {
            lastNum = bankNumber;
        }
        return "尾号" + lastNum + "储蓄卡";
    }


    protected BlankDto(Parcel in) {
        id = in.readInt();
        bankName = in.readString();
        bankNumber = in.readString();
        cardHolder = in.readString();
        branchBankName = in.readString();
        bankCellphone = in.readString();
        state = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(bankName);
        dest.writeString(bankNumber);
        dest.writeString(cardHolder);
        dest.writeString(branchBankName);
        dest.writeString(bankCellphone);
        dest.writeInt(state);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BlankDto> CREATOR = new Creator<BlankDto>() {
        @Override
        public BlankDto createFromParcel(Parcel in) {
            return new BlankDto(in);
        }

        @Override
        public BlankDto[] newArray(int size) {
            return new BlankDto[size];
        }
    };
}
