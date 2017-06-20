package com.jssj.jiangye.api;

import android.text.TextUtils;

import com.jssj.jiangye.utils.UIutils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Response;
import timber.log.Timber;

/**
 * 请求基类。可以屏蔽code.
 */
public abstract class BaseCallBack<T> extends Callback<T> {

    public static final int HTTP_SUCCESS = 1;//错误为-1

    private String message;

    public int getErrorCode() {
        return code;
    }

    private int code;

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String res_value = response.body().string().trim();
        JSONObject object = new JSONObject(res_value);

        code = object.optInt(ApiConstants.HTTP_CODE);
        message = object.optString(ApiConstants.HTTP_MESSAGE);

        Timber.d(response.request().url().toString());

        if (code == HTTP_SUCCESS) {
            String result = object.optString(ApiConstants.HTTP_RESULT);
            Timber.d("...result.." + result);
            //DebugLog.i("...result.." + result);
            return parseNetworkResponse(result);
        } else {
            Timber.d("..code.." + code + "..message.." + message);
            return null;
        }
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        e.printStackTrace();

        onLoadFail();

        if (e instanceof ConnectException) {
            UIutils.SnackbarText("网络连接异常");
            //to.showShort(CartoonApp.getInstance(), "网络连接异常");

        } else if (e instanceof SocketTimeoutException) {
            UIutils.SnackbarText("网络连接超时");
            //ToastUtils.showShort(CartoonApp.getInstance(), "网络连接超时");
        } else if (e instanceof IOException) {
            UIutils.SnackbarText("网络异常");
//            ToastUtils.showShort(CartoonApp.getInstance(), "网络异常");
        } else {
            if (!TextUtils.isEmpty(e.getMessage())) {
                UIutils.SnackbarText(e.getMessage());
//                ToastUtils.showShort(CartoonApp.getInstance(), e.getMessage());
            }
        }
    }

    @Override
    public void onResponse(T response, int id) {

        if (null == response) {
            if (code == 108) {// 108=token失效
              /*  CartoonApp.getInstance().logout();
                CartoonApp.getInstance().startActivity(new Intent(CartoonApp.getInstance(), LoginActivity.class).addFlags(FLAG_ACTIVITY_NEW_TASK));*/
            } if (code == 15) {// 15评论内容为空
                onContentNull();
                return;
            }else {
                /*if (!TextUtils.isEmpty(message)) {
                    if (!message.equals("广告获取失败")) {
                        if (com.cartton.library.BuildConfig.DEBUG) {
                            ToastUtils.showShort(CartoonApp.getInstance(), message);
                        }
                    }
                }*/
            }
            onLoadFail();
        } else {
            onLoadSuccess(response);
//            DebugLog.i("..res.." + t.toString());
        }
    }

    public String getMessage() {
        return message;
    }

    public abstract void onLoadFail();
    public abstract void onContentNull();

    public abstract void onLoadSuccess(T response);

    public abstract T parseNetworkResponse(String response) throws Exception;

}
