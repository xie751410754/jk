package com.plinkdt.jk.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.xzq.module_base.bean.AreaDto;
import com.xzq.module_base.dialog.photo.BottomSelectDialog;
import com.xzq.module_base.utils.DateUtils;
import com.xzq.module_base.utils.PermissionUtil;
import com.xzq.module_base.utils.StringUtils;
import com.plinkdt.jk.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * CommonUtils
 * Created by xzq on 2019/8/19.
 */
public class CommonUtils {
    // 进入相册 以下是例子：用不到的api可以不写
//        PictureSelector.create(me)
//                .openGallery(PictureMimeType.ofAll())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
//                .maxSelectNum()// 最大图片选择数量 int
//                .minSelectNum()// 最小选择数量 int
//                .imageSpanCount(4)// 每行显示个数 int
//                .selectionMode()// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
//                .previewImage()// 是否可预览图片 true or false
//                .previewVideo()// 是否可预览视频 true or false
//                .enablePreviewAudio() // 是否可播放音频 true or false
//                .isCamera()// 是否显示拍照按钮 true or false
//                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
//                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
//                .enableCrop()// 是否裁剪 true or false
//                .compress()// 是否压缩 true or false
//                .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                .hideBottomControls()// 是否显示uCrop工具栏，默认不显示 true or false
//                .isGif()// 是否显示gif图片 true or false
//                .compressSavePath(getPath())//压缩图片保存地址
//                .freeStyleCropEnabled()// 裁剪框是否可拖拽 true or false
//                .circleDimmedLayer()// 是否圆形裁剪 true or false
//                .showCropFrame()// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
//                .showCropGrid()// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
//                .openClickSound()// 是否开启点击声音 true or false
//                .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
//                .previewEggs()// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                .cropCompressQuality()// 裁剪压缩质量 默认90 int
//                .minimumCompressSize(100)// 小于100kb的图片不压缩
//                .synOrAsy(true)//同步true或异步false 压缩 默认同步
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
//                .rotateEnabled() // 裁剪是否可旋转图片 true or false
//                .scaleEnabled()// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
//                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
//                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
//                .recordVideoSecond()//视频秒数录制 默认60s int
//                .isDragFrame(false)// 是否可拖动裁剪框(固定)
//                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

    public static String getPath(Intent data) {
        // 图片、视频、音频选择结果回调
        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
        // 例如 LocalMedia 里面返回三种path
        // 1.media.getPath(); 为原图path
        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
        // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
        if (!selectList.isEmpty()) {
            LocalMedia media = selectList.get(0);
            if (media.isCompressed()) {
                return media.getCompressPath();
            }
            if (media.isCut()) {
                return media.getCutPath();
            }
            return media.getPath();
        }
        return null;
    }


    public static String getPath(LocalMedia media) {
        // 图片、视频、音频选择结果回调
        // 例如 LocalMedia 里面返回三种path
        // 1.media.getPath(); 为原图path
        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
        // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
        if (media.isCompressed()) {
            return media.getCompressPath();
        }
        if (media.isCut()) {
            return media.getCutPath();
        }
        return media.getPath();
    }

    //选取头像
    public static void selectHead(Fragment activity) {
        selectSingle(activity, true, PictureConfig.CHOOSE_REQUEST, null);
    }

    public static void selectSingle(Fragment activity, boolean enableCrop, int requestCode, String title) {
        BottomSelectDialog dialog = new BottomSelectDialog(activity.getContext());
        dialog.show(title, "拍照", "从相册选择");
        dialog.setOnBottomTabClickListener(new BottomSelectDialog.OnBottomTabClickListener() {
            @Override
            public void onBottomTabClick(int position) {

                if (position == 0) {
                    //拍照
                    PermissionUtil.requestCamera(new PermissionUtil.PermissionCallback() {
                        @Override
                        public void onGotPermission() {
                            PictureSelector.create(activity)
                                    .openCamera(PictureMimeType.ofImage())
                                    .selectionMode(PictureConfig.SINGLE)
                                    .enableCrop(enableCrop)
                                    .isDragFrame(true)
                                    .freeStyleCropEnabled(false)
                                    .circleDimmedLayer(false)
                                    .showCropFrame(true)
                                    .showCropGrid(true)
                                    .isZoomAnim(false)
                                    .rotateEnabled(true)
                                    .withAspectRatio(1, 1)
                                    .forResult(requestCode);
                        }
                    });

                } else {

                    //相册
                    PermissionUtil.requestAlbum(new PermissionUtil.PermissionCallback() {
                        @Override
                        public void onGotPermission() {
                            PictureSelector.create(activity)
                                    .openGallery(PictureMimeType.ofImage())
                                    .selectionMode(PictureConfig.SINGLE)
                                    .enableCrop(enableCrop)
                                    .isDragFrame(true)
                                    .freeStyleCropEnabled(false)
                                    .circleDimmedLayer(false)
                                    .showCropFrame(true)
                                    .showCropGrid(true)
                                    .isZoomAnim(false)
                                    .rotateEnabled(true)
                                    .withAspectRatio(1, 1)
                                    /* .theme(R.style.picture_default_style2)*/
                                    .forResult(requestCode);
                        }
                    });

                }
            }
        });
    }

