package com.jssj.jiangye.module.main;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by cc on 17-5-15.
 */
public class ActionFragment extends BaseFragment {


    @BindView(R.id.tvtitle_left)
    TextView mTvtitleLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tvtitle_right)
    TextView mTvtitleRight;
    @BindView(R.id.toolbar)
    Toolbar  mToolbar;

    @Override
    protected int getLayoutResource() {
        return R.layout.fg_action;
    }

    @Override
    protected void initView() {
        mTvtitleLeft.setText(R.string.big_event);
        mTvTitle.setText(R.string.tab_action);
        mTvtitleRight.setVisibility(View.INVISIBLE);
    }



}
