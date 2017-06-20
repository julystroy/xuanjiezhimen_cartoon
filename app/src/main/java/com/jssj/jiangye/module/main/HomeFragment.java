package com.jssj.jiangye.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jssj.jiangye.BaseApplication;
import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.api.ApiUrl;
import com.jssj.jiangye.api.BaseCallBack;
import com.jssj.jiangye.api.BuilderInstance;
import com.jssj.jiangye.base.BaseFragment;
import com.jssj.jiangye.data.HomeRecord;
import com.jssj.jiangye.data.SlideShowData;
import com.jssj.jiangye.data.SysModule;
import com.jssj.jiangye.data.UnreadChapterCount;
import com.jssj.jiangye.data.res.HomeRecordList;
import com.jssj.jiangye.module.adapter.HomeAdapter;
import com.jssj.jiangye.module.someui.ReadNovelListActivity;
import com.jssj.jiangye.utils.DisplayUtil;
import com.jssj.jiangye.utils.ToastUitl;
import com.jssj.jiangye.utils.download.DownloadManager;
import com.jssj.jiangye.utils.download.DownloadTask;
import com.jssj.jiangye.utils.download.DownloadTaskListener;
import com.jssj.jiangye.widget.MyLinearLayoutManager;
import com.jssj.jiangye.widget.SelectionLayout;
import com.jssj.jiangye.widget.banner.Banner;
import com.jssj.jiangye.widget.banner.GlideImageLoader;
import com.jssj.jiangye.widget.banner.loader.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cc on 17-5-15.
 */
public class HomeFragment extends BaseFragment implements SelectionLayout.OnSelectListener, OnBannerListener, XRecyclerView.LoadingListener, View.OnClickListener {

    @BindView(R.id.slOverall)
    SelectionLayout slOverall;
    @BindView(R.id.toolbar)
    Toolbar       mToolbar;
    @BindView(R.id.tv_title)
    AppBarLayout  mTvTitle;
    @BindView(R.id.recycleview)
    XRecyclerView mRecyclerView;
    private Banner banner;
    boolean isRefresh =false;

    protected List<SysModule> list = new ArrayList() ;
    int time =1;//记录刷新次数
    private List<HomeRecord> mList = new ArrayList<>();
    private DownloadManager downloadManager;
    private HomeAdapter adapter;
    @Override
    protected int getLayoutResource() {
        return R.layout.fg_home;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadTitle();//加载下拉模块标题
        if (isLogin())
            loadUnreadCount();//获取未读章节

       // downloadManager = DownloadManager.getInstance(mActivity);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }
    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
    @Override
    protected void initView() {
        mToolbar.setTitle("将夜");
        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        View header = LayoutInflater.from(mActivity).inflate(R.layout.header, null);
        banner = (Banner) header.findViewById(R.id.banner);
        banner.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.getScreenHeight(mActivity) / 3));
        mRecyclerView.addHeaderView(banner);

        FindSlideShow();//加载轮播图


        mRecyclerView.setLoadingListener(this);
        adapter = new HomeAdapter(mActivity);
        adapter.setOnClickButViewListener(this);
        mRecyclerView.setAdapter(adapter);
       // mRecyclerView.refresh();