    public static void selectMultiplePic(Activity activity, int maxSelectNum, List<LocalMedia> selectionMedia) {
        selectMultiplePic(activity, maxSelectNum, selectionMedia, "图片选取");
    }

    public static void selectMultiplePic(Activity activity, int maxSelectNum, List<LocalMedia> selectionMedia, String title) {
        BottomSelectDialog dialog = new BottomSelectDialog(activity);
        dialog.show(title, "拍摄一张", "从相册选取");
        dialog.setOnBottomTabClickListener(new BottomSelectDialog.OnBottomTabClickListener() {
            @Override
            public void onBottomTabClick(int position) {
                if (position == 0) {
                    PermissionUtil.requestCamera(new PermissionUtil.PermissionCallback() {
                        @Override
                        public void onGotPermission() {
                            PictureSelector.create(activity)
                                    .openCamera(PictureMimeType.ofImage())
                                    .compress(true)
                                    .minimumCompressSize(100)
                                    .selectionMedia(selectionMedia)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }
                    });
                } else {
                    PermissionUtil.requestAlbum(new PermissionUtil.PermissionCallback() {
                        @Override
                        public void onGotPermission() {
                            PictureSelector.create(activity)
                                    .openGallery(PictureMimeType.ofImage())
                                    .maxSelectNum(maxSelectNum)
                                    .compress(true)
                                    .minimumCompressSize(100)
                                    .isZoomAnim(false)
                                    .selectionMedia(selectionMedia)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        }
                    });
                }
            }
        });
    }

    public static void showTimePicker(String pattern, View v, Activity me, OnTimeSelectListener listener) {
        showTimePicker(pattern, v, me, listener, null, null);
    }

    public static void showTimeMaxEndDate(String pattern, View v, Activity me, OnTimeSelectListener listener) {
        showTimePicker(pattern, v, me, listener, null, Calendar.getInstance());
    }

