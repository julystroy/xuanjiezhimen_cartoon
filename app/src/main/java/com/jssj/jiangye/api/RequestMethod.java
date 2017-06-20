package com.jssj.jiangye.api;

import com.alibaba.fastjson.JSON;
import com.jssj.jiangye.BaseApplication;
import com.jssj.jiangye.data.UserInfo;
import com.jssj.jiangye.utils.ToastUitl;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by cc on 17-6-2.
 * 各个接口请求方法
 */
public class RequestMethod {


    //预注册   返回code=1   成功
    public static void PreRegister() {

        OkHttpUtils.post().url(ApiUrl.PRE_REGISTER)
                .addParams(ApiConstants.UNIQUEID, BaseApplication.getAppContext().getDeviceId())
                .addParams(ApiConstants.DEVICETYPE, "1")
                .build().execute(new BaseCallBack<UserInfo>() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(UserInfo response) {

                if (response != null)
                    BaseApplication.getAppContext().saveUserInfo(response);

                System.out.println(BaseApplication.getAppContext().getLoginUser().getToken() + "        ********222222");
            }

            @Override
            public UserInfo parseNetworkResponse(String response) throws Exception {
                return JSON.parseObject(response, UserInfo.class);
            }
        });
    }

    /*图形验证
    *     uniqueId - 设备ID
     *     imgVerifyCode - 图片验证码
     */
    public static void InitImagCode(String CODE) {
        if (CODE == null) {
            return;
        }

        BuilderInstance.getInstance()
        .getPostBuilderInstance(ApiUrl.INITIMGCODE)
       .addParams(ApiConstants.UNIQUEID, BaseApplication.getAppContext().getDeviceId())
        .addParams(ApiConstants.IMGCODE, CODE)
       .build().execute(new BaseCallBack() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(Object response) {
            }

            @Override
            public Object parseNetworkResponse(String response) throws Exception {

                return response;
            }
        });
    }

    //更新用户信息   性别  和  name
    public static void UpdataUseinfo(final int num, final String name) {
        ToastUitl.showLong("信息更改成功");
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.UPDATA_USEINFO)
                .addParams("userId", BaseApplication.getAppContext().getLoginUser().getId())
                .addParams("sex", String.valueOf(num))
                .addParams("nickName", name)
                .build().execute(new BaseCallBack() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(Object response) {

                UserInfo userInfo = new UserInfo();
                userInfo.setSex(String.valueOf(num));
                userInfo.setNickName(name);
                //BaseApplication.getAppContext().updateUserInfo(userInfo);
            }

            @Override
            public Object parseNetworkResponse(String response) throws Exception {
                return response;
            }
        });
    }


/*
* 标记已读目录章节
* */
    public static void MarkNovelReaded(String novelChapterId) {
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.MARK_NOVEL_READED)
                .addParams(ApiConstants.MOBILE,BaseApplication.getAppContext().getLoginUser().getMobile())
                .addParams("novelChapterId",novelChapterId)
                .build().execute(new BaseCallBack() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(Object response) {

            }

            @Override
            public Object parseNetworkResponse(String response) throws Exception {
                return response;
            }
        });

    }

    /*
* 标记已读内容章节
* pageNo  章节号
* */
    public static void MarkNovelChapterReaded(String pageNo) {
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.MARK_NOVEL_READED_CHAPTER)
                .addParams(ApiConstants.MOBILE,BaseApplication.getAppContext().getLoginUser().getMobile())
                .addParams("pageNo",pageNo)
                .build().execute(new BaseCallBack() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(Object response) {

            }

            @Override
            public Object parseNetworkResponse(String response) throws Exception {
                return response;
            }
        });

    }

    /*
    * like
    *   fromUserId - 点赞人id
        toUserId - 被点赞人id（如果是业务模块，而不是评论，即没有被点赞者，就不传）
        busType - 业务类型id （1=书友圈 2=原著阅读 3=原著解读 4=星影资讯 5=明星魅力，鲜花 6=活动）
        resourceId - 条目id （书友圈=对应的评论id,即commentId，其他模块式对应条目的id）
        likesNum - 点赞数量 （明星魅力献花是对应的数量，其他均为1）
*/
    public static void ClickLike(String fromUserId, String toUserId, String busType, String resourceId, String likesNum) {
        BuilderInstance.getInstance().getPostBuilderInstance(ApiUrl.LIKES)
                .addParams("fromUserId",fromUserId)
                .addParams("toUserId",toUserId)
                .addParams("busType",busType)
                .addParams("resourceId",resourceId)
                .addParams("likesNum",likesNum)
                .build().execute(new BaseCallBack() {
            @Override
            public void onLoadFail() {

            }

            @Override
            public void onContentNull() {

            }

            @Override
            public void onLoadSuccess(Object response) {

            }

            @Override
            public Object parseNetworkResponse(String response) throws Exception {
                return response;
            }
        });
    }


}
