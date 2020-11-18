package com.xzq.module_base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * BasisDto
 * Created by xzq on 2019/9/5.
 */
public class BasisDto implements Parcelable {
    public String income;
    public String balance;
    public String bonus;
    public String grade;
    public String client;

    protected BasisDto(Parcel in) {
        income = in.readString();
        balance = in.readString();
        bonus = in.readString();
        grade = in.readString();
        client = in.readString();
    }

    public static final Creator<BasisDto> CREATOR = new Creator<BasisDto>() {
        @Override
        public BasisDto createFromParcel(Parcel in) {
            return new BasisDto(in);
        }

        @Override
        public BasisDto[] newArray(int size) {
            return new BasisDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(income);
        dest.writeString(balance);
        dest.writeString(bonus);
        dest.writeString(grade);
        dest.writeString(client);
    }
}
