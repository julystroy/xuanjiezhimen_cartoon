package com.jssj.jiangye.module.main.mine.mymessage;

import android.support.v7.widget.Toolbar;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.widget.NormalTitleBar;

import butterknife.BindView;

/**
 * Created by cc on 17-6-12.
 */
public class MyMessageActivity extends BaseActivity {
    @BindView(R.id.titlebar)
    NormalTitleBar mTitlebar;
    @BindView(R.id.toolbar)
    Toolbar        mToolbar;
    @BindView(R.id.recycleview)
    XRecyclerView  mRecycleview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mymessage;
    }

    @Override
    public void initView() {
        mTitlebar.setTitleText("我的消息");
        mTitlebar.setLeftTitle("返回");
    }

}
