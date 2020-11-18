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
import com.xzq.module_base.bean.PurchaseDto;
import com.xzq.module_base.bean.PushingDto;
import com.xzq.module_base.bean.QuantitativeGoodsDto;
import com.xzq.module_base.bean.ReportBaseDto;
import com.xzq.module_base.bean.ReportDto;
import com.xzq.module_base.bean.SingleFieldDto;
import com.xzq.module_base.bean.StockDto;
import com.xzq.module_base.bean.StoreDto;
import com.xzq.module_base.bean.TransactionDto;
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
    @POST("/qixing-app/app/dictionary/tree")
    Observable<NetBean<List<AreaDto>>> getArea(
            @Field("parentId") String parentId
    );

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

    @FormUrlEncoded
    @POST("/qixing-app/app/user/{path}")
    Observable<NetBean<Object>> editAddress(
            @Path("path") String path,
            @Field("token") String token,
            @Field("id") Integer id,
            @Field("userName") String userName,
            @Field("userPhone") String userPhone,
            @Field("province") String province,
            @Field("city") String city,
            @Field("district") String district,
            @Field("detailAddress") String detailAddress,
            @Field("addressState") int addressState
    );

    @GET("/qixing-app/app/user/findAllAddress")
    Observable<NetBean<List<AddressDto>>> findAllAddress(@Query("token") String token);

    @GET("/api-uaa/clients/list?category=1")
    Observable<NetBean<List<ApplicationCenterDto>>> findApplication();

    @GET("/api-user/mobile/get/address-book/{orgId}")
    Observable<NetBean<List<NoticeDto>>> getNoticeList(@Path("orgId") String orgId);

    @GET("/api-uaa/clients/appRoute/{clientId}")
    Observable<NetBean<String>> getApplicationUrl(@Path("clientId") String clientId);

    @GET("/qixing-app/app/user/updAddressState")
    Observable<NetBean<Object>> updAddressState(@Query("token") String token,
                                                @Query("id") int id);

    @GET("/qixing-app/app/user/delAddress")
    Observable<NetBean<Object>> delAddress(@Query("token") String token,
                                           @Query("id") int id);

    @GET("/api-user/users/current")
    Observable<NetBean<UserDto>> getUserInfo();
    @GET("/api-uaa/oauth/remove/token")
    Observable<NetBean<Object>> logOut();


    @GET("/api-uaa/validata/code/{id}")
    Observable<NetBean<Object>> getValidata(@Path("id") String id);

    @GET("/qixing-app/app/training/finaTrainingList")
    Observable<NetBean<List<PushingDto>>> findTrainingList(@Query("token") String token,
                                                           @Query("page") int page,
                                                           @Query("limit") int limit,
                                                           @Query("type") int type
    );

    @GET("/qixing-app/app/training/finaTrainingInfo")
    Observable<NetBean<PushingDto>> findTrainingInfo(@Query("token") String token,
                                                     @Query("id") int id);

    @FormUrlEncoded
    @POST("/qixing-app/app/netWork/finaMyAgent")
    Observable<NetBean<MyAgentDto>> finaMyAgent(
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/goodsCategory/getGoodsCategory")
    Observable<NetBean<List<ClassifyTabDto>>> getGoodsCategory(
            @Field("token") String token,
            @Field("parentId") String parentId
    );
    @POST("/api-uaa/clients/getMobileUnProcessAssign")
    Observable<NetBean<List<MatterDto>>> waitDeal(

    );
    @POST("/api-uaa/clients/getMobileUnProcessNotice")
    Observable<NetBean<List<MatterDto>>> noticeForm(

    );
    @POST("/api-uaa/clients/getUnProcessAssign")
    Observable<NetBean<List<MatterDto>>> finishDeal(

    );


    @FormUrlEncoded
    @POST("/qixing-app/app/goods/findGoodsDetails")
    Observable<NetBean<GoodsDetailDto>> findGoodsDetails(
            @Field("token") String token,
            @Field("id") int id
    );

    @GET("/qixing-app/app/goods/findPageGoodsEvaluate")
    Observable<NetBean<List<EvalDto>>> findPageGoodsEvaluate(@Query("id") int id,
                                                             @Query("page") int page,
                                                             @Query("limit") int limit
    );


    @GET("/qixing-app/app/shopCart/findAllPageShopCdartGoods")
    Observable<NetBean<List<CartDto>>> findAllPageShopCartGoods(@Query("token") String token,
                                                                @Query("page") int page,
                                                                @Query("limit") int limit
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/shopCart/updShopCartGoodsNum")
    Observable<NetBean<Object>> updShopCartGoodsNum(@Field("token") String token,
                                                    @Field("id") int id,
                                                    @Field("goodsId") int goodsId,
                                                    @Field("skuPriceId") int skuPriceId,
                                                    @Field("num") int num
    );


    @FormUrlEncoded
    @POST("/qixing-app/app/order/addGoodsDetailsOrder")
    Observable<NetBean<String>> addGoodsDetailsOrder(@Field("token") String token,
                                                     @Field("goodsId") int goodsId,
                                                     @Field("goodsNum") int goodsNum,
                                                     @Field("skuId") int skuId,
                                                     @Field("addressId") int addressId,
                                                     @Field("integral") Integer integral,
                                                     @Field("userLotteryTcketIId") Integer userLotteryTcketIId,
                                                     @Field("remark") String remark
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/shopCart/payShopCartGoods")
    Observable<NetBean<Object>> payShopCartGoods(@Field("token") String token,
                                                 @Field("orderNumber") String orderNumber,
                                                 @Field("payType") int payType
    );


    @FormUrlEncoded
    @POST("/qixing-app/app/order/updAbrogateUserOrder")
    Observable<NetBean<Object>> updAbrogateUserOrder(@Field("token") String token,
                                                     @Field("orderId") int orderId
    );

    @GET("/qixing-app/app/order/updUserOrderState")
    Observable<NetBean<Object>> updUserOrderState(@Query("token") String token,
                                                  @Query("orderId") int orderId
    );

    @GET("/qixing-app/app/order/delUserOrder")
    Observable<NetBean<Object>> delUserOrder(@Query("token") String token,
                                             @Query("orderId") int orderId
    );

    @GET("/qixing-app/app/delUserCollection")
    Observable<NetBean<Object>> delUserCollection(@Query("token") String token,
                                                  @Query("collId") int collId
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/addUserCollection")
    Observable<NetBean<Object>> addUserCollection(@Field("token") String token,
                                                  @Field("collIds") int collIds
    );

    @GET("/qixing-app/app/findPageCollection")
    Observable<NetBean<List<CollDto>>> findPageCollection(@Query("token") String token,
                                                          @Query("page") int page,
                                                          @Query("limit") int limit
    );

    @GET("/qixing-app/app/userNews/findAllPageUserNews")
    Observable<NetBean<List<MsgDto>>> findAllPageUserNews(@Query("token") String token,
                                                          @Query("page") int page,
                                                          @Query("limit") int limit,
                                                          @Query("newsTyp") int newsTyp
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/userNews/updUserNews")
    Observable<NetBean<Object>> updUserNews(@Field("token") String token,
                                            @Field("jsonArray") int jsonArray
    );

    @GET("/qixing-app/app/userNews/delUserNews")
    Observable<NetBean<Object>> delUserNews(@Query("token") String token,
                                            @Query("jsonArray") int jsonArray
    );

    @GET("/qixing-app/app/user/updateInfo")
    Observable<NetBean<Object>> updateInfo(@Query("token") String token,
                                           @Query("headImgUrl") String headImgUrl,
                                           @Query("nickname") String nickname,
                                           @Query("sex") int sex
    );

    @GET("/qixing-app/app/user/info")
    Observable<NetBean<UserInfoDto>> info(@Query("token") String token);

    @GET("/qixing-app/app/agent/basis")
    Observable<NetBean<BasisDto>> basis(@Query("token") String token);

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/finaStockList")
    Observable<NetBean<List<StoreDto>>> finaStockList(@Field("token") String token,
                                                      @Field("page") int page,
                                                      @Field("limit") int limit,
                                                      @Field("goodsName") String goodsName
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/finaStockInfo")
    Observable<NetBean<List<StoreDto>>> finaStockInfo(@Field("token") String token,
                                                      @Field("page") int page,
                                                      @Field("limit") int limit,
                                                      @Field("goodsId") int goodsId
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/bonus/detail")
    Observable<NetBean<List<BonusDto>>> bonusDetail(@Field("token") String token,
                                                    @Field("page") int page,
                                                    @Field("limit") int limit
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/bonus/bulletin")
    Observable<NetBean<BulletinDto>> bulletin(@Field("token") String token);


    @GET("/qixing-app/app/wallet/findAllBankCard")
    Observable<NetBean<List<BlankDto>>> findAllBankCard(@Query("token") String token);

    @FormUrlEncoded
    @POST("/qixing-app/app/wallet/addBankCard")
    Observable<NetBean<Object>> addBankCard(@Field("token") String token,
                                            @Field("bankName") String bankName,
                                            @Field("branchBankName") String branchBankName,
                                            @Field("cardHolder") String cardHolder,
                                            @Field("bankNumber") String bankNumber,
                                            @Field("bankCellphone") String bankCellphone,
                                            @Field("state") int state
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/wallet/updBankCard")
    Observable<NetBean<Object>> updBankCard(@Field("token") String token,
                                            @Field("bankName") String bankName,
                                            @Field("branchBankName") String branchBankName,
                                            @Field("cardHolder") String cardHolder,
                                            @Field("bankNumber") String bankNumber,
                                            @Field("bankCellphone") String bankCellphone,
                                            @Field("id") int id,
                                            @Field("state") int state
    );

    @GET("/qixing-app/app/wallet/delBankCard")
    Observable<NetBean<Object>> delBankCard(@Query("token") String token,
                                            @Query("id") int id
    );

    @GET("/qixing-app/app/wallet/defaultBankCard")
    Observable<NetBean<Object>> defaultBankCard(@Query("token") String token,
                                                @Query("id") int id,
                                                @Query("state") int state
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/network")
    Observable<NetBean<List<WebsiteDto>>> getWebsite(@Field("token") String token,
                                                     @Field("type") int type
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/approval")
    Observable<NetBean<Object>> approval(@Field("token") String token,
                                         @Field("id") int id
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/shopCart/addShopCartGoods")
    Observable<NetBean<Object>> addShopCartGoods(@Field("token") String token,
                                                 @Field("goodsId") int goodsId,
                                                 @Field("num") int num,
                                                 @Field("skuPriceId") int skuPriceId
    );

    @GET("/qixing-app/app/shopCart/delShopCartGoods")
    Observable<NetBean<Object>> delShopCartGoods(@Query("token") String token,
                                                 @Query("arrayIds") String arrayIds
    );


    @FormUrlEncoded
    @POST("/qixing-app/app/order/addOrder")
    Observable<NetBean<String>> addOrder(@Field("token") String token,
                                         @Field("addressId") int addressId,
                                         @Field("arrayIds") String arrayIds,
                                         @Field("integral") Integer integral,
                                         @Field("userLotteryTcketIId") Integer userLotteryTcketIId,
                                         @Field("remark") String remark
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/order/updRefundOrderMoney")
    Observable<NetBean<Object>> updRefundOrderMoney(@Field("token") String token,
                                                    @Field("id") int orderId,
                                                    @Field("returnBackReason") String returnBackReason,
                                                    @Field("returnBackPhoto") String returnBackPhoto,
                                                    @Field("returnType") int returnType,
                                                    @Field("orderItemId") int orderItemId,
                                                    @Field("description") String description
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/qixing-app/app/order/addOrderGoodsEvaluate")
    Observable<NetBean<Object>> addOrderGoodsEvaluate(@Body RequestBody body);

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/orderShip")
    Observable<NetBean<Object>> orderShip(@Field("token") String token,
                                          @Field("id") int id
    );

    @GET("/qixing-app/app/netWork/finaUserLotteryTicket")
    Observable<NetBean<List<CouponDto>>> finaUserLotteryTicket(@Query("token") String token,
                                                               @Query("applyType") int applyType);


    @FormUrlEncoded
    @POST("/qixing-app/app/order/orderReturnComplete")
    Observable<NetBean<Object>> orderReturnComplete(@Field("token") String token,
                                                    @Field("id") int id
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/orderReturnConfirm")
    Observable<NetBean<Object>> orderReturnConfirm(@Field("token") String token,
                                                   @Field("id") int id,
                                                   @Field("returnType") int returnType
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/home/index")
    Observable<NetBean<HomeDto>> getHomeAd(@Field("token") String token
    );


    @FormUrlEncoded
    @POST("/qixing-app/app/home/activityTimeList")
    Observable<NetBean<List<LimitedTimeDto>>> activityTimeList(@Field("token") String token
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/home/activityLimitedTimeList")
    Observable<NetBean<LimitedDto<LimitedGoodsDto>>> activityLimitedGoodsList(@Field("token") String token,
                                                                              @Field("page") int page,
                                                                              @Field("limit") int limit,
                                                                              @Field("activityTimeId") Integer activityTimeId
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/home/activityQuantitativeList")
    Observable<NetBean<List<QuantitativeGoodsDto>>> activityQuantitativeList(@Field("token") String token,
                                                                             @Field("page") int page,
                                                                             @Field("limit") int limit
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/order/updSingleGoodsUserOrder")
    Observable<NetBean<Object>> updSingleGoodsUserOrder(@Field("token") String token,
                                                        @Field("orderItemId") int orderItemId
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/returnTag/list")
    Observable<NetBean<List<CauseDto>>> returnList(@Field("token") String token);

    @GET("/qixing-app/app/wallet/bill/list")
    Observable<NetBean<List<TransactionDto>>> getBillList(@Query("token") String token,
                                                          @Query("page") int page,
                                                          @Query("limit") int limit,
                                                          @Query("state") Integer state,
                                                          @Query("type") Integer type
    );

    @GET("/qixing-app/app/netWork/finaMyIntegral")
    Observable<NetBean<SingleFieldDto>> finaMyIntegral(@Query("token") String token
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/goods/goodsSpecificationsList")
    Observable<NetBean<List<GoodsSkuDto>>> goodsSkuList(@Field("goodsId") int goodsId);

    @GET("/qixing-app/app/shopCart/findShopCdartTotalAndAmount")
    Observable<NetBean<CartNumDto>> findShopCdartTotalAndAmount(@Query("token") String token
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/wallet/applyUserWithdraw")
    Observable<NetBean<Object>> applyUserWithdraw(
            @Field("token") String token,
            @Field("money") String money,
            @Field("type") int type,
            @Field("bankCardId") int bankCardId
    );

    @GET("/qixing-app/app/agent/wallet/findPageUserBill")
    Observable<NetBean<List<BillDto>>> findPageUserBill(@Query("token") String token,
                                                        @Query("page") int page,
                                                        @Query("limit") int limit
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/backgroundColour/list")
    Observable<NetBean<BackgroundColourDto>> backgroundColour(@Field("token") String token);

    @FormUrlEncoded
    @POST("/qixing-app/app/backgroundImg/list")
    Observable<NetBean<BackgroundImgDto>> backgroundImg(@Field("token") String token);

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/finaStockList")
    Observable<NetBean<List<StockDto>>> getStockList(@Field("token") String token,
                                                     @Field("page") int page,
                                                     @Field("limit") int limit,
                                                     @Field("goodsName") String goodsName,
                                                     @Field("goodsType") String goodsType
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/incomeDetail")
    Observable<NetBean<List<BizDto>>> incomeDetailList(@Field("token") String token,
                                                       @Field("page") int page,
                                                       @Field("limit") int limit
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/findAgentSales")
    Observable<NetBean<ReportBaseDto<ReportDto>>> getReportList(@Field("token") String token,
                                                                @Field("page") int page,
                                                                @Field("limit") int limit,
                                                                @Field("startTime") String startTime,
                                                                @Field("endTime") String endTime
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/findAgentPurchase")
    Observable<NetBean<ReportBaseDto<PurchaseDto>>> findAgentPurchase(@Field("token") String token,
                                                                      @Field("page") int page,
                                                                      @Field("limit") int limit,
                                                                      @Field("startTime") String startTime,
                                                                      @Field("endTime") String endTime
    );

    @GET("/qixing-app/app/user/sendCode")
    Observable<NetBean<Object>> sendCode(@Query("phone") String token
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/user/verificationcode")
    Observable<NetBean<Object>> verificationCode(@Field("phone") String phone,
                                                 @Field("code") String code
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/user/forgetpwd")
    Observable<NetBean<Object>> forgetpwd(@Field("phone") String phone,
                                          @Field("password") String password,
                                          @Field("code") String code
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/updateOrder")
    Observable<NetBean<Object>> updateOrder(@Field("token") String token,
                                            @Field("id") int id,
                                            @Field("price") String price
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/order/orderReceipt")
    Observable<NetBean<Object>> orderReceipt(@Field("token") String token,
                                             @Field("orderId") int orderId
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/order/updOrderItem")
    Observable<NetBean<Object>> updOrderItem(@Field("token") String token,
                                             @Field("id") int id,
                                             @Field("goodsNum") int goodsNum,
                                             @Field("modifyPrice") String modifyPrice,
                                             @Field("actualPrice") String actualPrice
    );

    @FormUrlEncoded
    @POST("/qixing-app/app/agent/info")
    Observable<NetBean<WebsiteDto.UserListBean>> websiteInfo(@Field("id") int id
    );
}
