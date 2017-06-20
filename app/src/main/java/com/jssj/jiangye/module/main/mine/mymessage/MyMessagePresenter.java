package com.jssj.jiangye.module.main.mine.mymessage;

public interface MyMessagePresenter {
    void loadMyMessage(int page, int pageSize);

    void deleteMyMessage(int messageId, int position);
}
