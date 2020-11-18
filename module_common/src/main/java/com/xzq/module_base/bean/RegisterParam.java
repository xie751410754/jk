package com.xzq.module_base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * RegisterParam
 * Created by xzq on 2019/8/29.
 */
public class RegisterParam implements Parcelable {

    public boolean isAgent;
    public String phone;
    public String code;
    public String password;
    public String nickname;
    public String regions;
    public String lnvitationCode;
    public String realName;
    public String cardTime;
    public String card;
    public String cardPositivePhoto;
    public String cardNegativePhoto;
    public String storeName;
    public String storeAddress;

    public RegisterParam() {
    }

    protected RegisterParam(Parcel in) {
        isAgent = in.readByte() != 0;
        phone = in.readString();
        code = in.readString();
        password = in.readString();
        nickname = in.readString();
        regions = in.readString();
        lnvitationCode = in.readString();
        realName = in.readString();
        cardTime = in.readString();
        card = in.readString();
        cardPositivePhoto = in.readString();
        cardNegativePhoto = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isAgent ? 1 : 0));
        dest.writeString(phone);
        dest.writeString(code);
        dest.writeString(password);
        dest.writeString(nickname);
        dest.writeString(regions);
        dest.writeString(lnvitationCode);
        dest.writeString(realName);
        dest.writeString(cardTime);
        dest.writeString(card);
        dest.writeString(cardPositivePhoto);
        dest.writeString(cardNegativePhoto);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RegisterParam> CREATOR = new Creator<RegisterParam>() {
        @Override
        public RegisterParam createFromParcel(Parcel in) {
            return new RegisterParam(in);
        }

        @Override
        public RegisterParam[] newArray(int size) {
            return new RegisterParam[size];
        }
    };

    @Override
    public String toString() {
        return "RegisterParam{" +
                "isAgent=" + isAgent +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", regions='" + regions + '\'' +
                ", lnvitationCode='" + lnvitationCode + '\'' +
                ", realName='" + realName + '\'' +
                ", cardTime='" + cardTime + '\'' +
                ", card='" + card + '\'' +
                ", cardPositivePhoto='" + cardPositivePhoto + '\'' +
                ", cardNegativePhoto='" + cardNegativePhoto + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                '}';
    }
}
