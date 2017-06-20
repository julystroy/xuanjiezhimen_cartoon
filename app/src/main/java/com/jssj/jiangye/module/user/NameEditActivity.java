package com.jssj.jiangye.module.user;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.utils.StringUtils;
import com.jssj.jiangye.utils.ToastUitl;
import com.jssj.jiangye.widget.NormalTitleBar;

import butterknife.BindView;

/**
 * Created by cc on 17-5-25.
 */
public class NameEditActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.titlebar)
    NormalTitleBar mTitlebar;
    @BindView(R.id.edit_name)
    EditText       mEditName;
    @BindView(R.id.tv_subscrib)
    TextView       mTvSubscrib;

    private boolean booleanExtra;
    @Override
    public int getLayoutId() {
        return R.layout.activity_name_edit;
    }

    @Override
    public void initView() {
         booleanExtra = getIntent().getBooleanExtra(ApiConstants.FIRST_LOGIN, false);
        if (booleanExtra) {
            mTitlebar.setBackVisibility(false);
            mTvSubscrib.setText("下一步");
        }
        mTitlebar.setLeftTitle("返回");
        mTvSubscrib.setText("提交");
        mTvSubscrib.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar:
                String name = mEditName.getText().toString();
                if (StringUtils.isAllChinese(name)) {
                    checkName(name);
                }else
                    ToastUitl.showLong("昵称含有非汉字字符");
                break;
        }
    }

    //// FIXME: 17-6-6 查询名字是否重复
    private void checkName(String name) {

        //不重复的话
        if (booleanExtra) {
            Intent intent = new Intent(NameEditActivity.this, SexSelectActivity.class);
            intent.putExtra(ApiConstants.NICKNAME,name);
            startActivity(intent);
        }
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (booleanExtra) {
                ToastUitl.showLong("请完善个人资料");
                return true;
            }else
                onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
