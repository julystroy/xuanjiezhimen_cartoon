package com.jssj.jiangye.module.someui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jssj.jiangye.BaseApplication;
import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.api.ApiUrl;
import com.jssj.jiangye.api.BaseCallBack;
import com.jssj.jiangye.api.BuilderInstance;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.data.UserInfo;
import com.jssj.jiangye.module.user.NameEditActivity;
import com.jssj.jiangye.utils.CodeUtil;
import com.jssj.jiangye.utils.ToastUitl;
import com.jssj.jiangye.widget.SwipeableLayout;
import com.jssj.jiangye.widget.swipbackhelper.SwipeBackHelper;

import butterknife.BindView;

/**
 * Created by cc on 17-5-26.
 */
public class LoginActivity extends BaseActivity implements  View.OnClickListener {
    @BindView(R.id.et_name)
    EditText       etName;
    @BindView(R.id.et_pwd)
    EditText       etPwd;
    @BindView(R.id.bt_login)
    Button         btLogin;
    @BindView(R.id.bt_code)
    Button         btCode;
    @BindView(R.id.tv_accept)
    TextView       tvAccept;
    @BindView(R.id.et_picCodes)
    EditText       etPicCodes;
    @BindView(R.id.iv_showCode)
    ImageView      ivShowCode;
    @BindView(R.id.select_point)
    ImageView selectPoint;

    @BindView(R.id.pullback)
    SwipeableLayout pullback;

    /**
     * 是否开启倒计时
     */
    boolean isCutStart = false;
    boolean isSelected = true;
    private MyCount myCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        tvAccept.setText(Html.fromHtml("<font color=#8aa0cc>《将夜用户协议》</font>"));
        selectPoint.setOnClickListener(this);
        selectPoint.setSelected(isSelected);


        myCount = new MyCount(60 * 1000, 1000);
        // 将验证码用图片的形式显示出来
        ivShowCode.setImageBitmap(CodeUtil.getInstance().createBitmap());
        btLogin.setOnClickListener(this);
        tvAccept.setOnClickListener(this);
        ivShowCode.setOnClickListener(this);
        btCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = etName.getText().toString().trim();
                if (!isPhoneNumberCorrect(mobile)) {
                    ToastUitl.showLong( "手机号不正确");
                } else {
                    getMSCode(mobile);
                }
            }
        });
        pullback.setOnLayoutCloseListener(new SwipeableLayout.OnLayoutCloseListener() {
            @Override
            public void OnLayoutClosed() {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:

                String realCode = CodeUtil.getInstance().getCode();
                String picCodesEt = etPicCodes.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String code = etPwd.getText().toString().trim();
                if (!isPhoneNumberCorrect(name)) {
                    ToastUitl.showLong( "手机号不正确");
                } else if (TextUtils.isEmpty(code)) {
                    ToastUitl.showLong(  "请输入验证码");
                } else if (TextUtils.isEmpty(picCodesEt)) {
                    ToastUitl.showLong( "请输入图片验证码");
                } else if (!picCodesEt.equalsIgnoreCase(realCode)) {
                    ToastUitl.showLong(  "图片验证码错误");
                } else if (!isSelected){
                    ToastUitl.showLong( "请选中玄界之门官方APP协议");
                }else {

                    login(name,code,picCodesEt);
                }
                break;
            case R.id.tv_accept:
                startActivity(new Intent(this, LicenseActivity.class));
                break;
            case R.id.iv_showCode:
                ivShowCode.setImageBitmap(CodeUtil.getInstance().createBitmap());
                break;
            case R.id.select_point:
                selectPoint.setSelected(!isSelected);
                isSelected = !isSelected;
                break;
        }
    }



    private boolean isPhoneNumberCorrect(String pPhoneNumber) {
        //
        //        Pattern pattern = Pattern
        //                .compile("^((17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        //        Matcher matcher = pattern.matcher(pPhoneNumber);
        //
        //        if (matcher.matches())
        //            return true;
        if (pPhoneNumber.length() == 11) return true;
        return false;
    }

    /* 定义一个倒计时的内部类 */
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            isCutStart = false;
            btCode.setText(R.string.repat_verification);
            btCode.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btCode.setEnabled(false);
            btCode.setText(millisUntilFinished / 1000 + "s");
        }

    }


    /*
       * 获取短信验证码
       */
    private void getMSCode(String mobile) {
        //showLoadingView();
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.SENDCODE)
                .addParams("mobile", mobile)
                .build().execute(new BaseCallBack() {
            @Override
            public void onLoadFail() {
                //  hideLoadingView();
                if (getErrorCode() == 33 || getErrorCode() == 22) {// 超过频率，同一个手机号同一验证码模板每30秒只能发送一条
                    ToastUitl.showShort("验证频率过高，请稍后再试");
                }
            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(Object response) {
                // hideLoadingView();
                myCount.start();
                if (null != etPwd) etPwd.requestFocus();
                ToastUitl.showShort("已发送验证码");
            }

            @Override
            public Object parseNetworkResponse(String response) throws Exception {

                return response;
            }
        });
    }


    //登录
    private   void login(String mobile, String authCode,String imgVerifyCode) {
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.LOGIN)
                .addParams("mobile", mobile)
                .addParams(ApiConstants.UNIQUEID, BaseApplication.getAppContext().getDeviceId() )
                .addParams(ApiConstants.DEVICETYPE, "1")
                .addParams("authCode", authCode)
                .addParams("imgVerifyCode", imgVerifyCode)
                .build().execute(new BaseCallBack<UserInfo>() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(UserInfo response) {


                if (response != null) {

                    BaseApplication.getAppContext().updateUserInfo(response);
                }

                if (TextUtils.equals(response.isLogin(), "1")) {
                    Intent intent = new Intent(LoginActivity.this, NameEditActivity.class);
                    intent.putExtra(ApiConstants.FIRST_LOGIN,true);
                    startActivity(intent);
                }
                onBackPressed();

            }

            @Override
            public UserInfo parseNetworkResponse(String response) throws Exception {
                return JSON.parseObject(response, UserInfo.class);
            }
        });
    }
}
