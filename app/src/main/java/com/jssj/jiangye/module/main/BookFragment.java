package com.jssj.jiangye.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiUrl;
import com.jssj.jiangye.api.BaseCallBack;
import com.jssj.jiangye.api.BuilderInstance;
import com.jssj.jiangye.base.BaseFragment;
import com.jssj.jiangye.data.SysModule;
import com.jssj.jiangye.widget.SelectionLayout;
import com.jssj.jiangye.widget.dialog.SendPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cc on 17-5-15.
 */
public class BookFragment extends BaseFragment implements View.OnClickListener, SelectionLayout.OnSelectListener {


    @BindView(R.id.big_event)
    TextView             mBigEvent;
    @BindView(R.id.slOverall)
    SelectionLayout      slOverall;
    @BindView(R.id.send_book)
    TextView             mSendBook;
    @BindView(R.id.toolbar)
    Toolbar              mToolbar;
    @BindView(R.id.tv_title)
    AppBarLayout         mTvTitle;
    @BindView(R.id.recycleview)
    XRecyclerView        mRecycleview;
    @BindView(R.id.fab_mode)
    FloatingActionButton mFabMode;
    protected List<SysModule> list = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadTitle();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fg_book;
    }

    @Override
    protected void initView() {

        mSendBook.setOnClickListener(this);
    }

    //加载头下拉列表
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_book:
                SendPopWindow sendPopWindow = new SendPopWindow(mActivity);
                sendPopWindow.showPopupWindow(view);
                break;
        }
    }



    @Override
    public void onSelect( int position, SysModule title) {

    }
}
