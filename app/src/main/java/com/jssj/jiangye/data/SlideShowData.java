package com.jssj.jiangye.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cc on 17-6-6.
 */
public class SlideShowData implements Parcelable{
    /*
    * adverItemId: "06a79f39959b4e2e8ccad6b62fe04075",//ID
                      adverImageUrl: "http://oqjj5mrzr.bkt.clouddn.com/VcS6nHBpX2wS1495881316397.jpg",//封面图片URL
                      adverImageLink: "/action7",//调转链接
                      sortIndex: "1",//序号
                      tagParam: "活动",//标签
                      busDataId: "11179f39959b4e2e8ccad6b62fe04075",//关联业务ID
                      parentBusName: "活动",//模块
                      subBusName: "NBA活动投票",//活动主题
                      publishTime: "2017-10-10 22:33:11"//发布时间
    */
    private String adverItemId;
    private String adverImageUrl;
    private String adverImageLink;
    private String sortIndex;
    private String tagParam;
    private String busDataId;
    private String parentBusName;
    private String subBusName;
    private String publishTime;

    public SlideShowData() {
    }

    protected SlideShowData(Parcel in) {
        adverItemId = in.readString();
        adverImageUrl = in.readString();
        adverImageLink = in.readString();
        sortIndex = in.readString();
        tagParam = in.readString();
        busDataId = in.readString();
        parentBusName = in.readString();
        subBusName = in.readString();
        publishTime = in.readString();
    }

    public static final Creator<SlideShowData> CREATOR = new Creator<SlideShowData>() {
        @Override
        public SlideShowData createFromParcel(Parcel in) {
            return new SlideShowData(in);
        }

        @Override
        public SlideShowData[] newArray(int size) {
            return new SlideShowData[size];
        }
    };

    public String getAdverItemId() {
        return adverItemId;
    }

    public void setAdverItemId(String adverItemId) {
        this.adverItemId = adverItemId;
    }

    public String getAdverImageUrl() {
        return adverImageUrl;
    }

    public void setAdverImageUrl(String adverImageUrl) {
        this.adverImageUrl = adverImageUrl;
    }

    public String getAdverImageLink() {
        return adverImageLink;
    }

    public void setAdverImageLink(String adverImageLink) {
        this.adverImageLink = adverImageLink;
    }

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getTagParam() {
        return tagParam;
    }

    public void setTagParam(String tagParam) {
        this.tagParam = tagParam;
    }

    public String getBusDataId() {
        return busDataId;
    }

    public void setBusDataId(String busDataId) {
        this.busDataId = busDataId;
    }

    public String getParentBusName() {
        return parentBusName;
    }

    public void setParentBusName(String parentBusName) {
        this.parentBusName = parentBusName;
    }

    public String getSubBusName() {
        return subBusName;
    }

    public void setSubBusName(String subBusName) {
        this.subBusName = subBusName;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "SlideShowData{" +
                "adverItemId='" + adverItemId + '\'' +
                ", adverImageUrl='" + adverImageUrl + '\'' +
                ", adverImageLink='" + adverImageLink + '\'' +
                ", sortIndex='" + sortIndex + '\'' +
                ", tagParam='" + tagParam + '\'' +
                ", busDataId='" + busDataId + '\'' +
                ", parentBusName='" + parentBusName + '\'' +
                ", subBusName='" + subBusName + '\'' +
                ", publishTime='" + publishTime + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(adverItemId);
        parcel.writeString(adverImageUrl);
        parcel.writeString(adverImageLink);
        parcel.writeString(sortIndex);
        parcel.writeString(tagParam);
        parcel.writeString(busDataId);
        parcel.writeString(parentBusName);
        parcel.writeString(subBusName);
        parcel.writeString(publishTime);
    }
}
