package com.xzq.module_base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * UserInfoDto
 * Created by xzq on 2020/3/23.
 */
public class UserInfoDto implements Parcelable {
    /**
     * openid :
     * rolename : KYS内服
     * sex : m
     * end_time : 2020-07-04 12:00:00
     * deptid : 39
     * qq_number :
     * clientsign : null
     * orgid : 31
     * start_time : 2019-06-01 12:00:00
     * password : $2a$10$W/DyupU9F3rMXyfoVIfNquZT4i.R7tH/1npt/AQanTJ.yuB7CS6oy
     * lasttime : 2020-03-23 14:02:48
     * weixin_number :
     * phone : 13800166661
     * first_time : 0
     * loginName : kys.nf
     * name : KYS内服
     * roletype : 5
     * id : 46
     * customer_name : 中泓在线-专题分析
     * email : 12266661@126.com
     * attempts : 0
     * username : 内服kys.nf
     * status : 1
     */

    public String openid;
    public String rolename;
    public String sex;
    public String end_time;
    public int deptid;
    public String qq_number;
    public int orgid;
    public String start_time;
    public String password;
    public String lasttime;
    public String weixin_number;
    public String phone;
    public int first_time;
    public String loginName;
    public String name;
    public String roletype;
    public int id;
    public String customer_name;
    public String email;
    public int attempts;
    public String username;
    public int status;

    protected UserInfoDto(Parcel in) {
        openid = in.readString();
        rolename = in.readString();
        sex = in.readString();
        end_time = in.readString();
        deptid = in.readInt();
        qq_number = in.readString();
        orgid = in.readInt();
        start_time = in.readString();
        password = in.readString();
        lasttime = in.readString();
        weixin_number = in.readString();
        phone = in.readString();
        first_time = in.readInt();
        loginName = in.readString();
        name = in.readString();
        roletype = in.readString();
        id = in.readInt();
        customer_name = in.readString();
        email = in.readString();
        attempts = in.readInt();
        username = in.readString();
        status = in.readInt();
    }

    public static final Creator<UserInfoDto> CREATOR = new Creator<UserInfoDto>() {
        @Override
        public UserInfoDto createFromParcel(Parcel in) {
            return new UserInfoDto(in);
        }

        @Override
        public UserInfoDto[] newArray(int size) {
            return new UserInfoDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(openid);
        dest.writeString(rolename);
        dest.writeString(sex);
        dest.writeString(end_time);
        dest.writeInt(deptid);
        dest.writeString(qq_number);
        dest.writeInt(orgid);
        dest.writeString(start_time);
        dest.writeString(password);
        dest.writeString(lasttime);
        dest.writeString(weixin_number);
        dest.writeString(phone);
        dest.writeInt(first_time);
        dest.writeString(loginName);
        dest.writeString(name);
        dest.writeString(roletype);
        dest.writeInt(id);
        dest.writeString(customer_name);
        dest.writeString(email);
        dest.writeInt(attempts);
        dest.writeString(username);
        dest.writeInt(status);
    }
}
