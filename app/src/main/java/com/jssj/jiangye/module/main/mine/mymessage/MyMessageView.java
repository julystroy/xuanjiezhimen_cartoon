package com.jssj.jiangye.module.main.mine.mymessage;


/**
 */

public interface MyMessageView /*extends IBaseView*/ {
   /* void renderList(MyMessageResp resp);

    void renderListMore(MyMessageResp resp);*/

    void showDeleteWaitDialog();

    void hideDeleteWaitDialog();

    void successDeleteMessage(int position);

}