        loadList(START_PAGE,"");
    }


    //下拉标题点击回调
    @Override
    public void OnBannerClick(SlideShowData position) {
        ToastUitl.showLong(position.getAdverImageUrl());
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        banner.releaseBanner();
    }

    String businessCode ="";
    //下拉菜单选择
    @Override
    public void onSelect( int position, SysModule title) {
        businessCode = title.getBusinessCode();
        loadList(START_PAGE,businessCode);
    }

    @Override
    public void onRefresh() {
       loadList(START_PAGE,"");
        if (!isRefresh) {
            FindSlideShow();
            loadTitle();//no net refrash
            isRefresh=true;
        }
    }

    @Override
    public void onLoadMore() {
        loadList(time,businessCode);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.novel_download:
                ToastUitl.showLong("下载");
                download(ApiUrl.READ_LIST);
                break;
            case R.id.novel_read:
                ToastUitl.showLong("阅读");

                break;
            case R.id.novel_list:
                ToastUitl.showLong("目录");
                startActivity(new Intent(mActivity, ReadNovelListActivity.class));
                break;
        }
    }

    private void loadList(final int start_page, String code) {
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.HOME_RECORDLIST)
                .addParams(ApiConstants.PAGE_SIZE,ApiConstants.PAGE_COUNT)
                .addParams(ApiConstants.PAGE,String.valueOf(start_page))
                .addParams(ApiConstants.BUSINESS_CODE,code)
                .build().execute(new BaseCallBack<HomeRecordList>() {
            @Override
            public void onLoadFail() {
                mRecyclerView.refreshComplete();

            }

            @Override
            public void onContentNull() {
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadSuccess(HomeRecordList response) {
                mRecyclerView.refreshComplete();

                if (response != null) {
                    if (start_page==START_PAGE){
                        if (mList != null) {
                            mList.clear();
                        }
                        mList.addAll(response.getData());
                        adapter.setData(mList);
                        time=2;
                    }else{
                        time++;
                        mList.addAll(response.getData());
                        adapter.addAll(mList);
                    }
                }

            }

            @Override
            public HomeRecordList parseNetworkResponse(String response) throws Exception {
                return JSON.parseObject(response, HomeRecordList.class);
            }
        });
    }


    //轮播图数据请求
    private void FindSlideShow() {
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.SLIDESHOW)
                .build().execute(new BaseCallBack<List<SlideShowData>>() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(List<SlideShowData> response) {
                if (response != null) {
                    setUrl(response);
                }

                isRefresh =true;
            }

            @Override
            public List<SlideShowData> parseNetworkResponse(String response) throws Exception {
                return JSON.parseArray(response, SlideShowData.class);
            }
        });
    }

    private void setUrl(List<SlideShowData> response) {
        if (response.size()>0)
            banner.setImages(response)
                    .setImageLoader(new GlideImageLoader())
                    .setOnBannerListener(this)
                    .start();
    }

    //板块获取
    private void loadTitle() {
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.SYSMODULES)
                .build().execute(new BaseCallBack<List<SysModule>>() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(List<SysModule> response) {

                if (response != null) {

                    setTitle(response);
                }

                isRefresh =true;

            }

            @Override
            public List<SysModule> parseNetworkResponse(String response) throws Exception {
                return JSON.parseArray(response,SysModule.class);
            }
        });
    }

    private void setTitle(List<SysModule> list) {


        if (slOverall != null) {
            slOverall.setData(list);
            slOverall.setOnSelectListener(this);
        }
    }

    //获取未读章节
    private void loadUnreadCount() {
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.READ_UNREAD)
                .addParams(ApiConstants.MOBILE, BaseApplication.getAppContext().getLoginUser().getMobile())
                .addParams(ApiConstants.UNIQUEID,BaseApplication.getAppContext().getDeviceId())
                .build().execute(new BaseCallBack<UnreadChapterCount>() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(UnreadChapterCount response) {

                if (response != null)
                adapter.setHeadNum(response);
            }

            @Override
            public UnreadChapterCount parseNetworkResponse(String response) throws Exception {
                return JSON.parseObject(response,UnreadChapterCount.class);
            }
        });
    }
    //下载
    private List<String> taskIds = new ArrayList<>();
    static final String TAG = "DownloadManager";
    private void download(String url) {
       // tv.setEnabled(false);
        DownloadTask task = new DownloadTask();
        // String fileName = MD5.MD5(url);

        task.setFileName(url);
        taskIds.add(url);
        task.setId(url);
        task.setSaveDirPath(mActivity.getExternalCacheDir().getPath() + "/");

        task.setUrl(url);
        downloadManager.addDownloadTask(task, new DownloadTaskListener() {
            @Override
            public void onPrepare(final DownloadTask downloadTask) {

                Log.d(TAG,"onPrepare");
            }

            @Override
            public void onStart(final DownloadTask downloadTask) {

                Log.d(TAG,"onStart");

            }

            @Override
            public void onDownloading(final DownloadTask downloadTask) {

                Log.d(TAG,"onDownloading");
            }

            @Override
            public void onPause(DownloadTask downloadTask) {

                Log.d(TAG,"onPause");

            }

            @Override
            public void onCancel(DownloadTask downloadTask) {

                Log.d(TAG,"onCancel");

            }

            @Override
            public void onCompleted(final DownloadTask downloadTask) {

                Log.d(TAG,"onCompleted");

            }

            @Override
            public void onError(DownloadTask downloadTask, int errorCode) {
                Log.d(TAG,"onError");

            }
        });

    }
}
