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
import com.xzq.module_base.bean.MatterDto;
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
    interface SearchView {
        /**
         * 待办搜索获取成功
         *
         * @param list 列表
         */
        void getSearchListSucceed(List<MatterDto> list);
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

            doAnyRequestRespWithBaseListBean(api -> api.getNoticeList(orgId));
//            doPagingListRequest(api -> api.getNoticeList(orgId));

        }

        public void getNoticeUserList(String username) {

            doAnyRequestRespWithBaseListBean(api -> api.getNoticeUserList(username));

        }



        /**
         * 获取待办
         */
        public void getWaitDeal(int start,int pageSize) {
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
            String json = JsonUtils.getMobileUnProcessAssign(start,pageSize);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
            doPagingListRequest(api -> api.waitDeal(body));
        }
        /**
         * 获取搜索待办
         */
        public void getSearchWaitDeal(String keyword,int pageSize,int type) {

            String json = JsonUtils.getSearchMobileUnProcessAssign(keyword, pageSize, type);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
            doListRequest(api -> api.waitDeal(body))
                    .subscribe(new StateCallback<List<MatterDto>>() {
                        @Override
                        protected void onSuccess(NetBean<List<MatterDto>> response, List<MatterDto> data) {
                            //do nothing
                        }

                        @Override
                        protected void onList(NetBean<List<MatterDto>> response, List<MatterDto> data, int page) {
                            if (mView instanceof SearchView) {
                                ((SearchView) mView).getSearchListSucceed(data);
                            }
                        }
                    });
//            doPagingListRequest(api -> api.waitDeal(body));
        }

        /**
         * 获取通知
         */
        public void getnoticeForm(int pageSize) {
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
            String json = JsonUtils.getNoticeForm(pageSize);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

            doPagingListRequest(api -> api.noticeForm(body));
        }
        /**
         * 搜索通知
         */
        public void getSearchNoticeForm(String keyword,int pageSize,int type) {
            doListRequest(api -> api.searchNoticeForm(keyword, pageSize, type))
                    .subscribe(new StateCallback<List<MatterDto>>() {
                        @Override
                        protected void onSuccess(NetBean<List<MatterDto>> response, List<MatterDto> data) {
                            //do nothing
                        }

                        @Override
                        protected void onList(NetBean<List<MatterDto>> response, List<MatterDto> data, int page) {
                            if (mView instanceof SearchView) {
                                ((SearchView) mView).getSearchListSucceed(data);
                            }
                        }
                    });
//            doPagingListRequest(api -> api.searchNoticeForm(keyword,pageSize,type));
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
         * 获取搜索已办
         */
        public void getsearchFinishDeal(String keyword,int pageSize,int type) {
            doListRequest(api -> api.searchFinishDeal(keyword, pageSize, type))
                    .subscribe(new StateCallback<List<MatterDto>>() {
                        @Override
                        protected void onSuccess(NetBean<List<MatterDto>> response, List<MatterDto> data) {
                            //do nothing
                        }

                        @Override
                        protected void onList(NetBean<List<MatterDto>> response, List<MatterDto> data, int page) {
                            if (mView instanceof SearchView) {
                                ((SearchView) mView).getSearchListSucceed(data);
                            }
                        }
                    });
//            doPagingListRequest(api -> api.searchFinishDeal(keyword,pageSize,type));
        }





    }
}
