package com.xzq.module_base.mvp;

import android.text.TextUtils;
import android.widget.TextView;

import com.xzq.module_base.User;
import com.xzq.module_base.api.NetBean;
import com.xzq.module_base.api.RequestUtils;
import com.xzq.module_base.arouter.RouterUtils;
import com.xzq.module_base.bean.AddressDto;
import com.xzq.module_base.bean.AreaDto;
import com.xzq.module_base.bean.BasisDto;
import com.xzq.module_base.bean.BlankDto;
import com.xzq.module_base.bean.BulletinDto;
import com.xzq.module_base.bean.CartDto;
import com.xzq.module_base.bean.CartNumDto;
import com.xzq.module_base.bean.CauseDto;
import com.xzq.module_base.bean.ClassifyTabDto;
import com.xzq.module_base.bean.CollDto;
import com.xzq.module_base.bean.GoodsDetailDto;
import com.xzq.module_base.bean.GoodsSkuDto;
import com.xzq.module_base.bean.HomeDto;
import com.xzq.module_base.bean.LimitedDto;
import com.xzq.module_base.bean.LimitedGoodsDto;
import com.xzq.module_base.bean.LimitedTimeDto;
import com.xzq.module_base.bean.LoginDto;
import com.xzq.module_base.bean.MsgDto;
import com.xzq.module_base.bean.MyAgentDto;
import com.xzq.module_base.bean.PushingDto;
import com.xzq.module_base.bean.QuantitativeGoodsDto;
import com.xzq.module_base.bean.RegisterParam;
import com.xzq.module_base.bean.SingleFieldDto;
import com.xzq.module_base.bean.UpdateAppEntity;
import com.xzq.module_base.bean.UserDto;
import com.xzq.module_base.bean.UserInfoDto;
import com.xzq.module_base.bean.WebsiteDto;
import com.xzq.module_base.managers.LubanManager;
import com.xzq.module_base.sp.ConfigSPManager;
import com.xzq.module_base.utils.JsonUtils;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.XZQLog;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * MvpContract
 * Created by xzq on 2019/7/2.
 */
public interface MvpContract {

    interface CommonView extends IStateView, IPostLoadingView {
    }

    interface UserInfoView {
        void onGetUserInfoFinish(UserDto user);
    }
    interface ApplicationView {
        void onGetApplicationView(String user);
    }
    interface UpdateAppView {
        void onGetUpdateAppSucceed(UpdateAppEntity updateAppEntity);
    }

    interface DoneView {
        /**
         * 请求成功
         *
         * @param obj .
         */
        void onDone(Object... obj);
    }

    interface ValidataView {
        /**
         * 请求成功
         *
         * @param obj .
         */
        void onValidataSucceed(Object... obj);
    }

    interface LoginView {
        /**
         * 登录成功
         *
         * @param data .
         */
        void loginSucceed(LoginDto data);
    }

    interface UploadImgView {
        /**
         * 图片上传成功
         *
         * @param remotePath 远程图片相对地址
         * @param code       .
         */
        void onUploadImgSucceed(String remotePath, int... code);
    }
    interface MobileAssignURLView {
        void onMobileAssignURLSucceed(String data);
    }

    interface AreaView {
        /**
         * 区域获取成功
         *
         * @param list 区域列表
         */
        void getAreaSucceed(List<AreaDto> list);
    }

    interface GoodsDetailsView {
        /**
         * 获取商品详情成功
         *
         * @param dto .
         */
        void getGoodsDetailsSucceed(GoodsDetailDto dto);


        /**
         * 收藏成功
         */
        void collSucceed();

        /**
         * 取消收藏成功
         */
        void delCollSucceed();

        /**
         * 减价购物车成功
         */
        void addShopCartGoodsSucceed();
    }

    class CommonPresenter extends AbsPresenter<CommonView> {

