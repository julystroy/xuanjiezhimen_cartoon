package com.jssj.jiangye.module.someui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
import com.jssj.jiangye.data.NovelChapterList;
import com.jssj.jiangye.data.res.NovelChapterRes;
import com.jssj.jiangye.module.adapter.NovelChapterListAdapter;
import com.jssj.jiangye.utils.ToastUitl;
import com.jssj.jiangye.widget.MyLinearLayoutManager;
import com.jssj.jiangye.widget.NormalTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cc on 17-6-13.
 */
public class ReadNovelListActivity extends BaseActivity implements XRecyclerView.LoadingListener {
    @BindView(R.id.titlebar)
    NormalTitleBar mTitlebar;
    @BindView(R.id.toolbar)
    Toolbar        mToolbar;
    @BindView(R.id.recycleview)
    XRecyclerView  mRecyclerView;

    private NovelChapterListAdapter adapter;
    private List<NovelChapterList>  mList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_novellist;
    }

    @Override
    public void initView() {

        mTitlebar.setTitleText("目录");
        mTitlebar.setTvLeft("返回");
        mTitlebar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mTitlebar.setRightTitleVisibility(true);
        mTitlebar.setRightTitle("正序");
        mTitlebar.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUitl.showLong("排序");
            }
        });

        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mRecyclerView.setLoadingListener(this);
        adapter = new NovelChapterListAdapter(this);
        mRecyclerView.setAdapter(adapter);

        loadData(START_PAGE);
    }

    private int time=2;
    private void loadData(final int start) {

        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.READ_LIST)
                .addParams(ApiConstants.MOBILE, BaseApplication.getAppContext().getLoginUser().getMobile())
                .addParams(ApiConstants.PAGE,String.valueOf(start))
                .addParams(ApiConstants.PAGE_SIZE,ApiConstants.PAGE_COUNT)
                .build().execute(new BaseCallBack<NovelChapterRes>() {
            @Override
            public void onLoadFail() {
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(NovelChapterRes response) {
                mRecyclerView.refreshComplete();
                if (response==null)
                    return;
                if (start == START_PAGE) {
                    if (mList != null) {
                        mList.clear();
                    }
                    mList.addAll(response.getData());
                    adapter.addData(mList);
                } else {
                    ++time;
                    mList.addAll(response.getData());
                    adapter.addData(response.getData());
                }


            }

            @Override
            public NovelChapterRes parseNetworkResponse(String response) throws Exception {
                return JSON.parseObject(response,NovelChapterRes.class);
            }
        });

    }


    @Override
    public void onRefresh() {
        loadData(START_PAGE);
    }

    @Override
    public void onLoadMore() {
        loadData(time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.releaseAllHolder(mRecyclerView);
    }
}
