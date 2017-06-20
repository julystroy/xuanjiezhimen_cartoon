package com.jssj.jiangye.api;

/**
 * Created by cc on 17-5-8.
 */
public class ApiUrl {
    /*
    * 存放app接口url
    * */

   public static final String BASE_URL = "http://10.0.0.5:8080/";
  //public static final String BASE_URL = "http://10.0.0.229:8080/";
 // public static final String BASE_URL = "http://10.0.0.228:8080/";


    public static final String PRE_REGISTER = BASE_URL + "V1/preRegister";
    public static final String INITIMGCODE = BASE_URL + "V1/initImgCode";
    public static final String SENDCODE = BASE_URL + "V1/sendCode";
    public static final String LOGIN = BASE_URL + "V1/login";
    public static final String POST_IMAGIN = BASE_URL + "V1/common/image/upload";//上传图片

    public static final String UPDATA_USEINFO = BASE_URL+"V1/user/savaBasicInfo";
    public static final String UPDATA_AVATAR  =BASE_URL+"" ;
    public static final String APP_LOGOUT     =BASE_URL + "" ;
    public static final String APP_VERSION       = BASE_URL +"";
    public static final String SLIDESHOW       = BASE_URL +"V1/adverItem/findSlideshows";
    public static final String SYSMODULES       = BASE_URL +"V1/index/getSysModules";
    public static final String REPORT       = BASE_URL +"report/v1/addReport";//举报
    public static final String HOME_RECORDLIST       = BASE_URL +"V1/index/findBusinessRecordList";//home
 //novel
    public static final String READ_UNREAD       = BASE_URL +"V1/novel/countUnread";
    public static final String READ_EXPLAIN       = BASE_URL +"/V1/article/getDetail";//原著解读
    public static final String READ_LIST       = BASE_URL +"V1/novel/findNovelChapterList";//目录
    public static final String SEND_NOVEL_COMMENT       = BASE_URL +"V1/novel/getDetail";//写评论
    public static final String MARK_NOVEL_READED       = BASE_URL +"V1/novel/markReadChapter";//已读目录章节
    public static final String MARK_NOVEL_READED_CHAPTER       = BASE_URL +"V1/novel/markLastReadChapter";//已读内容章节

    public static final String STARTS_NEWS       = BASE_URL +"V1/starNews/findStarNewsList";
    public static final String STARTS_NEWS_DETAIL       = BASE_URL +"V1/starNews/getDetail";

    public static final String STARTS_HOTS = BASE_URL + "V1/star/findHotStarList";
    public static final String STARTS_HOTS_DETAIL = BASE_URL + "V1/star/getDetail";//从list界面传data过去，未使用

    //mine
    public static final String MY_MESSAGE = BASE_URL + "v1/message/getMyMessage";

    //comment
    public static final String DELETE_COMMENT = BASE_URL +"v1/comment/deleteComment";

    //LIKE
    public static final String LIKES = BASE_URL + "v1/likes/likes";

    //store
    public static final String STORELIST = BASE_URL + "V1/props/findPropsList";
}
