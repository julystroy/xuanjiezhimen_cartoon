package com.jssj.jiangye.module.starts;

import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.api.ApiUrl;
import com.jssj.jiangye.api.BaseCallBack;
import com.jssj.jiangye.api.BuilderInstance;
import com.jssj.jiangye.base.BaseFragment;
import com.jssj.jiangye.data.StartsHotData;
import com.jssj.jiangye.data.res.StartsHotDataResp;
import com.jssj.jiangye.module.adapter.StartsHotAdapter;
import com.jssj.jiangye.widget.DemoItemDecoration;
import com.jssj.jiangye.widget.MyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cc on 17-5-31.
 */
public class CharmFragment extends BaseFragment implements XRecyclerView.LoadingListener {
    @BindView(R.id.xrecycle_view)
    XRecyclerView mRecyclerView;

    private StartsHotAdapter adapter;

    private List<StartsHotData> mNewsDataList = new ArrayList<>();
    @Override
    protected int getLayoutResource() {
        return R.layout.str_charm;
    }

    @Override
    protected void initView() {
        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        mRecyclerView.addItemDecoration(new DemoItemDecoration(mActivity));
        mRecyclerView.setLoadingListener(this);
        adapter= new StartsHotAdapter(mActivity);
        mRecyclerView.setAdapter(adapter);
        findStarHoteList(START_PAGE);
    }
    int time =2;
    private void findStarHoteList(final int start_page) {
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.STARTS_HOTS)
                .addParams(ApiConstants.PAGE,String.valueOf(start_page))
                .addParams(ApiConstants.PAGE_SIZE,ApiConstants.PAGE_COUNT)
                .build().execute(new BaseCallBack<StartsHotDataResp>() {
            @Override
            public void onLoadFail() {

                mRecyclerView.refreshComplete();
            }

            @Override
            public void onContentNull() {
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadSuccess(StartsHotDataResp response) {
                mRecyclerView.refreshComplete();
                if (start_page == START_PAGE) {
                    if (mNewsDataList != null) {
                        mNewsDataList.clear();
                    }
                    mNewsDataList.addAll(response.getData());
                    adapter.addData(mNewsDataList);
                } else {
                    time++;
                    mNewsDataList.addAll(response.getData());
                    adapter.addData(mNewsDataList);
                }
            }

            @Override
            public StartsHotDataResp parseNetworkResponse(String response) throws Exception {
                return JSON.parseObject(response,StartsHotDataResp.class);
            }
        });
    }


    @Override
    public void onRefresh() {
        findStarHoteList(START_PAGE);
    }

    @Override
    public void onLoadMore() {
        findStarHoteList(time);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.releaseAllHolder(mRecyclerView);
    }
}
