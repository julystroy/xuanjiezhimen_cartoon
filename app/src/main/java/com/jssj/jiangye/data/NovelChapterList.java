package com.jssj.jiangye.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cc on 17-6-13.
 */
public class NovelChapterList implements Parcelable {
    /*novelChapterId: "06a79f39959b4e2e8ccad6b62fe04075",//ID
                                          chapterCode: "NIGHT_1",//章节编码
                                          chapterTitle: "第一百零二章 小鸡炖蘑菇",//章节标题
                                          pageNo: "102",//第几张
                                          chapterContent: "天王盖地虎，宝塔镇河妖",//章节内容（富文本格式）
                                          isRead: "1",//标记章节目录是否已读（1.已读0.未读）
                                          lastRead: "1"//标记上次光标已读的章节位置（1.上次已经读到次位置0.未标示）*/
    private String novelChapterId;
    private String chapterCode;
    private String chapterTitle;
    private String pageNo;
    private String chapterContent;
    private String isRead;
    private String lastRead;

    public NovelChapterList() {
    }

    protected NovelChapterList(Parcel in) {
        novelChapterId = in.readString();
        chapterCode = in.readString();
        chapterTitle = in.readString();
        pageNo = in.readString();
        chapterContent = in.readString();
        isRead = in.readString();
        lastRead = in.readString();
    }

    public static final Creator<NovelChapterList> CREATOR = new Creator<NovelChapterList>() {
        @Override
        public NovelChapterList createFromParcel(Parcel in) {
            return new NovelChapterList(in);
        }

        @Override
        public NovelChapterList[] newArray(int size) {
            return new NovelChapterList[size];
        }
    };

    public String getNovelChapterId() {
        return novelChapterId;
    }

    public void setNovelChapterId(String novelChapterId) {
        this.novelChapterId = novelChapterId;
    }

    public String getChapterCode() {
        return chapterCode;
    }

    public void setChapterCode(String chapterCode) {
        this.chapterCode = chapterCode;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getLastRead() {
        return lastRead;
    }

    public void setLastRead(String lastRead) {
        this.lastRead = lastRead;
    }

    @Override
    public String toString() {
        return "NovelChapterList{" +
                "novelChapterId='" + novelChapterId + '\'' +
                ", chapterCode='" + chapterCode + '\'' +
                ", chapterTitle='" + chapterTitle + '\'' +
                ", pageNo='" + pageNo + '\'' +
                ", chapterContent='" + chapterContent + '\'' +
                ", isRead='" + isRead + '\'' +
                ", lastRead='" + lastRead + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(novelChapterId);
        parcel.writeString(chapterCode);
        parcel.writeString(chapterTitle);
        parcel.writeString(pageNo);
        parcel.writeString(chapterContent);
        parcel.writeString(isRead);
        parcel.writeString(lastRead);
    }
}
