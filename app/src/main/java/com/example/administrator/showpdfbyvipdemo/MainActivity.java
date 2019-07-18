package com.example.administrator.showpdfbyvipdemo;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;

/**
 * ================================================
 *
 * @author :Vip
 * @version :V 1.0.0
 * @date :2019/7/18 11:26
 * 描    述：在线展示Pdf
 * 修订历史：
 * ================================================
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 初始
     **/
    private ExtendedWebView extendedWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化
     **/
    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        extendedWebView = findViewById(R.id.view_web);
        extendedWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = extendedWebView.getSettings();
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setBuiltInZoomControls(true);
        extendedWebView.requestDisallowInterceptTouchEvent(true);
        String mPdfFilePath = "http://114.115.152.132:80/lessonDoc/1540185667975.pdf";
        extendedWebView.setWebChromeClient(new WebChromeClient());
        noStyle(mPdfFilePath);
    }

    /**
     * 有样式（含有下载翻页等快捷键）
     **/
    private void haveStyle(String mPdfFilePath) {
        if (!"".equals(mPdfFilePath)) {
            byte[] bytes = null;
            try {// 获取以字符编码为utf-8的字符
                bytes = mPdfFilePath.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (bytes != null) {
                // BASE64转码
                mPdfFilePath = new Base64Encoder().encode(bytes);
            }
            //TODO 采用有样式的，则引用 viewer.html?file=,需要mPdfFilePath转码
            extendedWebView.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file=" + mPdfFilePath);

        }
    }

    /**
     * 无样式（满足大部分业务需求。样式简洁美观）
     **/
    private void noStyle(String mPdfFilePath) {
        //TODO 采用无样式的，则引用index.html?不需要file=，并且mPdfFilePath不需要转码。
        extendedWebView.loadUrl("file:///android_asset/pdfjs/web/index.html?" + mPdfFilePath);
    }
}
