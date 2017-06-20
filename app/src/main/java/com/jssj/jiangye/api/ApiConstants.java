package com.jssj.jiangye.api;

import com.jssj.jiangye.BaseApplication;
import com.jssj.jiangye.utils.FileUtils;

/**
 * Created by cc on 17-5-8.
 */
public class ApiConstants {

    public static final String CARTOON      ="CARTOON" ;
    public static final String HTTP_CODE    = "code";
    public static final String HTTP_MESSAGE = "msg";
    public static final String HTTP_RESULT  = "data";
    public static final String FOLOAT          = "hide";
    public static final String PAGE_SIZE = "pageSize";

    public  static final String TYPE_INFORMATION_STAR   = "information_star";//咨讯
    public  static final String TYPE_CHARM_STAR   = "charm_star";//魅力榜
    public  static final String TYPE_ACTIV_DETAIL  = "detail_activ";//活动详情
    public static final String DETAIL_ACTIVITY       = "detail_information";
    //##########################Title展开项  start
    public static final int ID_TYPE_COMMENT =  0;
    public  static final int    ID_TYPE_BOOK    = 1;//书有圈

    public  static final int    ID_TYPE_READ   = 2;//原著阅读
    public  static final int    ID_TYPE_ARTICAL_READ   = 3;//原著解读
    public  static final int    ID_TYPE_NEWS   = 4;//星影资讯
    public  static final int    ID_TYPE_CHARM   = 5;//星影魅力榜
    public  static final int    ID_TYPE_ACTION = 6;//活动
//    public static final int ID_TYPE_START   = 4;
//    public static final int ID_TYPE_HOT   = 5;
    public  static final  String TYPE_ALL       = "老笔斋";

    public  static final String TYPE_READ      = "原著解读";
    public  static final String TYPE_NEWS      = "剧组讯息";
    public  static final String TYPE_ACTION    = "活动";


    public  static final String TYPE_BOOK    = "书友圈";
    public  static final String TYPE_START    = "星影追踪";
    public  static final String TYPE_HOT    = "最热";

    public static final String REFRESH_HOME = "refresh_home";
    public static final String REFRESH_BOOK = "refresh_book";
    //##########################Title展开项  over

    public static final int MAX_PHOTO_SIZE        = 100;//图片大小
    public static final String COMPOSE            ="COMPOSE" ;
    public static final int  SEND                 =1<<2 ;
    public static final String FIRST_LOGIN        ="first_login" ;
    public static final String NICKNAME           = "nickname";
    public static final CharSequence BUS_ACTIVITY = "BUS_ACTIVITY";
    public static final String PAGE               = "page";
    public static final String BUSINESS_CODE      ="businessCode" ;
    public static final String MOBILE                = "mobile";
    public static final String PAGE_COUNT            = "10";
    //novel
    public static final String PATH_DATA             =FileUtils.createRootPath(BaseApplication.getAppContext()) + "/cache" ;
    public static final String PATH_TXT              = PATH_DATA + "/book/";
    public static final String READCONTENT           = "read_content";
    public static final String TYPE_READ_LASTCHAPTER = "last_read_chapter";
    public static final String TYPE_READ_LIST           = "read_list";//目录


    public static String PATH_EPUB                = PATH_DATA + "/epub";
    public static final String SUFFIX_ZIP         = ".zip";
    public static String PATH_CHM                 = PATH_DATA + "/chm";


    public static String UNIQUEID           ="uniqueId";//设备id
    public static String DEVICETYPE         ="deviceType";
    public static String IMGCODE            ="imgVerifyCode";


    //sp
    public static final String FONTSIZE              ="fontsize" ;
    public static final String CHAPTERID             = "reading";


}
