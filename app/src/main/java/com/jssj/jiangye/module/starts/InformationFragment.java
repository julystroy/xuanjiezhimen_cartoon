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
import com.jssj.jiangye.data.StartsNewsData;
import com.jssj.jiangye.data.res.StartsNesListResp;
import com.jssj.jiangye.module.adapter.StartsNewsAdapter;
import com.jssj.jiangye.widget.DemoItemDecoration;
import com.jssj.jiangye.widget.MyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cc on 17-5-31.
 */
public class InformationFragment extends BaseFragment implements XRecyclerView.LoadingListener {
    @BindView(R.id.xrecycle_view)
    XRecyclerView mRecyclerView;

    private  StartsNewsAdapter adapter;

    private List<StartsNewsData>  mNewsDataList = new ArrayList<>();
    @Override
    protected int getLayoutResource() {
        return R.layout.str_information;
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
        adapter= new StartsNewsAdapter(mActivity);
        mRecyclerView.setAdapter(adapter);
        findStarNewsList(START_PAGE);
    }

    int time =2;
    private void findStarNewsList(final int start_page) {
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.STARTS_NEWS)
                .addParams("starInfoId","")
                .addParams("newsType",String.valueOf(START_PAGE))//1.明星资讯 2.明星图片
                .addParams(ApiConstants.PAGE,String.valueOf(start_page))
                .addParams(ApiConstants.PAGE_SIZE,ApiConstants.PAGE_COUNT)
                .build().execute(new BaseCallBack<StartsNesListResp>() {
            @Override
            public void onLoadFail() {

                mRecyclerView.refreshComplete();
            }

            @Override
            public void onContentNull() {
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadSuccess(StartsNesListResp response) {
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
            public StartsNesListResp parseNetworkResponse(String response) throws Exception {
                return JSON.parseObject(response,StartsNesListResp.class);
            }
        });
    }


    @Override
    public void onRefresh() {
        findStarNewsList(START_PAGE);
    }

    @Override
    public void onLoadMore() {
        findStarNewsList(time);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.releaseAllHolder(mRecyclerView);
    }
}
