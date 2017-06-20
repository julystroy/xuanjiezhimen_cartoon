package com.jssj.jiangye.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cc on 17-6-8.
 */
public class StartsNewsData implements Parcelable{
    /*
    * starNewsId: "06a79f39959b4e2e8ccad6b62fe04075",//ID
                                          newsTitle: "51刘若英来了",//业务标题
                                          newsCoverUrl: "http://123.jpg",//业务封面图片
                                          businessTag: "明星",//业务标签
                                          commentNum: "2000",//评论数
                                          likes: "100",//点赞数
                                          isRecomm: "1",1.推荐 2.不推荐
                                          recommTime: "2017-10-10 10:10"
                                          businessCode: "BUS_ACTIVE",//业务类型
                                          publishTime: "1010 22:10",//发布时间
                                          newsContent: "我是小沈阳",//资讯内容
                                          starInfoId: "111111111"明星ID*/
    private String starNewsId;
    private String newsTitle;
    private String newsCoverUrl;
    private String businessTag;
    private String likes;
    private String isRecomm;
    private String recommTime;
    private String businessCode;
    private String publishTime;
    private String newsContent;
    private String commentNum;
    private String starInfoId;

    public StartsNewsData() {}

    protected StartsNewsData(Parcel in) {
        starNewsId = in.readString();
        newsTitle = in.readString();
        newsCoverUrl = in.readString();
        businessTag = in.readString();
        likes = in.readString();
        isRecomm = in.readString();
        recommTime = in.readString();
        businessCode = in.readString();
        publishTime = in.readString();
        newsContent = in.readString();
        commentNum = in.readString();
        starInfoId = in.readString();
    }

    public static final Creator<StartsNewsData> CREATOR = new Creator<StartsNewsData>() {
        @Override
        public StartsNewsData createFromParcel(Parcel in) {
            return new StartsNewsData(in);
        }

        @Override
        public StartsNewsData[] newArray(int size) {
            return new StartsNewsData[size];
        }
    };

    public String getStarNewsId() {
        return starNewsId;
    }

    public void setStarNewsId(String starNewsId) {
        this.starNewsId = starNewsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsCoverUrl() {
        return newsCoverUrl;
    }

    public void setNewsCoverUrl(String newsCoverUrl) {
        this.newsCoverUrl = newsCoverUrl;
    }

    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getIsRecomm() {
        return isRecomm;
    }

    public void setIsRecomm(String isRecomm) {
        this.isRecomm = isRecomm;
    }

    public String getRecommTime() {
        return recommTime;
    }

    public void setRecommTime(String recommTime) {
        this.recommTime = recommTime;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getStarInfoId() {
        return starInfoId;
    }

    public void setStarInfoId(String starInfoId) {
        this.starInfoId = starInfoId;
    }

    @Override
    public String toString() {
        return "StartsNewsData{" +
                "starNewsId='" + starNewsId + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsCoverUrl='" + newsCoverUrl + '\'' +
                ", businessTag='" + businessTag + '\'' +
                ", likes='" + likes + '\'' +
                ", isRecomm='" + isRecomm + '\'' +
                ", recommTime='" + recommTime + '\'' +
                ", businessCode='" + businessCode + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", commentNum='" + commentNum + '\'' +
                ", starInfoId='" + starInfoId + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(starNewsId);
        parcel.writeString(newsTitle);
        parcel.writeString(newsCoverUrl);
        parcel.writeString(businessTag);
        parcel.writeString(likes);
        parcel.writeString(isRecomm);
        parcel.writeString(recommTime);
        parcel.writeString(businessCode);
        parcel.writeString(publishTime);
        parcel.writeString(newsContent);
        parcel.writeString(commentNum);
        parcel.writeString(starInfoId);
    }
}
