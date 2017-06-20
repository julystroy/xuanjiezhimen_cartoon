package com.jssj.jiangye.module.common;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.widget.NormalTitleBar;

import butterknife.BindView;

/**
 * Created by cc on 17-6-6.
 */
public class CommentActivity extends BaseActivity {
    @BindView(R.id.tv_desc)
    TextView          mTvDesc;
    @BindView(R.id.tv_hot)
    TextView          mTvHot;
    @BindView(R.id.tv_new)
    TextView          mTvNew;
    @BindView(R.id.rl_summary)
    RelativeLayout    mRlSummary;
    @BindView(R.id.toolbar)
    Toolbar           mToolbar;
    @BindView(R.id.recycle_view)
    XRecyclerView     mRecycleView;
    @BindView(R.id.et_input)
    EditText          mEtInput;
    @BindView(R.id.send_bt)
    Button            mSendBt;
    @BindView(R.id.tv_like)
    ImageView         mTvLike;
    @BindView(R.id.tv_comment_num)
    TextView          mTvCommentNum;
    @BindView(R.id.ll_like)
    RelativeLayout    mLlLike;
    @BindView(R.id.iv_share)
    ImageView         mIvShare;
    @BindView(R.id.ll_cartoon)
    CoordinatorLayout mLlCartoon;
    @BindView(R.id.titlebar)
    NormalTitleBar    mTitlebar;

    @Override
    public int getLayoutId() {
        return R.layout.ac_comment;
    }

    @Override
    public void initView() {
        mTitlebar.setTitleText("评论");
    }

}
