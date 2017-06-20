package com.jssj.jiangye.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cc on 17-6-12.
 */
public class StartsHotData implements Parcelable{
    /*starInfoId: "06a79f39959b4e2e8ccad6b62fe04075",//ID
                                          starName: "德华",//名字
                                          starPhotoUrl: "http://123.jpg",//明星头像
                                          charmValues: "1000",//明星魅力值
                                          commentNum: "2000",//评论数
                                          starDesc: "大家好，我是德华",//明星简介*/
    private String starInfoId;
    private String starName;
    private String starPhotoUrl;
    private String charmValues;
    private String commentNum;
    private String starDesc;

    public StartsHotData() {}

    protected StartsHotData(Parcel in) {
        starInfoId = in.readString();
        starName = in.readString();
        starPhotoUrl = in.readString();
        charmValues = in.readString();
        commentNum = in.readString();
        starDesc = in.readString();
    }

    public static final Creator<StartsHotData> CREATOR = new Creator<StartsHotData>() {
        @Override
        public StartsHotData createFromParcel(Parcel in) {
            return new StartsHotData(in);
        }

        @Override
        public StartsHotData[] newArray(int size) {
            return new StartsHotData[size];
        }
    };

    public String getStarInfoId() {
        return starInfoId;
    }

    public void setStarInfoId(String starInfoId) {
        this.starInfoId = starInfoId;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public String getStarPhotoUrl() {
        return starPhotoUrl;
    }

    public void setStarPhotoUrl(String starPhotoUrl) {
        this.starPhotoUrl = starPhotoUrl;
    }

    public String getCharmValues() {
        return charmValues;
    }

    public void setCharmValues(String charmValues) {
        this.charmValues = charmValues;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getStarDesc() {
        return starDesc;
    }

    public void setStarDesc(String starDesc) {
        this.starDesc = starDesc;
    }

    @Override
    public String toString() {
        return "StartsHotData{" +
                "starInfoId='" + starInfoId + '\'' +
                ", starName='" + starName + '\'' +
                ", starPhotoUrl='" + starPhotoUrl + '\'' +
                ", charmValues='" + charmValues + '\'' +
                ", commentNum='" + commentNum + '\'' +
                ", starDesc='" + starDesc + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(starInfoId);
        parcel.writeString(starName);
        parcel.writeString(starPhotoUrl);
        parcel.writeString(charmValues);
        parcel.writeString(commentNum);
        parcel.writeString(starDesc);
    }
}
