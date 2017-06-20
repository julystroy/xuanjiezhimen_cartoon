package com.jssj.jiangye;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.telephony.TelephonyManager;

import com.jssj.jiangye.data.UserInfo;
import com.jssj.jiangye.utils.NetWorkUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by cc on 17-5-8.
 */
public class BaseApplication extends Application {
    private RefWatcher mRefWatcher;//leakCanary观察器
    //读超时长，单位：毫秒
    public static final int    READ_TIME_OUT    = 7676;
    //连接时长，单位：毫秒
    public static final int    CONNECT_TIME_OUT = 7676;


    private        CacheControl    noCacheControl;

    private static BaseApplication baseApplication;


    @Override
    public void onCreate() {
        super.onCreate();

        baseApplication = this;
        api();
        CacheControl.Builder builder = new CacheControl.Builder();
        noCacheControl = builder.noCache().noStore().build();//选择器，不缓存设置
        if (BuildConfig.LOG_DEBUG) {//Timber日志打印
            Timber.plant(new Timber.DebugTree());
        }

        installLeakCanary();//leakCanary内存泄露检查
    }



    public static BaseApplication getAppContext() {
        return baseApplication;
    }

    public static Resources getAppResources() {
        return baseApplication.getResources();
    }


    //构造方法私有
    private void api() {

        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                .cache(cache)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (baseApplication != null)
            this.baseApplication = null;
        if (mRefWatcher != null)
            this.mRefWatcher = null;
    }

    /**
     * 安装leakCanary检测内存泄露
     */
    protected void installLeakCanary() {
        this.mRefWatcher = BuildConfig.USE_CANARY ? LeakCanary.install(this) : RefWatcher.DISABLED;
    }




    /**
     * 获得leakCanary观察器
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        return baseApplication.mRefWatcher;
    }


    //拦截器
    private  Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request.newBuilder().header("laojiang","aaaaaa");
            boolean networkOnLine = NetWorkUtils.isNetConnected(getApplicationContext());

            String apiUrl = request.url().url().toString();
            if (apiUrl.contains("StaticField.URL_NEXT_QUESTION")
                    /*|| apiUrl.contains(StaticField.URL_DAY_QUESTION_GET_ANSWER)
                    || apiUrl.contains(StaticField.URL_DAY_QUESTION_list)*/) {
                request = request.newBuilder()
                        .cacheControl(noCacheControl)
                        .build();
            } else {
                if (!networkOnLine) {
                    // have no network
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                } else {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_NETWORK)
                            .build();
                }
            }
            Response response = chain.proceed(request);
            if (networkOnLine) {
                int maxAge = 0; // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                //                maxStale = cacheOnlyGetMethod ? 0 : maxStale;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }

            return response;
        }
    };
    /**
     * 获取设备唯一标识
     * ben  ce  shi  ji  866373029778994
     */

    public String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String device_token = tm.getDeviceId();
        return device_token;
    }


    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }
    /**
     * 保存登录信息
     *
     * @param user 用户信息
     */
    @SuppressWarnings("serial")
    public void saveUserInfo(final UserInfo user) {
        setProperties(new Properties() {
            {
                setProperty("user.uid", String.valueOf(user.getId()));
                if (user.getNickName()!=null)
                setProperty("user.name", user.getNickName());
                if (user.getPortraitUrl()!=null)
                setProperty("user.face", user.getPortraitUrl());// 用户头像-文件名
                setProperty("user.pwd", user.getToken());
                if (user.getMobile()!=null)
                setProperty("user.mobile", user.getMobile());
                setProperty("user.gender", String.valueOf(user.getSex()));

            }
        });
    }


    /**
     * 更新用户信息
     *
     * @param user
     */
    @SuppressWarnings("serial")
    public void updateUserInfo(final UserInfo user) {
        setProperties(new Properties() {
            {
                setProperty("user.name", user.getNickName());
                setProperty("user.face", user.getPortraitUrl());// 用户头像-文件名
                setProperty("user.gender", String.valueOf(user.getSex()));
                setProperty("user.mobile", user.getMobile());
            }
        });
    }


    /**
     * 获取cookie时传AppConfig.CONF_COOKIE
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        String res = AppConfig.getAppConfig(this).get(key);
        return res;
    }
    /**
     * 获得登录用户的信息
     *
     * @return
     */
    public UserInfo getLoginUser() {
        UserInfo user = new UserInfo();
        user.setId(getProperty("user.uid"));
        user.setNickName(getProperty("user.name"));
        user.setPortraitUrl(getProperty("user.face"));
        user.setSex(getProperty("user.gender"));
        user.setToken(getProperty("user.pwd"));
        user.setMobile(getProperty("user.mobile"));
        return user;
    }

    /**
     * 清除登录信息
     */
    public void cleanLoginInfo() {
        removeProperty("user.uid", "user.name", "user.face",
                 "user.gender","user.pwd","user.mobile");
    }
    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

}
