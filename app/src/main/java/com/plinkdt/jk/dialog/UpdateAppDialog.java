package com.plinkdt.jk.dialog;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.blankj.utilcode.util.ActivityUtils;
import com.plinkdt.jk.R;
import com.plinkdt.jk.utils.DeviceUtils;
import com.plinkdt.jk.utils.DownloadUtil;
import com.xzq.module_base.User;
import com.xzq.module_base.bean.UpdateAppEntity;
import com.xzq.module_base.utils.MyToast;
import com.xzq.module_base.utils.SizeUtils;
import com.xzq.module_base.utils.XZQLog;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;


public class UpdateAppDialog extends BaseDialogFragment {

    public static final String TAG = "UpdateAppDialog";

    @BindView(R.id.version_name)
    TextView mVersionName;

    @BindView(R.id.update_content)
    TextView mUpdateContent;

    @BindView(R.id.update_progress_tv)
    TextView mUpdateProgressTv;

    @BindView(R.id.update_progress_bar)
    ProgressBar mUpdateProgressBar;

    @BindView(R.id.update_progress_layout)
    LinearLayout mUpdateProgressLayout;
    @BindView(R.id.rl_layout)
    RelativeLayout rlLayout;

    private UpdateAppEntity mUpdateAppInfos;


    public UpdateAppDialog(boolean bottomAnim, boolean isFullScreen, UpdateAppEntity mUpdateApp) {
        super(bottomAnim, isFullScreen);
        mUpdateAppInfos = mUpdateApp;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.update_app_layout;
    }

    @Override
    protected void bindSource(View view) {
        ViewGroup.LayoutParams params = rlLayout.getLayoutParams();
        params.width = (int) (DeviceUtils.getScreenWidth(view.getContext()) * 0.8);
        params.height = (int) (DeviceUtils.getScreenWidth(view.getContext()) * 0.7);
        rlLayout.setLayoutParams(params);
        mVersionName.setText("V" + mUpdateAppInfos.getName() + "新版本");
        mUpdateContent.setText(mUpdateAppInfos.getContent());
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener((dialog, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                return true;
            }
            return false;
        });
    }

    @OnClick({R.id.close_image, R.id.update_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close_image:
                dismiss();
                break;
            case R.id.update_btn:
                view.setVisibility(View.GONE);
                mUpdateProgressLayout.setVisibility(View.VISIBLE);
                downloadApk(mUpdateProgressTv, mUpdateProgressBar);
                break;
        }
    }


    private void downloadApk(final TextView progressTv, final ProgressBar progressBar) {
        String url = mUpdateAppInfos.getUrl();
        String filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "jk" + File.separator;
        int index = url.lastIndexOf("/");
        String fileName = url.substring(index + 1);
        DownloadUtil.download(url, filePath, fileName, new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                install(file);
            }

            @Override
            public void onDownloading(final int progress) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        XZQLog.debug("progress="+progress);
                        progressTv.setText(progress + "%");
                        progressBar.setProgress(progress);
                    }
                });
            }

            @Override
            public void onDownloadFailed(Exception e) {
                XZQLog.debug("onDownloadFailed"+e.getMessage());

            }
        });
    }

    private void install(File file) {


        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            if (Build.VERSION.SDK_INT >= 24) { //适配安卓7.0
                i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri apkFileUri = FileProvider.getUriForFile(getContext().getApplicationContext(), getContext().getPackageName() + ".fileprovider", file);
                i.setDataAndType(apkFileUri, "application/vnd.android.package-archive");
            } else {
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setDataAndType(Uri.parse("file://" + file.toString()), "application/vnd.android.package-archive");// File.toString()会返回路径信息
            }

            try {
                getContext().startActivity(i);


            } catch (ActivityNotFoundException e) {
                MyToast.show("installAPKFile exception:%s"+e.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
