package com.xzq.module_base.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.xzq.module_base.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AddressDto
 * Created by xzq on 2019/8/27.
 */
public class AddressDto implements Parcelable {
    public int id;
    public String userName;
    public String userPhone;
    public String province;
    public String city;
    public String district;
    public String userAddress;
    public String detailAddress;
    public int addressState;//是否默认0.默认地址，1非默认地址'

    public AddressDto() {
    }

    public boolean isAdd() {
        return id == 0;
    }

    public boolean isDef() {
        return addressState == 0;
    }

    public String getFullArea() {
        return StringUtils.toEmptyIfNull(province) + StringUtils.toEmptyIfNull(city) + StringUtils.toEmptyIfNull(district);
    }

    public static List<AddressDto> getTest() {
        List<AddressDto> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AddressDto dto = new AddressDto();
            dto.id = i + 1;
            dto.userName = "姓名 " + i;
            dto.userPhone = "13146790175";
            dto.province = "广东省";
            dto.city = "深圳市";
            dto.district = "福田区";
            dto.userAddress = "广东省深圳市福田区";
            dto.detailAddress = "泰然四路劲松大厦12D";
            dto.addressState = i;
            list.add(dto);
        }
        return list;
    }


    protected AddressDto(Parcel in) {
        id = in.readInt();
        userName = in.readString();
        userPhone = in.readString();
        province = in.readString();
        city = in.readString();
        district = in.readString();
        userAddress = in.readString();
        detailAddress = in.readString();
        addressState = in.readInt();
    }

    public static final Creator<AddressDto> CREATOR = new Creator<AddressDto>() {
        @Override
        public AddressDto createFromParcel(Parcel in) {
            return new AddressDto(in);
        }

        @Override
        public AddressDto[] newArray(int size) {
            return new AddressDto[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto dto = (AddressDto) o;
        return id == dto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(userName);
        dest.writeString(userPhone);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(district);
        dest.writeString(userAddress);
        dest.writeString(detailAddress);
        dest.writeInt(addressState);
    }
}
