package com.jssj.jiangye.module.main.mine.mymessage;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.jssj.jiangye.api.ApiUrl;
import com.jssj.jiangye.api.BaseCallBack;
import com.jssj.jiangye.api.BuilderInstance;

/**
 */

public class MyMessagePresenterImpl implements MyMessagePresenter {
    private MyMessageView view;

    public MyMessagePresenterImpl(MyMessageView view) {
        this.view = view;
    }

    @Override
    public void loadMyMessage(final int page, int pageSize) {
        if (isFirstPage(page)) {
            //view.showLoadDataView();
        }

       /* BuilderInstance.getInstance().getGetBuilderInstance(ApiUrl.MY_MESSAGE)
                .addParams(ApiConstants.PAGE, String.valueOf(page))
                .addParams(ApiConstants.PAGE_SIZE, ApiConstants.PAGE_COUNT)
                .build().execute(new BaseCallBack<MyMessageResp>() {

            @Override
            public void onLoadFail() {
                if (isFirstPage(page)) {
                    view.showErrorView();
                } else {
                    view.showLoadMoreFailedView();
                }
            }
            @Override
            public void onContentNull() {

            }
            @Override
            public void onLoadSuccess(MyMessageResp response) {
                view.hideLoadDataView();

                if (isFirstPage(page)) {
                    if (CollectionUtils.isEmpty(response.getList())) {
                        view.shoeEmptyView();
                    } else {
                        view.renderList(response);
                    }
                } else {
                    view.renderListMore(response);
                }
            }

            @Override
            public MyMessageResp parseNetworkResponse(String response) throws Exception {
                return JSON.parseObject(response, MyMessageResp.class);

            }

        });*/
    }

    @Override
    public void deleteMyMessage(int messageId, final int position) {
        view.showDeleteWaitDialog();

        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.DELETE_COMMENT)
                .addParams("id", String.valueOf(messageId))
                .build().execute(new BaseCallBack<String>() {

            @Override
            public void onLoadFail() {
                view.hideDeleteWaitDialog();
                if (!TextUtils.isEmpty(getMessage())) {

                }
            }
            @Override
            public void onContentNull() {

            }
            @Override
            public void onLoadSuccess(String response) {
                view.hideDeleteWaitDialog();
                view.successDeleteMessage(position);
            }

            @Override
            public String parseNetworkResponse(String response) throws Exception {
                return JSON.parseObject(response, String.class);
            }

        });

    }


    private boolean isFirstPage(int page) {
        return page <= 1;
    }
}