        /**
         * 上传图片
         *
         * @param localImgPath 本地图片路径
         * @param code         .
         */
        public void uploadImg(String localImgPath, int... code) {
            mView.onShowPostLoading(null);
            LubanManager.compressSingle(localImgPath, result -> {
                for (String p : result) {
                    File file = new File(p);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                    doAnyRequest(api -> api.uploadImg(body), String.class)
                            .subscribe(new PostLoadingCallback<String>() {
                                @Override
                                protected void onSuccess(NetBean<String> response, String data, int page) {
                                    XZQLog.debug("onUploadImgSucceed url = " + data);
                                    if (mView instanceof UploadImgView) {
                                        ((UploadImgView) mView).onUploadImgSucceed(data, code);
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    XZQLog.debug("onUploadImgFailed! ");

                                }
                            });
                }
            });
        }


        /**
         *
         * @param wordType .
         * @param userId   .
         */
        public void getMobileAssignURL(int wordType, String userId) {
            String json = JsonUtils.setFastModeDelJson(wordType, userId);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
            doAnyRequest(api -> api.getMobileAssignURL(body), String.class).subscribe(new PostLoadingCallback<String>() {
                @Override
                protected void onSuccess(NetBean<String> response, String data, int page) {
                    if (mView instanceof MobileAssignURLView) {
                        ((MobileAssignURLView) mView).onMobileAssignURLSucceed(data);
                    }
                }
            });
        }

        /**
         * 获取区域
         *
         * @param parentId .
         */
        public void getArea(String parentId) {
            doListRequest(api -> api.getArea(parentId)).subscribe(new PostLoadingCallback<List<AreaDto>>() {
                @Override
                protected void onSuccess(NetBean<List<AreaDto>> response, List<AreaDto> data, int page) {
                    if (mView instanceof AreaView) {
                        ((AreaView) mView).getAreaSucceed(data);
                    }
                }
            });
        }

        /**
         * 注册
         *
         * @param param .
         */
        public void register(RegisterParam param) {
            doObjectRequest(api -> api.register(
                    param.phone,
                    param.password,
                    param.nickname,
                    param.regions,
                    param.lnvitationCode,
                    param.storeName,
                    param.storeAddress
            )).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    ConfigSPManager.putLastUserPhone(param.phone);
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 登录
         *
         * @param userName .
         * @param password .
         */
        public void login(String userName, String password) {
            doAnyRequest(api -> api.login(userName, password), LoginDto.class).subscribe(new PostLoadingCallback<LoginDto>() {
                @Override
                protected void onSuccess(NetBean<LoginDto> response, LoginDto data, int page) {
                    ConfigSPManager.putLastUserPhone(userName);
                    if (mView instanceof LoginView) {
                        ((LoginView) mView).loginSucceed(data);
                    }
                }
            });
        }

        /**
         * 添加/编辑收货地址
         *
         * @param address .
         */
        public void editAddress(AddressDto address) {
            doObjectRequest(api -> api.editAddress(address.isAdd() ? "addAddress" : "updAddress",
                    User.getToken()
                    , address.isAdd() ? null : address.id
                    , address.userName
                    , address.userPhone
                    , address.province
                    , address.city
                    , address.district
                    , address.detailAddress
                    , address.addressState
            )).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 设置默认地址
         *
         * @param address .
         */
        public void defAddress(AddressDto address) {
            doObjectRequest(api -> api.updAddressState(User.getToken(), address.id)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(address);
                    }
                }
            });
        }

