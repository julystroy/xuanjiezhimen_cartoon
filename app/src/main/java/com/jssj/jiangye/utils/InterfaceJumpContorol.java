package com.jssj.jiangye.utils;

import android.content.Context;
import android.content.Intent;

import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.module.detail.ArticalReadDetailActivity;
import com.jssj.jiangye.module.detail.ReadNovelActivity;
import com.jssj.jiangye.module.detail.StarInfoDetailActivity;

/**
 * Created by cc on 17-5-25.
 */
public class InterfaceJumpContorol {
    /*
    * 各种界面跳转
    * @parm 1 模块类型   2  模块详情界面id
    * */
    public static void InterfaceJump(Context context,int type, String id) {
        switch (type) {
            case  ApiConstants.ID_TYPE_COMMENT://0  评论

                break;
            case ApiConstants.ID_TYPE_READ://2    原著阅读
                Intent intent2 = new Intent(context, ReadNovelActivity.class);
                intent2.putExtra(ApiConstants.TYPE_READ,id);
                context.startActivity(intent2);
                break;
            case ApiConstants.ID_TYPE_ARTICAL_READ://3    原著解读
                Intent intent = new Intent(context, ArticalReadDetailActivity.class);
                intent.putExtra(ApiConstants.TYPE_INFORMATION_STAR,id);
                context.startActivity(intent);
                break;
            case ApiConstants.ID_TYPE_NEWS://4 星影资讯
                Intent intent4 = new Intent(context, StarInfoDetailActivity.class);
                intent4.putExtra(ApiConstants.DETAIL_ACTIVITY,id);
                context.startActivity(intent4);
                break;
            case  ApiConstants.ID_TYPE_CHARM://5 星影魅力
                //固定界面跳转
                break;
            case  ApiConstants.ID_TYPE_ACTION://6 活动

                break;



        }
    }
/*/*
*
*  * BUS_STAR //明星
	 * BUS_STAR_NEWS //明星资讯
	 * BUS_ARTICLE //原著解读
	 * BUS_ACTIVITY //活动
*/
    public static String getUrl(String code) {

     return null;
    }

}
