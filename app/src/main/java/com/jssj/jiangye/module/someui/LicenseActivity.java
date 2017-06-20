package com.jssj.jiangye.module.someui;

import android.view.View;
import android.webkit.WebView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.widget.NormalTitleBar;

import butterknife.BindView;

/**
 * Created by cc on 17-5-26.
 */
public class LicenseActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    NormalTitleBar mTitlebar;
    @BindView(R.id.webView)
    WebView        mWebView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_license;
    }

    @Override
    public void initView() {

        mTitlebar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mTitlebar.setTitleText("用户协议");
        //        webView.lo

        mWebView.loadUrl("file:///android_res/raw/license.html");
    }


}
