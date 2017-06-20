package com.jssj.jiangye.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.jssj.jiangye.module.someui.LoginActivity;
import com.jssj.jiangye.module.photo.ViewBigImageActivity;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by cc on 17-5-9.
 */
public class UIutils {

    public static final int    SHOW          = 0;
    public static final int    HIDE          = 1;
    public static final int    SHOW_SNACKBAR = 2;
    public static final String APP_MESSAGE   = "app_message";
    public static final String FOLAT         = "hide_show";


    /**
     * 用snackbar显示
     *
     * @param text
     */
    public static void SnackbarText(String text) {
        Message message = new Message();
        message.what = SHOW_SNACKBAR;
        message.obj = text;
        message.arg1 = 0;
        EventBus.getDefault().post(message, APP_MESSAGE);
    }

    /**
     * 用snackbar长时间显示
     *
     * @param text
     */
    public static void SnackbarTextWithLong(String text) {
        Message message = new Message();
        message.what = SHOW_SNACKBAR;
        message.obj = text;
        message.arg1 = 1;
        EventBus.getDefault().post(message, APP_MESSAGE);
    }

    /*
    * float  show or hide
    * */
    public static void FloatShow() {
        Message message = new Message();
        message.what = SHOW;
        EventBus.getDefault().post(message, FOLAT);
    }

    public static void FloatHide() {
        Message message = new Message();
        message.what = HIDE;
        EventBus.getDefault().post(message, FOLAT);
    }

    /*
    * 重新请求数据
    * */
    public static void RefreshData(int type, String str) {
        Message message = new Message();
        message.what = type;
        EventBus.getDefault().post(message, str);
    }

    /*
    跳转登录界面
    * */
    public static void StartLoginActivity(Context context){
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    /*
    *跳转到imageactivity
    */
    public static void startImageActivity(Context context, int position, ArrayList<String> imgList){
        Bundle bundle = new Bundle();
        bundle.putInt("selet", 1);// 2,大图显示当前页数，1,头像，不显示页数
        bundle.putInt("code", position);//第几张
        bundle.putStringArrayList("imageuri", imgList);
        Intent intent = new Intent(context, ViewBigImageActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