    /**
     * 显示时间选择器
     *
     * @param pattern   .
     * @param v         .
     * @param me        .
     * @param listener  .
     * @param startDate .
     * @param endDate   .
     */
    public static void showTimePicker(String pattern,
                                      View v,
                                      Activity me,
                                      OnTimeSelectListener listener,
                                      Calendar startDate,
                                      Calendar endDate) {
        KeyboardUtils.hideSoftInput(me);
        TimePickerView pvTime = new TimePickerBuilder(me, listener)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.my_pickerview_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                    }
                })
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .setSubmitColor(0xFFD5AA6D)//确定按钮文字颜色
                .setCancelColor(0xFF989898)//取消按钮文字颜色
                .setSubCalSize(13)//确定取消按钮文字大小(sp)
                .setTitleSize(15)
                .setTextColorCenter(ContextCompat.getColor(me, R.color.color_main))
                .setTitleText("选择日期")
                .setLineSpacingMultiplier(2f)
                .setBgColor(Color.TRANSPARENT)
                .setDividerColor(Color.TRANSPARENT)
                .setTitleColor(0xFF656565)
                .build();
        if (BarUtils.isNavBarVisible(me)) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                    pvTime.getDialogContainerLayout().getLayoutParams();
            params.bottomMargin = BarUtils.getNavBarHeight();
            pvTime.getDialogContainerLayout().requestLayout();
        }
        if (v instanceof TextView) {
            String sele = ((TextView) v).getText().toString();
            Calendar current = Calendar.getInstance();
            current.setTime(new Date(DateUtils.getMillis(sele, pattern)));
            pvTime.setDate(current);
        }
        pvTime.show();

        me.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.returnData();
                pvTime.dismiss();
            }
        });

        me.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.dismiss();
            }
        });
    }

    /**
     * 显示区域选择器
     *
     * @param v        .
     * @param me       .
     * @param provList 区域列表
     * @param callback .
     */
    public static void showAreaPicker(View v, Activity me, List<AreaDto> provList, OnAreaCallback callback) {
        if (provList == null || provList.isEmpty()) {
            return;
        }
        KeyboardUtils.hideSoftInput(me);
        final List<AreaDto> options1Items = provList;
        final List<List<AreaDto>> options2Items = new ArrayList<>();
        final List<List<List<AreaDto>>> options3Items = new ArrayList<>();
        for (int P = 0; P < provList.size(); P++) {//遍历省（一级）
            AreaDto prov = provList.get(P);
            final List<AreaDto> cityList = new ArrayList<>(prov.getChilds());  //该省的所有城市列表（二级）
            final List<List<AreaDto>> areaList = new ArrayList<>();//该省的所有地区列表（三级）
            int cityCount = prov.childCount();
            for (int C = 0; C < cityCount; C++) {//遍历城市
                areaList.add(prov.get(C).getChilds());//添加该省所有地区数据
            }
            //该省没有城市则添加空数据
            if (areaList.isEmpty()) {
                areaList.addAll(prov.getAreaList());
            }
            options2Items.add(cityList);
            options3Items.add(areaList);
        }

        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(me, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                v.setTag(v.getId(), new int[]{options1, options2, options3});
                AreaDto prov = options1Items.get(options1);
                AreaDto city = options2Items.get(options1).get(options2);
                AreaDto area = options3Items.get(options1).get(options2).get(options3);
                String code = !area.isEmpty() ? area.code : !city.isEmpty() ? city.code : prov.code;
                code = code.replaceAll("\\.", ",");
                String name = prov.name + city.name + area.name;
                if (callback != null) {
                    callback.onAreaCallback(code, name);
                }
            }
        }).setSubmitColor(0xFFD5AA6D)//确定按钮文字颜色
                .setCancelColor(0xFF989898)//取消按钮文字颜色
                .setSubCalSize(13)//确定取消按钮文字大小(sp)
                .setTitleSize(15)
                .setTitleText("选择区域")
                .setTitleColor(0xFF656565)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);
        if (v.getTag(v.getId()) instanceof int[]) {
            int[] optionsIndex = (int[]) v.getTag(v.getId());
            int option1 = optionsIndex.length > 0 ? optionsIndex[0] : 0;
            int option2 = optionsIndex.length > 1 ? optionsIndex[1] : 0;
            int option3 = optionsIndex.length > 2 ? optionsIndex[2] : 0;
            pvOptions.setSelectOptions(option1, option2, option3);
        }
        if (BarUtils.isNavBarVisible(me)) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                    pvOptions.getDialogContainerLayout().getLayoutParams();
            params.bottomMargin = BarUtils.getNavBarHeight();
            pvOptions.getDialogContainerLayout().requestLayout();
        }
        pvOptions.show(v);
    }

    private static String[] MONTH = {"", "01", "02", "03", "04", "05",
            "06", "07", "08", "09", "10", "11", "12"};

    private static List<String> getMonths(String year) {
        List<String> defMonths = Arrays.asList(MONTH);
        if (TextUtils.equals(DateUtils.getNowYearStr(), year)) {
            defMonths = defMonths.subList(0, DateUtils.getNowMonth() + 2);
        }
        return defMonths;
    }

    private static List<String> getDays(String year, String monthStr) {
        List<String> days = new ArrayList<>();
        int month = StringUtils.toInt(monthStr, 0);
        int daysCount;
        if (month == 0) {
            days.add("");
            return days;
        } else if (month == 2) {
            daysCount = TimeUtils.isLeapYear(StringUtils.toInt(year)) ? 29 : 28;
        } else {
            int modulus = month <= 7 ? 1 : 0;
            daysCount = month % 2 == modulus ? 31 : 30;
        }
        final String nowYear = DateUtils.getNowYearStr();
        final int nowMonth = DateUtils.getNowMonth();
        if (TextUtils.equals(nowYear, year) && month == nowMonth + 1) {
            daysCount = DateUtils.getNowDayOfMonth();
        }
        for (int i = 0; i <= daysCount; i++) {
            if (i == 0) {
                days.add("");
            } else if (i < 10) {
                days.add("0" + i);
            } else {
                days.add(String.valueOf(i));
            }
        }
        return days;
    }

    /**
     * 显示销售报表时间选择器
     *
     * @param v        .
     * @param me       .
     * @param callback .
     */
    public static void showReportTimePicker(View v, Activity me, OnReportTimeCallback callback) {
        KeyboardUtils.hideSoftInput(me);
        List<String> years = new ArrayList<>();
        final int startYear = 2019;
        final int nowYear = DateUtils.getNowYear();
        for (int i = startYear; i <= nowYear; i++) {
            years.add(String.valueOf(i));
        }
        final List<String> options1Items = years;
        final List<List<String>> options2Items = new ArrayList<>();
        final List<List<List<String>>> options3Items = new ArrayList<>();

        for (int yIndex = 0; yIndex < years.size(); yIndex++) {
            List<String> months = getMonths(years.get(yIndex));
            List<List<String>> dayList = new ArrayList<>();
            for (int mIndex = 0; mIndex < months.size(); mIndex++) {
                dayList.add(new ArrayList<>(getDays(years.get(yIndex), months.get(mIndex))));
            }
            options2Items.add(months);
            options3Items.add(dayList);
        }

        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(me, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                v.setTag(v.getId(), new int[]{options1, options2, options3});
                String year = options1Items.get(options1);
                String month = options2Items.get(options1).get(options2);
                String day = options3Items.get(options1).get(options2).get(options3);
                String time = year;
                if (!TextUtils.isEmpty(month)) {
                    time = year + "-" + month;
                    if (!TextUtils.isEmpty(day)) {
                        time = time + "-" + day;
                    }
                }
                if (callback != null) {
                    callback.onReportTimeCallback(time);
                }
            }
        }).setSubmitColor(0xFFD5AA6D)//确定按钮文字颜色
                .setCancelColor(0xFF989898)//取消按钮文字颜色
                .setSubCalSize(13)//确定取消按钮文字大小(sp)
                .setTitleSize(15)
                .setTitleText("选择日期")
                .setTitleColor(0xFF656565)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);
        if (v.getTag(v.getId()) instanceof int[]) {
            int[] optionsIndex = (int[]) v.getTag(v.getId());
            int option1 = optionsIndex.length > 0 ? optionsIndex[0] : 0;
            int option2 = optionsIndex.length > 1 ? optionsIndex[1] : 0;
            int option3 = optionsIndex.length > 2 ? optionsIndex[2] : 0;
            pvOptions.setSelectOptions(option1, option2, option3);
        } else {
            pvOptions.setSelectOptions(years.size() - 1);
        }
        if (BarUtils.isNavBarVisible(me)) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                    pvOptions.getDialogContainerLayout().getLayoutParams();
            params.bottomMargin = BarUtils.getNavBarHeight();
            pvOptions.getDialogContainerLayout().requestLayout();
        }
        pvOptions.show(v);
    }

    public interface OnAreaCallback {
        void onAreaCallback(String code, String name);
    }

    public interface OnReportTimeCallback {
        void onReportTimeCallback(String time);
    }

    public static void switchPwd(ImageView ivPwd, EditText edtPwd) {
        ivPwd.setSelected(!ivPwd.isSelected());
        if (ivPwd.isSelected()) {
            edtPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            edtPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        edtPwd.setSelection(edtPwd.getText().length());
    }

    /**
     * 选中当前view #View.setSelected()
     *
     * @param view 当前view
     */
    public static void selectThis(View view) {
        if (view == null) {
            return;
        }
        ViewGroup vg = (ViewGroup) view.getParent();
        int count = vg.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = vg.getChildAt(i);
            if (v != view) {
                v.setSelected(false);
            }
        }
        view.setSelected(true);
    }

    /**
     * 取消选中的View
     *
     * @param parent ViewGroup
     */
    public static void unSelectFrom(ViewGroup parent) {
        if (parent != null) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);
                if (child instanceof ViewGroup) {
                    unSelectFrom((ViewGroup) child);
                } else {
                    child.setSelected(false);
                }
            }
        }
    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static void setLeftDrawable(TextView textView, int left) {
        textView.setCompoundDrawablesWithIntrinsicBounds(left, 0, 0, 0);
    }

}