        /**
         * 删除地址
         *
         * @param address .
         */
        public void delAddress(AddressDto address) {
            doObjectRequest(api -> api.delAddress(User.getToken(), address.id)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(address, address);
                    }
                }
            });
        }

        /**
         * 获取验证码图片
         */
        public void getValidata(String deviceId) {
            doObjectRequest(api -> api.getValidata(deviceId)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof ValidataView) {
                        ((ValidataView) mView).onValidataSucceed(data);
                    }
                }
            });
        }

        /**
         * 获取用户信息
         */
        public void getUser() {
            doAnyRequest(api -> api.getUserInfo(), UserDto.class).subscribe(new PostLoadingCallback<UserDto>() {
                @Override
                protected void onSuccess(NetBean<UserDto> response, UserDto data, int page) {
                    if (mView instanceof UserInfoView) {
                        ((UserInfoView) mView).onGetUserInfoFinish(data);
                    }
                }
            });
        }
        /**
         * 获取应用中心url
         */
        public void applicationUrl(String clientId) {
            doAnyRequest(api -> api.getApplicationUrl(clientId), String.class).subscribe(new PostLoadingCallback<String>() {
                @Override
                protected void onSuccess(NetBean<String> response, String data, int page) {
                    if (mView instanceof ApplicationView) {
                        ((ApplicationView) mView).onGetApplicationView(data);
                    }
                }
            });
        }
        /**
         * 获取版本更新
         */
        public void updateApp(String version) {
            doAnyRequest(api -> api.appUpdate(version), UpdateAppEntity.class).subscribe(new PostLoadingCallback<UpdateAppEntity>(false) {
                @Override
                protected void onSuccess(NetBean<UpdateAppEntity> response, UpdateAppEntity data, int page) {
                    if (mView instanceof UpdateAppView) {
                        ((UpdateAppView) mView).onGetUpdateAppSucceed(data);
                    }
                }
            });
        }
        /**
         * 退出登录
         */
        public void logOut() {
            doObjectRequest(api -> api.logOut()).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }

                @Override
                protected void onError(String error, int code) {
                    super.onError(error, code);
                }
            });
        }


        /**
         * 获取收货地址
         */
        public void findAllAddress() {
            doPagingListRequest(api -> api.findAllAddress(User.getToken()));
        }

        /**
         * 获取应用中心
         */
        public void findAllApplication() {

//            doListRequest(api -> api.findApplication())
//                    .subscribe(new StateCallback<List<ApplicationCenterDto>>() {
//                        @Override
//                        protected void onSuccess(NetBean<List<ApplicationCenterDto>> response, List<ApplicationCenterDto> data) {
//                            //do nothing
//                        }
//
//                        @Override
//                        protected void onList(NetBean<List<ApplicationCenterDto>> response, List<ApplicationCenterDto> data, int page) {
//                            if (mView instanceof DoneView) {
//                                ((DoneView) mView).onDone(data, data);
//                            }
//                        }
//                    });

            doPagingListRequest(api -> api.findApplication());
        }

        public void getNoticeList(String orgId) {
            doPagingListRequest(api -> api.getNoticeList(orgId));
        }

        /**
         * 查找推手/培训列表
         *
         * @param type 0推手中心1培训教学
         */
        public void findTrainingList(int type) {
            doPagingListRequest(api -> api.findTrainingList(User.getToken(), mPage, LIMIT, type));
        }

        /**
         * 查找推手/培训详情
         *
         * @param id .
         */
        public void findTrainingInfo(int id) {
            doAnyRequest(api -> api.findTrainingInfo(User.getToken(), id), PushingDto.class).subscribe(new StateCallback<PushingDto>() {
                @Override
                protected void onSuccess(NetBean<PushingDto> response, PushingDto data) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(data);
                    }
                }
            });
        }

        /**
         * 联系代理商
         */
        public void finaMyAgent() {
            doAnyRequest(api -> api.finaMyAgent(User.getToken()), MyAgentDto.class).subscribe(new StateCallback<MyAgentDto>() {
                @Override
                protected void onSuccess(NetBean<MyAgentDto> response, MyAgentDto data) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(data);
                    }
                }
            });
        }


        /**
         * 获取待办
         */
        public void getWaitDeal() {
//            doListRequest(api -> api.waitDeal())
//                    .subscribe(new StateCallback<List<MatterDto>>() {
//                        @Override
//                        protected void onSuccess(NetBean<List<MatterDto>> response, List<MatterDto> data) {
//                            //do nothing
//                        }
//
//                        @Override
//                        protected void onList(NetBean<List<MatterDto>> response, List<MatterDto> data, int page) {
//                            if (mView instanceof DoneView) {
//                                ((DoneView) mView).onDone(data, data);
//                            }
//                        }
//                    });
            doPagingListRequest(api -> api.waitDeal());
        }

        /**
         * 获取通知
         */
        public void getnoticeForm() {
//            doListRequest(api -> api.noticeForm())
//                    .subscribe(new StateCallback<List<MatterDto>>() {
//                        @Override
//                        protected void onSuccess(NetBean<List<MatterDto>> response, List<MatterDto> data) {
//                            //do nothing
//                        }
//
//                        @Override
//                        protected void onList(NetBean<List<MatterDto>> response, List<MatterDto> data, int page) {
//                            if (mView instanceof DoneView) {
//                                ((DoneView) mView).onDone(data, data);
//                            }
//                        }
//                    });
            doPagingListRequest(api -> api.noticeForm());
        }

        /**
         * 获取已办
         */
        public void getFinishDeal() {
//            doListRequest(api -> api.finishDeal())
//                    .subscribe(new StateCallback<List<MatterDto>>() {
//                        @Override
//                        protected void onSuccess(NetBean<List<MatterDto>> response, List<MatterDto> data) {
//                            //do nothing
//                        }
//
//                        @Override
//                        protected void onList(NetBean<List<MatterDto>> response, List<MatterDto> data, int page) {
//                            if (mView instanceof DoneView) {
//                                ((DoneView) mView).onDone(data, data);
//                            }
//                        }
//                    });
            doPagingListRequest(api -> api.finishDeal());
        }

        /**
         * 获取分类
         */
        public void getGoodsCategory(String... parentId) {
            String id = parentId.length == 1 ? parentId[0] : null;
            doListRequest(api -> api.getGoodsCategory(User.getToken(), id))
                    .subscribe(new StateCallback<List<ClassifyTabDto>>() {
                        @Override
                        protected void onSuccess(NetBean<List<ClassifyTabDto>> response, List<ClassifyTabDto> data) {
                            //do nothing
                        }

                        @Override
                        protected void onList(NetBean<List<ClassifyTabDto>> response, List<ClassifyTabDto> data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data, data);
                            }
                        }
                    });
        }

        /**
         * 获取子分类
         */
        public void getGoodsCategory(int parentId) {
            doListRequest(api -> api.getGoodsCategory(User.getToken(),
                    parentId > 0 ? String.valueOf(parentId) : null))
                    .subscribe(new PostLoadingCallback<List<ClassifyTabDto>>(false) {
                        @Override
                        protected void onSuccess(NetBean<List<ClassifyTabDto>> response, List<ClassifyTabDto> data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data);
                            }
                        }
                    });
        }

        public void getGoodsCategory(int parentId, int index) {
            doListRequest(api -> api.getGoodsCategory(User.getToken(),
                    parentId > 0 ? String.valueOf(parentId) : null))
                    .subscribe(new PostLoadingCallback<List<ClassifyTabDto>>(false) {
                        @Override
                        protected void onSuccess(NetBean<List<ClassifyTabDto>> response, List<ClassifyTabDto> data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data, index);
                            }
                        }
                    });
        }


        /**
         * 获取商品详情
         *
         * @param id .
         */
        public void findGoodsDetails(int id) {
            doAnyRequest(api -> api.findGoodsDetails(User.getToken(), id), GoodsDetailDto.class).subscribe(new StateCallback<GoodsDetailDto>() {
                @Override
                protected void onSuccess(NetBean<GoodsDetailDto> response, GoodsDetailDto data) {
                    if (mView instanceof GoodsDetailsView) {
                        ((GoodsDetailsView) mView).getGoodsDetailsSucceed(data);
                    }
                }
            });
        }

        /**
         * 获取商品评论列表
         */
        public void findPageGoodsEvaluate(int id) {
            doPagingListRequest(api -> api.findPageGoodsEvaluate(id, mPage, LIMIT));
        }

        /**
         * 获取购物车列表
         */
        public void findAllPageShopCartGoods() {
            if (User.hasToken()) {
                doPagingListRequest(api -> api.findAllPageShopCartGoods(User.getToken(), mPage, LIMIT));
            }
        }

        /**
         * 更新购物车数量
         *
         * @param cart .
         * @param num  数量
         */
        public void updShopCartGoodsNum(CartDto cart, int num) {
            doObjectRequest(api -> api.updShopCartGoodsNum(User.getToken(),
                    cart.id,
                    cart.goodsId,
                    cart.skuId,
                    num)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }

                @Override
                protected void onError(String error, int code) {
                    super.onError(error, code);
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }


        /**
         * 订单支付
         *
         * @param orderNumber .
         * @param payType     支付方式0：支付宝 ，1微信，2：线下支付，3：代理商下单
         */
        public void payOrder(String orderNumber, int payType) {
            doObjectRequest(api -> api.payShopCartGoods(User.getToken(),
                    orderNumber,
                    payType)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }

            });
        }


        /**
         * 取消订单
         *
         * @param orderId .
         */
        public void updAbrogateUserOrder(int orderId) {
            doObjectRequest(api -> api.updAbrogateUserOrder(User.getToken(),
                    orderId)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 确认收货
         *
         * @param orderId .
         */
        public void updUserOrderState(int orderId) {
            doObjectRequest(api -> api.updUserOrderState(User.getToken(),
                    orderId)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 删除订单
         *
         * @param orderId .
         */
        public void delUserOrder(int orderId) {
            doObjectRequest(api -> api.delUserOrder(User.getToken(),
                    orderId)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 收藏
         *
         * @param collIds 商品id
         */
        public void addUserCollection(int collIds) {
            if (RouterUtils.openLoginOrNoTokenIfNeed()) {
                return;
            }
            doObjectRequest(api -> api.addUserCollection(User.getToken(),
                    collIds)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof GoodsDetailsView) {
                        ((GoodsDetailsView) mView).collSucceed();
                    }
                }
            });
        }

        /**
         * 取消收藏
         *
         * @param collIds 商品id
         */
        public void delUserCollection(int collIds) {
            if (RouterUtils.openLoginOrNoTokenIfNeed()) {
                return;
            }
            doObjectRequest(api -> api.delUserCollection(User.getToken(),
                    collIds)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof GoodsDetailsView) {
                        ((GoodsDetailsView) mView).delCollSucceed();
                    }
                }
            });
        }

        /**
         * 删除商品
         */
        public void delUserCollection(CollDto dto) {
            doObjectRequest(api -> api.delUserCollection(User.getToken(),
                    dto.collId)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(dto);
                    }
                }
            });
        }

        /**
         * 收藏列表
         */
        public void findPageCollection() {
            doPagingListRequest(api -> api.findPageCollection(User.getToken(),
                    mPage, LIMIT));
        }

        /**
         * 读取消息
         *
         * @param dto .
         */
        public void updUserNews(MsgDto dto) {
            doObjectRequest(api -> api.updUserNews(User.getToken(),
                    dto.id)).subscribe(new PostLoadingCallback<Object>(false) {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                }

                @Override
                protected void onError(String error, int code) {
                }
            });
        }

        /**
         * 删除消息
         *
         * @param dto .
         */
        public void delUserNews(MsgDto dto) {
            doObjectRequest(api -> api.delUserNews(User.getToken(),
                    dto.id)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(dto);
                    }
                }
            });
        }

        /**
         * 消息列表
         */
        public void findAllPageUserNews(int newsTyp) {
            doPagingListRequest(api -> api.findAllPageUserNews(User.getToken(),
                    mPage, LIMIT, newsTyp));
        }

        /**
         * 获取用户信息
         */
        public void getUserInfo() {
            doAnyRequest(api -> api.info(User.getToken()), UserInfoDto.class).subscribe(new StateCallback<UserInfoDto>() {
                @Override
                protected void onSuccess(NetBean<UserInfoDto> response, UserInfoDto data) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(data);
                    }
                }
            });
        }

        /**
         * 我的页面获取个人信息
         */
        public void getUserInfoFromMe() {
            if (!User.hasToken()) {
                return;
            }
            doAnyRequest(api -> api.info(User.getToken()), UserInfoDto.class)
                    .subscribe(new PostLoadingCallback<UserInfoDto>(false) {
                        @Override
                        protected void onSuccess(NetBean<UserInfoDto> response, UserInfoDto data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data);
                            }
                        }

                        @Override
                        protected void onError(String error, int code) {
                        }
                    });
        }


        /**
         * 我的管理信息
         */
        public void getBasis() {
            if (!User.isLogin()) {
                return;
            }
            doAnyRequest(api -> api.basis(User.getToken()), BasisDto.class)
                    .subscribe(new PostLoadingCallback<BasisDto>(false) {
                        @Override
                        protected void onSuccess(NetBean<BasisDto> response, BasisDto data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data);
                            }
                        }

                        @Override
                        protected void onError(String error, int code) {
                        }
                    });
        }

        /**
         * 我的金额
         */
        public void getWithdraw() {
            doAnyRequest(api -> api.basis(User.getToken()), BasisDto.class)
                    .subscribe(new StateCallback<BasisDto>() {
                        @Override
                        protected void onSuccess(NetBean<BasisDto> response, BasisDto data) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data);
                            }
                        }
                    });
        }

        /**
         * 库存列表
         */
        public void getStockList(String goodsType) {
            doPagingListRequest(api -> api.getStockList(User.getToken(),
                    mPage, LIMIT, null, goodsType));
        }

        /**
         * 营业明细
         */
        public void incomeDetailList() {
            doPagingListRequest(api -> api.incomeDetailList(User.getToken(),
                    mPage, LIMIT));
        }

        /**
         * 销售报表
         */
        public void getReportList(String startTime, String endTime) {
            if (TextUtils.isEmpty(startTime)) {
                startTime = null;
            }
            if (TextUtils.isEmpty(endTime)) {
                endTime = null;
            }
            String finalStartTime = startTime;
            String finalEndTime = endTime;
            doAnyRequestRespWithBaseListBean(api -> api.getReportList(User.getToken(),
                    mPage, LIMIT, finalStartTime, finalEndTime));
        }

        /**
         * 采购报表
         */
        public void findAgentPurchase(String startTime, String endTime) {
            if (TextUtils.isEmpty(startTime)) {
                startTime = null;
            }
            if (TextUtils.isEmpty(endTime)) {
                endTime = null;
            }
            String finalStartTime = startTime;
            String finalEndTime = endTime;
            doAnyRequestRespWithBaseListBean(api -> api.findAgentPurchase(User.getToken(),
                    mPage, LIMIT, finalStartTime, finalEndTime));
        }

        /**
         * 库存列表
         */
        public void findStockList() {
            doPagingListRequest(api -> api.finaStockList(User.getToken(),
                    mPage, LIMIT, null));
        }

        /**
         * 库存明细列表
         */
        public void findStockInfo(int goodsId) {
            doPagingListRequest(api -> api.finaStockInfo(User.getToken(),
                    mPage, LIMIT, goodsId));
        }

        /**
         * 奖金池收入明细列表
         */
        public void bonusDetail() {
            doPagingListRequest(api -> api.bonusDetail(User.getToken(),
                    mPage, LIMIT));
        }

        /**
         * 奖金池公告
         */
        public void bulletin() {
            if (!User.hasToken()) {
                return;
            }
            doAnyRequest(api -> api.bulletin(User.getToken()), BulletinDto.class)
                    .subscribe(new PostLoadingCallback<BulletinDto>(false) {
                        @Override
                        protected void onSuccess(NetBean<BulletinDto> response, BulletinDto data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data, data);
                            }
                        }

                        @Override
                        protected void onError(String error, int code) {
                        }
                    });
        }

        /**
         * 我绑定的银行卡列表
         */
        public void findAllBankCard() {
            doPagingListRequest(api -> api.findAllBankCard(User.getToken()));
        }

        /**
         * 获取默认银行卡
         *
         * @param doneView .
         */
        public void findDefBankCard(DoneView doneView) {
            doListRequest(api -> api.findAllBankCard(User.getToken())).subscribe(new PostLoadingCallback<List<BlankDto>>(false) {
                @Override
                protected void onSuccess(NetBean<List<BlankDto>> response, List<BlankDto> data, int page) {
                    for (int i = 0; i < data.size(); i++) {
                        BlankDto dto = data.get(i);
                        if (dto.isDef()) {
                            doneView.onDone(dto);
                            break;
                        }
                    }
                }
            });
        }

        /**
         * 添加/编辑银行卡
         *
         * @param id               id > 0  为编辑
         * @param tvBankName       银行名称
         * @param tvBankBranchName 支行名称
         * @param cardHolder       持有者
         * @param bankNumber       卡号
         * @param bankCellphone    预留手机号码
         * @param isDef            是否默认
         */
        public void addBankCard(int id,
                                TextView tvBankName,
                                TextView tvBankBranchName,
                                TextView cardHolder,
                                TextView bankNumber,
                                TextView bankCellphone,
                                boolean isDef) {
            if (id > 0) {
                doObjectRequest(api -> api.updBankCard(User.getToken(),
                        tvBankName.getText().toString(),
                        tvBankBranchName.getText().toString(),
                        cardHolder.getText().toString(),
                        bankNumber.getText().toString(),
                        bankCellphone.getText().toString(),
                        id,
                        isDef ? 0 : 1)).subscribe(new PostLoadingCallback<Object>() {
                    @Override
                    protected void onSuccess(NetBean<Object> response, Object data, int page) {
                        if (mView instanceof DoneView) {
                            ((DoneView) mView).onDone();
                        }
                    }
                });
            } else {
                doObjectRequest(api -> api.addBankCard(User.getToken(),
                        tvBankName.getText().toString(),
                        tvBankBranchName.getText().toString(),
                        cardHolder.getText().toString(),
                        bankNumber.getText().toString(),
                        bankCellphone.getText().toString(),
                        isDef ? 0 : 1)).subscribe(new PostLoadingCallback<Object>() {
                    @Override
                    protected void onSuccess(NetBean<Object> response, Object data, int page) {
                        if (mView instanceof DoneView) {
                            ((DoneView) mView).onDone();
                        }
                    }
                });
            }
        }

        /**
         * 删除银行卡
         *
         * @param blank .
         */
        public void delBankCard(BlankDto blank) {
            doObjectRequest(api -> api.delBankCard(User.getToken(),
                    blank.id)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(blank);
                    }
                }
            });
        }

        /**
         * 设置默认银行卡
         *
         * @param blank .
         * @param isDef .
         */
        public void defBlankCard(BlankDto blank, boolean isDef) {
            doObjectRequest(api -> api.defaultBankCard(User.getToken(), blank.id, isDef ? 0 : 1)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(blank, isDef);
                    }
                }
            });
        }

        /**
         * 获取我的网点
         *
         * @param type 1未审核 2已审核
         */
        public void getWebsite(int type) {
            doPagingListRequest(api -> api.getWebsite(User.getToken(), type));
        }

        /**
         * 审批网点
         *
         * @param id .
         */
        public void approval(int id) {
            doObjectRequest(api -> api.approval(User.getToken(), id)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 添加购物车
         *
         * @param goodsId    .
         * @param num        .
         * @param skuPriceId .
         */
        public void addShopCartGoods(int goodsId, int num, int skuPriceId) {
            if (RouterUtils.openLoginOrNoTokenIfNeed()) {
                return;
            }
            if (skuPriceId == 0 || num == 0) {
                return;
            }
            doObjectRequest(api -> api.addShopCartGoods(User.getToken(),
                    goodsId,
                    num,
                    skuPriceId
            )).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof GoodsDetailsView) {
                        ((GoodsDetailsView) mView).addShopCartGoodsSucceed();
                    }
                }
            });
        }

        /**
         * 删除购物车
         *
         * @param arrayIds .
         */
        public void delShopCartGoods(String arrayIds) {
            if (TextUtils.isEmpty(arrayIds)) {
                return;
            }
            doObjectRequest(api -> api.delShopCartGoods(User.getToken(),
                    arrayIds
            )).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }


        /**
         * 退换货
         *
         * @param orderId          .
         * @param returnBackReason .
         * @param returnBackPhoto  .
         * @param returnType       退换类型：0退1换
         * @param orderItemId      订单字表id
         * @param description      .
         */
        public void updRefundOrderMoney(int orderId,
                                        String returnBackReason,
                                        String returnBackPhoto,
                                        int returnType,
                                        int orderItemId,
                                        String description) {
            doObjectRequest(api -> api.updRefundOrderMoney(User.getToken(),
                    orderId,
                    returnBackReason,
                    returnBackPhoto,
                    returnType,
                    orderItemId,
                    description
            )).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 添加商品评价
         */
        public void addOrderGoodsEvaluate(RequestBody body) {
            doObjectRequest(api -> api.addOrderGoodsEvaluate(
                    body)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 确认发货
         */
        public void orderShip(int id) {
            doObjectRequest(api -> api.orderShip(User.getToken(),
                    id)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 获取我的优惠券
         *
         * @param applyType 使用状态0未使用1已使用
         */
        public void finaUserLotteryTicket(int applyType) {
            doPagingListRequest(api -> api.finaUserLotteryTicket(User.getToken(), applyType));
        }


        /**
         * 确认退换货
         *
         * @param id 退换货d
         */
        public void orderReturnComplete(int id) {
            doObjectRequest(api -> api.orderReturnComplete(User.getToken(),
                    id)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 同意/拒绝退换货
         *
         * @param id         退换货d
         * @param returnType 2通过3驳回
         */
        public void orderReturnConfirm(int id, int returnType) {
            doObjectRequest(api -> api.orderReturnConfirm(User.getToken(),
                    id, returnType)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 获取首页广告和分类
         */
        public void getHomeAd() {
            doAnyRequest(api -> api.getHomeAd(User.getToken()
            ), HomeDto.class).subscribe(new PostLoadingCallback<HomeDto>(false) {
                @Override
                protected void onSuccess(NetBean<HomeDto> response, HomeDto data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(data);
                    }
                }
            });
        }


        /**
         * 限时抢购时间列表
         */
        public void activityTimeList() {
            doListRequest(api -> api.activityTimeList(User.getToken())).subscribe(new PostLoadingCallback<List<LimitedTimeDto>>() {
                @Override
                protected void onSuccess(NetBean<List<LimitedTimeDto>> response, List<LimitedTimeDto> data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(data);
                    }
                }
            });
        }

        /**
         * 获取限时抢购列表
         */
        public void activityLimitedGoodsList(Integer activityTimeId) {
            doAnyRequestRespWithBaseListBean(api -> api.activityLimitedGoodsList(User.getToken(), mPage, LIMIT, activityTimeId));
        }

        /**
         * 首页获取第一页限时抢购列表
         */
        public void activityLimitedGoodsListFromHome() {
            RequestUtils.doAnyRequestRespWithBaseListBean(new LimitedDto<>(),
                    api -> api.activityLimitedGoodsList(User.getToken(), 1, 5, null))
                    .subscribe(new PostLoadingCallback<LimitedDto<LimitedGoodsDto>>(false) {
                        @Override
                        protected void onSuccess(NetBean<LimitedDto<LimitedGoodsDto>> response,
                                                 LimitedDto<LimitedGoodsDto> data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data, data);
                            }
                        }
                    });
        }

        /**
         * 获取定量抢购列表
         */
        public void activityQuantitativeList() {
            doPagingListRequest(api -> api.activityQuantitativeList(User.getToken(), mPage, LIMIT));
        }

        /**
         * 首页获取第一页定量抢购列表
         */
        public void activityQuantitativeListFromHome() {
            doListRequest(api -> api.activityQuantitativeList(User.getToken(), 1, 5))
                    .subscribe(new PostLoadingCallback<List<QuantitativeGoodsDto>>(false) {
                        @Override
                        protected void onSuccess(NetBean<List<QuantitativeGoodsDto>> response,
                                                 List<QuantitativeGoodsDto> data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data, data, data);
                            }
                        }
                    });
        }

        /**
         * 取消单个商品
         *
         * @param orderItemId .
         */
        public void updSingleGoodsUserOrder(int orderItemId) {
            doObjectRequest(api -> api.updSingleGoodsUserOrder(User.getToken(),
                    orderItemId)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 获取退换货理由
         */
        public void returnList() {
            doListRequest(api -> api.returnList(User.getToken()))
                    .subscribe(new PostLoadingCallback<List<CauseDto>>() {
                        @Override
                        protected void onSuccess(NetBean<List<CauseDto>> response, List<CauseDto> data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data);
                            }
                        }
                    });
        }

        /**
         * 交易流水
         *
         * @param state 状态(0：入,1：出)
         * @param type  流水类型（1：代理商下单,2:微信，3：支付宝，4:积分,5：优惠卷,6:活动押金）
         */
        public void getBillList(int state, Integer type) {
            Integer stateKey = state < 0 ? null : state;
            doPagingListRequest(api -> api.getBillList(User.getToken(), mPage, LIMIT, stateKey, type));
        }

        /**
         * 我的积分
         */
        public void finaMyIntegral() {
            doAnyRequest(api -> api.finaMyIntegral(User.getToken()), SingleFieldDto.class)
                    .subscribe(new PostLoadingCallback<SingleFieldDto>(false) {
                        @Override
                        protected void onSuccess(NetBean<SingleFieldDto> response, SingleFieldDto data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data.getValue());
                            }
                        }
                    });
        }

        /**
         * 获取商品的sku
         */
        public void goodsSkuList(int id, Object goods) {
            doListRequest(api -> api.goodsSkuList(id))
                    .subscribe(new PostLoadingCallback<List<GoodsSkuDto>>() {
                        @Override
                        protected void onSuccess(NetBean<List<GoodsSkuDto>> response, List<GoodsSkuDto> data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data, goods, goods);
                            }
                        }
                    });
        }

        public void addShopCart(int goodsId, int num, int skuPriceId) {
            if (RouterUtils.openLoginOrNoTokenIfNeed()) {
                return;
            }
            if (skuPriceId == 0 || num == 0) {
                return;
            }
            doObjectRequest(api -> api.addShopCartGoods(User.getToken(),
                    goodsId,
                    num,
                    skuPriceId
            )).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 购物车总数
         */
        public void findShopCdartTotalAndAmount() {
            if (!User.hasToken()) {
                return;
            }
            doAnyRequest(api -> api.findShopCdartTotalAndAmount(User.getToken()), CartNumDto.class)
                    .subscribe(new PostLoadingCallback<CartNumDto>(false) {
                        @Override
                        protected void onSuccess(NetBean<CartNumDto> response, CartNumDto data, int page) {
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone(data, 0, 0, 0);
                            }
                        }
                    });
        }

        /**
         * 提现
         *
         * @param money      提现金额
         * @param type       提现类型(0:余额, 1:奖金)
         * @param bankCardId .
         */
        public void applyUserWithdraw(String money, int type, int bankCardId) {
            doObjectRequest(api -> api.applyUserWithdraw(User.getToken(),
                    money,
                    type,
                    bankCardId
            )).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 提现明细
         */
        public void findPageUserBill() {
            doPagingListRequest(api -> api.findPageUserBill(User.getToken(),
                    mPage, LIMIT));
        }

        /**
         * 发送验证码
         *
         * @param phone .
         */
        public void sendCode(String phone) {
            doObjectRequest(api -> api.sendCode(phone)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 验证手机号码和验证码
         *
         * @param phone .
         * @param code  .
         */
        public void verificationCode(String phone, String code) {
            doObjectRequest(api -> api.verificationCode(phone, code)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(1);
                    }
                }
            });
        }

        /**
         * 忘记密码
         *
         * @param phone .
         * @param pwd   .
         * @param code  .
         */
        public void forgetpwd(String phone, String pwd, String code) {
            doObjectRequest(api -> api.forgetpwd(phone, pwd, code)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(phone);
                    }
                }
            });
        }

        /**
         * 获取网点详情
         *
         * @param id .
         */
        public void getWebsiteInfo(int id) {
            doAnyRequest(api -> api.websiteInfo(id), WebsiteDto.UserListBean.class).subscribe(new StateCallback<WebsiteDto.UserListBean>() {
                @Override
                protected void onSuccess(NetBean<WebsiteDto.UserListBean> response, WebsiteDto.UserListBean data) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone(data);
                    }
                }
            });
        }

        /**
         * 修改订单价格
         *
         * @param id    .
         * @param price .
         */
        public void updateOrder(int id, double price) {
            if (price <= 0) {
                return;
            }
            doObjectRequest(api -> api.updateOrder(User.getToken(), id, String.valueOf(price))).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    MyToast.show("修改成功");
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 确认收款
         *
         * @param id .
         */
        public void orderReceipt(int id) {
            doObjectRequest(api -> api.orderReceipt(User.getToken(), id)).subscribe(new PostLoadingCallback<Object>() {
                @Override
                protected void onSuccess(NetBean<Object> response, Object data, int page) {
                    if (mView instanceof DoneView) {
                        ((DoneView) mView).onDone();
                    }
                }
            });
        }

        /**
         * 代理商修改数量
         *
         * @param id          .
         * @param goodsNum    .
         * @param modifyPrice .
         * @param actualPrice .
         */
        public void updOrderItem(int id, int goodsNum, String modifyPrice, String actualPrice) {
            if (goodsNum <= 0) {
                return;
            }
            doObjectRequest(api -> api.updOrderItem(User.getToken(), id, goodsNum, modifyPrice, actualPrice))
                    .subscribe(new PostLoadingCallback<Object>() {
                        @Override
                        protected void onSuccess(NetBean<Object> response, Object data, int page) {
                            MyToast.show("修改成功");
                            if (mView instanceof DoneView) {
                                ((DoneView) mView).onDone();
                            }
                        }
                    });
        }
    }
}
