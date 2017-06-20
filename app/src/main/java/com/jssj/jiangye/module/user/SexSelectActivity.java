package com.jssj.jiangye.module.user;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.api.RequestMethod;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.utils.ToastUitl;
import com.jssj.jiangye.widget.NormalTitleBar;

import butterknife.BindView;

/**
 * Created by cc on 17-6-2.
 */
public class SexSelectActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.titlebar)
    NormalTitleBar mTitlebar;
    @BindView(R.id.select_boy)
    CheckBox       mSelectBoy;
    @BindView(R.id.select_girl)
    CheckBox       mSelectGirl;
    @BindView(R.id.tv_subscrib)
    TextView       mTvSubscrib;

    private  String name;
    @Override
    public int getLayoutId() {
        return R.layout.activty_select;
    }

    @Override
    public void initView() {

         name = getIntent().getStringExtra(ApiConstants.NICKNAME);
        mTitlebar.setTvLeft("返回");
        mTitlebar.setOnBackListener(this);
        mSelectBoy.setOnClickListener(this);
        mSelectGirl.setOnClickListener(this);
        mTvSubscrib.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ToastUitl.showLong("请完善个人资料");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

int num;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar:
                onBackPressed();
                break;
            case R.id.select_boy:
                num=0;
                mSelectGirl.setChecked(false);
                break;
            case R.id.select_girl:
                num=1;
                mSelectBoy.setChecked(false);
                break;
            case R.id.tv_subscrib:
                if (!TextUtils.isEmpty(name))
                RequestMethod.UpdataUseinfo(num,name);
                break;
        }
    }
}
