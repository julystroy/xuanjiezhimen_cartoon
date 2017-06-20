package com.jssj.jiangye.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cc on 17-6-7.
 */
public class HomeRecord implements Parcelable{
    /* businessRecordId: "06a79f39959b4e2e8ccad6b62fe04075",//ID
                                          businessTitle: "51刘若英来了",//业务标题
                                          businessImgUrl: "http://123.jpg",//业务封面图片
                                          businessTag: "明星",//业务标签
                                          commentNum: "2000",//评论数
                                          businessCode: "BUS_ACTIVE",//业务类型
                                          createTime: "2017-10-10 22:10:10"//发布时间*/

    private String businessRecordId;
    private String businessTitle;
    private String businessImgUrl;
    private String commentNum;
    private String businessCode;
    private String createTime;
    private String businessTag;


    public HomeRecord() {
    }

    protected HomeRecord(Parcel in) {
        businessRecordId = in.readString();
        businessTitle = in.readString();
        businessImgUrl = in.readString();
        commentNum = in.readString();
        businessCode = in.readString();
        createTime = in.readString();
        businessTag = in.readString();
    }

    public static final Creator<HomeRecord> CREATOR = new Creator<HomeRecord>() {
        @Override
        public HomeRecord createFromParcel(Parcel in) {
            return new HomeRecord(in);
        }

        @Override
        public HomeRecord[] newArray(int size) {
            return new HomeRecord[size];
        }
    };

    public String getBusinessRecordId() {
        return businessRecordId;
    }

    public void setBusinessRecordId(String businessRecordId) {
        this.businessRecordId = businessRecordId;
    }

    public String getBusinessTitle() {
        return businessTitle;
    }

    public void setBusinessTitle(String businessTitle) {
        this.businessTitle = businessTitle;
    }

    public String getBusinessImgUrl() {
        return businessImgUrl;
    }

    public void setBusinessImgUrl(String businessImgUrl) {
        this.businessImgUrl = businessImgUrl;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag;
    }

    @Override
    public String toString() {
        return "HomeRecord{" +
                "businessRecordId='" + businessRecordId + '\'' +
                ", businessTitle='" + businessTitle + '\'' +
                ", businessImgUrl='" + businessImgUrl + '\'' +
                ", commentNum='" + commentNum + '\'' +
                ", businessCode='" + businessCode + '\'' +
                ", createTime='" + createTime + '\'' +
                ", businessTag='" + businessTag + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(businessRecordId);
        parcel.writeString(businessTitle);
        parcel.writeString(businessImgUrl);
        parcel.writeString(commentNum);
        parcel.writeString(businessCode);
        parcel.writeString(createTime);
        parcel.writeString(businessTag);
    }
}
