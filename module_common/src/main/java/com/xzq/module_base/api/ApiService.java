package com.xzq.module_base.api;

import com.xzq.module_base.bean.AddressDto;
import com.xzq.module_base.bean.ApplicationCenterDto;
import com.xzq.module_base.bean.AreaDto;
import com.xzq.module_base.bean.BackgroundColourDto;
import com.xzq.module_base.bean.BackgroundImgDto;
import com.xzq.module_base.bean.BasisDto;
import com.xzq.module_base.bean.BillDto;
import com.xzq.module_base.bean.BizDto;
import com.xzq.module_base.bean.BlankDto;
import com.xzq.module_base.bean.BonusDto;
import com.xzq.module_base.bean.BulletinDto;
import com.xzq.module_base.bean.CartDto;
import com.xzq.module_base.bean.CartNumDto;
import com.xzq.module_base.bean.CauseDto;
import com.xzq.module_base.bean.ClassifyTabDto;
import com.xzq.module_base.bean.CollDto;
import com.xzq.module_base.bean.CouponDto;
import com.xzq.module_base.bean.EvalDto;
import com.xzq.module_base.bean.GoodsDetailDto;
import com.xzq.module_base.bean.GoodsSkuDto;
import com.xzq.module_base.bean.HomeDto;
import com.xzq.module_base.bean.HomePageBean;
import com.xzq.module_base.bean.LimitedDto;
import com.xzq.module_base.bean.LimitedGoodsDto;
import com.xzq.module_base.bean.LimitedTimeDto;
import com.xzq.module_base.bean.LoginDto;
import com.xzq.module_base.bean.MatterDto;
import com.xzq.module_base.bean.MsgDto;
import com.xzq.module_base.bean.MyAgentDto;
import com.xzq.module_base.bean.NoticeDto;
import com.xzq.module_base.bean.NoticeListDto;
import com.xzq.module_base.bean.PurchaseDto;
import com.xzq.module_base.bean.PushingDto;
import com.xzq.module_base.bean.QuantitativeGoodsDto;
import com.xzq.module_base.bean.ReportBaseDto;
import com.xzq.module_base.bean.ReportDto;
import com.xzq.module_base.bean.SingleFieldDto;
import com.xzq.module_base.bean.StockDto;
import com.xzq.module_base.bean.StoreDto;
import com.xzq.module_base.bean.TransactionDto;
import com.xzq.module_base.bean.UpdateAppEntity;
import com.xzq.module_base.bean.UserDto;
import com.xzq.module_base.bean.UserInfoDto;
import com.xzq.module_base.bean.WebsiteDto;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * ApiService
 * Created by Wesley on 2018/7/10.
 */
public interface ApiService {

    /**
     * method：网络请求的方法（区分大小写）
     * path：网络请求地址路径
     * hasBody：是否有请求体
     */
    @HTTP(method = "GET", path = "/article/list/{page}/json", hasBody = false)
    Observable<NetBean<BaseListBean<HomePageBean>>> getWangAndroidHomePage(
            @Path("page") int page);

    @Multipart
    @POST("api-user/mobile/set/avatar-url")
    Observable<NetBean<String>> uploadImg(@Part MultipartBody.Part file);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/api-uaa/clients/getMobileAssignURL")
    Observable<NetBean<String>> getMobileAssignURL(@Body RequestBody body);



    @FormUrlEncoded
    @POST("/qixing-app/app/user/registered")
    Observable<NetBean<Object>> register(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("nickname") String nickname,
            @Field("regions") String regions,
            @Field("lnvitationCode") String lnvitationCode,
            @Field("storeName") String storeName,
            @Field("storeAddress") String storeAddress
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/user/login")
    Observable<NetBean<LoginDto>> login(
            @Field("userName") String userName,
            @Field("password") String password
    );



    @GET("/api-uaa/clients/list?category=1")
    Observable<NetBean<List<ApplicationCenterDto>>> findApplication();

    @GET("/api-user/mobile/get/address-book/{orgId}")
    Observable<NetBean<BaseListBean<NoticeListDto.DetailsBean>>> getNoticeList(@Path("orgId") String orgId);

    @GET("/api-user/mobile/get-name/address-book")
    Observable<NetBean<BaseListBean<NoticeListDto.DetailsBean>>> getNoticeUserList(@Query("username") String username);

    @GET("/api-user/apply-upgrade/app-update/{version}")
    Observable<NetBean<UpdateAppEntity>> appUpdate(@Path("version") String version);

    @GET("/api-uaa/clients/appRoute/{clientId}")
    Observable<NetBean<String>> getApplicationUrl(@Path("clientId") String clientId);


    @GET("/api-user/users/current")
    Observable<NetBean<UserDto>> getUserInfo();
    @GET("/api-uaa/oauth/remove/token")
    Observable<NetBean<Object>> logOut();


    @GET("/api-uaa/validata/code/{id}")
    Observable<NetBean<Object>> getValidata(@Path("id") String id);




    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/api-uaa/clients/getMobileUnProcessAssign")
    Observable<NetBean<List<MatterDto>>> waitDeal(@Body RequestBody body
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/api-uaa/clients/getMobileUnProcessNotice")
    Observable<NetBean<List<MatterDto>>> noticeForm(@Body RequestBody body

    );

    @FormUrlEncoded
    @POST("/api-uaa/clients/getMobileUnProcessNotice")
    Observable<NetBean<List<MatterDto>>> searchNoticeForm(
            @Field("keyword") String keyword,
            @Field("pageSize") int pageSize,
            @Field("type") int type
    );
    @POST("/api-uaa/clients/getUnProcessAssign")
    Observable<NetBean<List<MatterDto>>> finishDeal(

    );
    @FormUrlEncoded
    @POST("/api-uaa/clients/getUnProcessAssign")
    Observable<NetBean<List<MatterDto>>> searchFinishDeal(
            @Field("keyword") String keyword,
            @Field("pageSize") int pageSize,
            @Field("type") int type
    );




}
