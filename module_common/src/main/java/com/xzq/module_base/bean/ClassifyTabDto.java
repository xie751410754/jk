package com.xzq.module_base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ClassifyTabDto
 * Created by xzq on 2019/8/16.
 */
public class ClassifyTabDto implements Parcelable {
    public int categoryId;
    public int parentId;
    public String categoryName;
    public String categoryImage;
    public boolean isSelected;

    public ClassifyTabDto() {
    }

    protected ClassifyTabDto(Parcel in) {
        categoryId = in.readInt();
        parentId = in.readInt();
        categoryName = in.readString();
        categoryImage = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<ClassifyTabDto> CREATOR = new Creator<ClassifyTabDto>() {
        @Override
        public ClassifyTabDto createFromParcel(Parcel in) {
            return new ClassifyTabDto(in);
        }

        @Override
        public ClassifyTabDto[] newArray(int size) {
            return new ClassifyTabDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(categoryId);
        dest.writeInt(parentId);
        dest.writeString(categoryName);
        dest.writeString(categoryImage);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
