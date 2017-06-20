package com.jssj.jiangye.module.detail;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jssj.jiangye.BaseApplication;
import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.api.ApiUrl;
import com.jssj.jiangye.api.BaseCallBack;
import com.jssj.jiangye.api.BuilderInstance;
import com.jssj.jiangye.api.RequestMethod;
import com.jssj.jiangye.base.BaseActivity;
import com.jssj.jiangye.data.NovelListData;
import com.jssj.jiangye.data.res.NovelListDataResp;
import com.jssj.jiangye.module.adapter.NovelReadAdaper;
import com.jssj.jiangye.utils.ScreenUtils;
import com.jssj.jiangye.utils.ShareprefenceHelper;
import com.jssj.jiangye.utils.ToastUitl;
import com.jssj.jiangye.widget.NormalTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by cc on 17-6-8.
 */
public class ReadNovelActivity extends BaseActivity /*implements XRecyclerView.LoadingListener*/ {
    @BindView(R.id.titlebar)
    NormalTitleBar mTitlebar;
    @BindView(R.id.toolbar)
    Toolbar        mToolbar;
    @BindView(R.id.recycleview)
    XRecyclerView  mRecycleview;
    @BindView(R.id.tvFontsizeMinus)
    TextView       mTvFontsizeMinus;
    @BindView(R.id.seekbarFontSize)
    SeekBar        seekbarFontSize;
    @BindView(R.id.tvFontsizePlus)
    TextView       mTvFontsizePlus;
    @BindView(R.id.rlReadAaSet)
    LinearLayout   rlReadAaSet;
    @BindView(R.id.tv_title)
    AppBarLayout   mTvTitle;
    @BindView(R.id.novel_download)
    TextView       mNovelDownload;
    @BindView(R.id.novel_read)
    TextView       mNovelRead;
    @BindView(R.id.novel_list)
    TextView       mNovelList;

    private NovelReadAdaper novelListAdpater;

    private List<NovelListData> mNovelListDatas = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getReadList(START_PAGE);
        //String stringExtra = getIntent().getStringExtra(ApiConstants.TYPE_READ);//// FIXME: 17-6-13 已读章节

        getIntent().getStringExtra(ApiConstants.TYPE_READ);

    }

    @Override
    public int getLayoutId() {
        return R.layout.actiity_readnovel;
    }

    @Override
    public void initView() {
        SetTranslanteBar();
        mTitlebar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mTitlebar.setTitleText("小说");
        mTitlebar.setLeftTitle("返回");
        mTitlebar.setRightTitleVisibility(true);
        mTitlebar.setRightTitle("设置");
        mTitlebar.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isVisible(rlReadAaSet)) {
                    gone(rlReadAaSet);
                } else {
                    visible(rlReadAaSet);
                }
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(ReadNovelActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleview.setLayoutManager(layoutManager);
        novelListAdpater = new NovelReadAdaper(ReadNovelActivity.this);
        mRecycleview.setAdapter(novelListAdpater);
        mRecycleview.setPullRefreshEnabled(false);
        mRecycleview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isVisible(rlReadAaSet)) {
                    gone(rlReadAaSet);
                }
                return false;
            }
        });


        initAset();
    }
//字体大小设置
    private void initAset() {

        seekbarFontSize.setMax(10);
        //int fontSizePx = SettingManager.getInstance().getReadFontSize(bookId);
        int fontSizePx = ShareprefenceHelper.getIntergerSF(this,ApiConstants.FONTSIZE);
        int progress = (int) ((ScreenUtils.pxToDpInt(fontSizePx) - 12) / 1.7f);
        seekbarFontSize.setProgress(progress);
        seekbarFontSize.setOnSeekBarChangeListener(new SeekBarChangeListener());
    }

    @OnClick(R.id.tvFontsizeMinus)
    public void fontsizeMinus() {
        calcFontSize(seekbarFontSize.getProgress() - 1);
    }

    @OnClick(R.id.tvFontsizePlus)
    public void fontsizePlus() {
        calcFontSize(seekbarFontSize.getProgress() + 1);
    }

    //设置字体大小
    private void calcFontSize(int progress) {
        // progress range 1 - 10
        if (progress >= 0 && progress <= 10) {
            seekbarFontSize.setProgress(progress);
            novelListAdpater.setFontSize(ScreenUtils.dpToPxInt(6 + 1.7f * progress));
        }
    }



    int num = 2;

    private void getReadList(final int page) {

        String mobile = BaseApplication.getAppContext().getLoginUser().getMobile();
        if (TextUtils.isEmpty(mobile)) {
            return;
        }
        //从网络获取
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.READ_LIST)
                .addParams(ApiConstants.MOBILE, mobile)
                .addParams(ApiConstants.PAGE_SIZE, ApiConstants.PAGE_COUNT)
                .addParams(ApiConstants.PAGE, String.valueOf(page))
                .build().execute(new BaseCallBack<NovelListDataResp>() {
            @Override
            public void onLoadFail() {
if (mRecycleview !=null)
                mRecycleview.refreshComplete();
            }

            @Override
            public void onContentNull() {
                mRecycleview.refreshComplete();
            }

            @Override
            public void onLoadSuccess(NovelListDataResp response) {

                mRecycleview.refreshComplete();
                if (response == null)
                    ToastUitl.showLong("更新中...");
                if (START_PAGE == page) {
                    if (mNovelListDatas != null)
                        mNovelListDatas.clear();
                    mNovelListDatas.addAll(response.getData());
                    novelListAdpater.setData(mNovelListDatas);
                } else {
                    mRecycleview.loadMoreComplete();
                    num++;
                    mNovelListDatas.addAll(response.getData());
                    novelListAdpater.setData(mNovelListDatas);
                }


            }

            @Override
            public NovelListDataResp parseNetworkResponse(String response) throws Exception {
                return JSON.parseObject(response, NovelListDataResp.class);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        String stringSF = ShareprefenceHelper.getStringSF(ReadNovelActivity.this, ApiConstants.TYPE_READ_LASTCHAPTER);//// FIXME: 17-6-13 已读章节
        if (TextUtils.isEmpty(stringSF))
        RequestMethod.MarkNovelChapterReaded(stringSF);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // FIXME: 17-6-13 发送已读章节
    }

    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (seekBar.getId() == seekbarFontSize.getId() && fromUser) {
                calcFontSize(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }



}
