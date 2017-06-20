package com.jssj.jiangye.base;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jssj.jiangye.module.manager.AppManager;
import com.jssj.jiangye.BaseApplication;
import com.jssj.jiangye.BuildConfig;
import com.jssj.jiangye.R;
import com.jssj.jiangye.widget.StatusBarCompat;
import com.jssj.jiangye.widget.swipbackhelper.SwipeBackHelper;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Context  mContext;
    private   Unbinder mUnbinder;
    public int START_PAGE =1;
        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();

            if (useEventBus())//如果要使用eventbus请将此方法返回true
                EventBus.getDefault().register(this);//注册到事件主线
            setContentView(getLayoutId());
            initPermission();
            SwipeBackHelper.onCreate(this);//滑动退出
            //绑定到butterknife
            mUnbinder = ButterKnife.bind(this);
        ButterKnife.bind(this);


        mContext = this;

        this.initView();
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }
    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        /*// 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);*/
        // 默认着色状态栏
        SetStatusBarColor();

    }
    /*********************子类实现*****************************/
    //获取布局文件
    public abstract int getLayoutId();
    //初始化view
    public abstract void initView();


    public boolean isLogin(){
        return BaseApplication.getAppContext().getLoginUser().getMobile()!=null?true:false;
    }
    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(){
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this,R.color.main_color));
    }
    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color){
        StatusBarCompat.setStatusBarColor(this,color);
    }
    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar(){
        StatusBarCompat.translucentStatusBar(this);
    }


//visible or gon
    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }
   /* *//**
     * 开启浮动加载进度条
     *//*
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(this);
    }

    *//**
     * 开启浮动加载进度条
     *
     * @param msg
     *//*
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    *//**
     * 停止浮动加载进度条
     *//*
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    *//**
     * 短暂显示Toast提示(来自String)
     **//*
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    *//**
     * 短暂显示Toast提示(id)
     **//*
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    *//**
     * 长时间显示Toast提示(来自res)
     **//*
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    *//**
     * 长时间显示Toast提示(来自String)
     **//*
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }
    *//**
     * 带图片的toast
     * @param text
     * @param res
     *//*
    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text,res);
    }
    *//**
     * 网络访问错误提醒
     *//*
    public void showNetErrorTip() {
        ToastUitl.showToastWithImg(getText(R.string.net_error).toString(),R.mipmap.ic_wifi_off);
    }
    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error,R.mipmap.ic_wifi_off);
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        //debug版本不统计crash
        if(!BuildConfig.LOG_DEBUG) {
            //友盟统计
           // MobclickAgent.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //debug版本不统计crash
        if(!BuildConfig.LOG_DEBUG) {
            //友盟统计
          //  MobclickAgent.onPause(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().unregister(this);
        SwipeBackHelper.onDestroy(this);
        AppManager.getAppManager().finishActivity(this);

    }

    /**
     * 是否使用eventBus,默认为使用(false)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }


    //退出activity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    private static final int REQUEST_PERMISSION_SDCARD_CODE = 1;
    private void initPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_SDCARD_CODE);
            }
        }
    }
}
