package com.jssj.jiangye.module.main.mine;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jssj.jiangye.BaseApplication;
import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.api.ApiUrl;
import com.jssj.jiangye.api.BaseCallBack;
import com.jssj.jiangye.api.BuilderInstance;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.data.StoreData;
import com.jssj.jiangye.data.res.StoreDataRes;
import com.jssj.jiangye.module.adapter.StoreAdapter;
import com.jssj.jiangye.widget.NormalTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cc on 17-6-14.
 */
public class StoreActivity extends BaseActivity implements XRecyclerView.LoadingListener {
    @BindView(R.id.titlebar)
    NormalTitleBar mTitlebar;
    @BindView(R.id.toolbar)
    Toolbar        mToolbar;
    @BindView(R.id.money_count)
    TextView       mMoneyCount;
    @BindView(R.id.modle_count)
    TextView       mModleCount;
    @BindView(R.id.recycle_view)
    XRecyclerView  mRecyclerView;

    private StoreAdapter adapter;
    private List<StoreData>  mList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_store;
    }

    @Override
    public void initView() {

        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);

        mRecyclerView.setLoadingListener(this);
         adapter = new StoreAdapter(StoreActivity.this);
        mRecyclerView.setAdapter(adapter);
        loadData(START_PAGE);
    }

    int time =2;
    private void loadData(final int page) {
        String id = BaseApplication.getAppContext().getLoginUser().getId();
        id = "4a59f5abcdf7479e9154d42047ff882a";
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.STORELIST)
                .addParams("userId", id)
                .addParams(ApiConstants.PAGE,page+"")
                .addParams(ApiConstants.PAGE_SIZE,ApiConstants.PAGE_COUNT)
                .build().execute(new BaseCallBack<StoreDataRes>() {
            @Override
            public void onLoadFail() {
                if (mRecyclerView!=null)mRecyclerView.refreshComplete();
            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(StoreDataRes response) {
                time++;
                mRecyclerView.refreshComplete();
                if (response != null) {
                    return;
                }
                if (page == START_PAGE) {
                    if (mList != null) {
                        mList.clear();
                    }
                    mList.addAll(response.getData());
                    adapter.addData(mList);
                } else {
                    mList.addAll(response.getData());
                    adapter.addData(mList);
                }
            }

            @Override
            public StoreDataRes parseNetworkResponse(String response) throws Exception {
                return JSON.parseObject(response,StoreDataRes.class);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.releaseAllHolder(mRecyclerView);
    }

    @Override
    public void onRefresh() {
        loadData(START_PAGE);
    }

    @Override
    public void onLoadMore() {
        loadData(time);
    }
}
