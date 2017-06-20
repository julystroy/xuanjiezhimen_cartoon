package com.jssj.jiangye.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cc on 17-6-13.
 */
public class UnreadChapterCount implements Parcelable{
    /*{"code":1,"msg":"查询成功","data":{"mobile":null,"unreadNum":12,"isRead":null,"novelChapterId":null,"readpageNo":null}}*/
    private String unreadNum;//未读
    private String isRead;
    private String novelChapterId;//阅读到某一张
    private String readpageNo;//

    protected UnreadChapterCount(Parcel in) {
        unreadNum = in.readString();
        isRead = in.readString();
        novelChapterId = in.readString();
        readpageNo = in.readString();
    }

    public UnreadChapterCount() {
    }

    public static final Creator<UnreadChapterCount> CREATOR = new Creator<UnreadChapterCount>() {
        @Override
        public UnreadChapterCount createFromParcel(Parcel in) {
            return new UnreadChapterCount(in);
        }

        @Override
        public UnreadChapterCount[] newArray(int size) {
            return new UnreadChapterCount[size];
        }
    };

    public String getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(String unreadNum) {
        this.unreadNum = unreadNum;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getNovelChapterId() {
        return novelChapterId;
    }

    public void setNovelChapterId(String novelChapterId) {
        this.novelChapterId = novelChapterId;
    }

    public String getReadpageNo() {
        return readpageNo;
    }

    public void setReadpageNo(String readpageNo) {
        this.readpageNo = readpageNo;
    }

    @Override
    public String toString() {
        return "UnreadChapterCount{" +
                "unreadNum='" + unreadNum + '\'' +
                ", isRead='" + isRead + '\'' +
                ", novelChapterId='" + novelChapterId + '\'' +
                ", readpageNo='" + readpageNo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(unreadNum);
        parcel.writeString(isRead);
        parcel.writeString(novelChapterId);
        parcel.writeString(readpageNo);
    }
}
