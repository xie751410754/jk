package com.plinkdt.jk.main.personal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.plinkdt.jk.R;
import com.plinkdt.jk.YqApp;
import com.plinkdt.jk.main.LoginActivity;
import com.plinkdt.jk.main.SettingProxy;
import com.xzq.module_base.base.BaseActivity;
import com.xzq.module_base.eventbus.EventAction;
import com.xzq.module_base.eventbus.EventUtil;
import com.xzq.module_base.eventbus.MessageEvent;
import com.xzq.module_base.utils.XZQLog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class WebActivity extends BaseActivity {



    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.webview)
    WebView mWebView;

    String titleName= null;

    public static void start(Context context,String url,String titleName) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra("workUrl",url);
        starter.putExtra("title",titleName);
        context.startActivity(starter);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {

        String workUrl = getIntent().getStringExtra("workUrl");
        String titleName = getIntent().getStringExtra("title");
        setToolbar(titleName);
        XZQLog.debug("work =" + workUrl);

        WebSettings settings = mWebView.getSettings();
        //设置WebView能够执行Js脚本文件
        settings.setJavaScriptEnabled(true);
        //设置WebView自适应网页大小
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //禁止WebView可以使用文件
        settings.setAllowFileAccess(false);
        //设置禁止网页缩放
        settings.setBuiltInZoomControls(false);
        //禁止HTML%的地理位置服务
        settings.setGeolocationEnabled(false);
        //设置DOM Storage缓存false可能页面加载不出来
        settings.setDomStorageEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
//        SettingProxy.setProxy(mWebView,"https://39.130.147.131",4431, "com.plinkdt.jk.YqApp");
        mWebView.loadUrl(workUrl);
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            view.removeJavascriptInterface("accessibility");
            view.removeJavascriptInterface("accessibilityTraversal");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("TAG", "isFinish");
//            tv_title.setText(view.getTitle());
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (request.isForMainFrame()) {
                //自定义错误页面
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return;
            }
            //自定义错误页面
        }
    }


    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Log.d("TAG", "newProgress" + newProgress);
            if (mProgressBar != null) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (mProgressBar.getVisibility() == View.GONE) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
            }
        }

    }


}
