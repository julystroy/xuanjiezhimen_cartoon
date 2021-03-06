package com.jssj.jiangye.module.main.mine;


import com.jssj.jiangye.data.AppVersion;

/**
 * Created by jinbangzhu on 7/22/16.
 */

public interface MineView {

    void showLoadingView();

    void hideLoadingView();

    void updateNickName(String name);

    void updateGender(int gender);

    void showTips(String message);

    void updateAvatar(String avatarUrl);

    void logoutSuccess();

    void processUpdate(AppVersion appVersion);

}
