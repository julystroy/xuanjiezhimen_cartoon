package com.jssj.jiangye.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cc on 17-6-12.
 */
public class ArticleData implements Parcelable {
    /*articleId: "06a79f39959b4e2e8ccad6b62fe04075",//ID
                      articleTitle: "关于将夜第一章",//原著解读标题
                      articleType: "1",//解读类型
                      articleCoverUrl: "http://122.jpg",//封面图URL
                      tagParam: "解读",//标签
                      articleContent: "第一章好看",//解读内容
                      publishTime: "03/01  10:00",//发布时间*/
    private String articleId;
    private String articleTitle;
    private String articleType;
    private String articleCoverUrl;
    private String tagParam;
    private String articleContent;
    private String publishTime;

    public ArticleData() {
    }

    protected ArticleData(Parcel in) {
        articleId = in.readString();
        articleTitle = in.readString();
        articleType = in.readString();
        articleCoverUrl = in.readString();
        tagParam = in.readString();
        articleContent = in.readString();
        publishTime = in.readString();
    }

    public static final Creator<ArticleData> CREATOR = new Creator<ArticleData>() {
        @Override
        public ArticleData createFromParcel(Parcel in) {
            return new ArticleData(in);
        }

        @Override
        public ArticleData[] newArray(int size) {
            return new ArticleData[size];
        }
    };

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleCoverUrl() {
        return articleCoverUrl;
    }

    public void setArticleCoverUrl(String articleCoverUrl) {
        this.articleCoverUrl = articleCoverUrl;
    }

    public String getTagParam() {
        return tagParam;
    }

    public void setTagParam(String tagParam) {
        this.tagParam = tagParam;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "ArticleData{" +
                "articleId='" + articleId + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleType='" + articleType + '\'' +
                ", articleCoverUrl='" + articleCoverUrl + '\'' +
                ", tagParam='" + tagParam + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", publishTime='" + publishTime + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(articleId);
        parcel.writeString(articleTitle);
        parcel.writeString(articleType);
        parcel.writeString(articleCoverUrl);
        parcel.writeString(tagParam);
        parcel.writeString(articleContent);
        parcel.writeString(publishTime);
    }
}
