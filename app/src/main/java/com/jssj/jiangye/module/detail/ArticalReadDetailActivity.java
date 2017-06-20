package com.jssj.jiangye.module.detail;

import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.api.ApiUrl;
import com.jssj.jiangye.api.BaseCallBack;
import com.jssj.jiangye.api.BuilderInstance;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.data.StartsNewsData;
import com.jssj.jiangye.widget.NormalTitleBar;

import java.util.List;

import butterknife.BindView;

/**
 * Created by cc on 17-5-16.
 */
public class ArticalReadDetailActivity extends BaseActivity {


    @BindView(R.id.titlebar)
    NormalTitleBar mTitlebar;
    @BindView(R.id.toolbar)
    Toolbar        mToolbar;
    @BindView(R.id.recycleview)
    XRecyclerView  mRecycleview;
    @BindView(R.id.et_input)
    EditText       mEtInput;
    @BindView(R.id.send_bt)
    Button         mSendBt;
    @BindView(R.id.tv_like)
    ImageView      mTvLike;
    @BindView(R.id.tv_comment_num)
    TextView       mTvCommentNum;
    @BindView(R.id.ll_like)
    RelativeLayout mLlLike;
    @BindView(R.id.iv_share)
    ImageView      mIvShare;
    @BindView(R.id.ll_comment)
    CardView       mLlComment;

    private String id;
    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initView() {
        SetTranslanteBar();

         id = getIntent().getStringExtra(ApiConstants.DETAIL_ACTIVITY);
        mTitlebar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mTitlebar.setLeftTitle("返回");
        mTitlebar.setRightTitleVisibility(true);

         loadData();
    }

    private void loadData() {

        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.STARTS_NEWS_DETAIL)
                .addParams("starNewsId",id)
                .build().execute(new BaseCallBack<List<StartsNewsData>>() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(List<StartsNewsData> response) {

            }

            @Override
            public List<StartsNewsData> parseNetworkResponse(String response) throws Exception {
                return JSON.parseArray(response,StartsNewsData.class);
            }
        });
    }


}
