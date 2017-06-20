package com.jssj.jiangye.module.someui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.jssj.jiangye.BaseApplication;
import com.jssj.jiangye.R;
import com.jssj.jiangye.api.RequestMethod;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.module.MainActivity;

import butterknife.BindView;

/**
 * Created by cc on 17-6-2.
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.tvSkip)
    TextView tvSkip;

    private boolean flag = false;
    private Runnable runnable;
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

        if (BaseApplication.getAppContext().getLoginUser().getToken()==null) {//预注册判断
            RequestMethod.PreRegister();
        }


        runnable = new Runnable() {
            @Override
            public void run() {
                goHome();
            }
        };

        tvSkip.postDelayed(runnable, 2000);

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });



    }

    private synchronized void goHome() {
        if (!flag) {
            flag = true;
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            tvSkip.removeCallbacks(runnable);
            onBackPressed();
        }
    }

}
