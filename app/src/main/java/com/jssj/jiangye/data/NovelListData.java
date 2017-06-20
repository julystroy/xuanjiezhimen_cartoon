package com.jssj.jiangye.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cc on 17-6-8.
 */
public class NovelListData implements Parcelable {
    /* novelChapterId: "06a79f39959b4e2e8ccad6b62fe04075",//ID
                                          chapterCode: "NIGHT_1",//章节编码
                                          chapterTitle: "第一百零二章 小鸡炖蘑菇",//章节标题
                                          pageNo: "102",//第几张
                                          chapterContent: "天王盖地虎，宝塔镇河妖",//章节内容（富文本格式）*/
    private String novelChapterId;
    private String chapterCode;
    private String chapterTitle;
    private String pageNo;
    private String chapterContent;

    public NovelListData() {

    }

    protected NovelListData(Parcel in) {
        novelChapterId = in.readString();
        chapterCode = in.readString();
        chapterTitle = in.readString();
        pageNo = in.readString();
        chapterContent = in.readString();
    }

    public static final Creator<NovelListData> CREATOR = new Creator<NovelListData>() {
        @Override
        public NovelListData createFromParcel(Parcel in) {
            return new NovelListData(in);
        }

        @Override
        public NovelListData[] newArray(int size) {
            return new NovelListData[size];
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

    @Override
    public String toString() {
        return "NovelListData{" +
                "novelChapterId='" + novelChapterId + '\'' +
                ", chapterCode='" + chapterCode + '\'' +
                ", chapterTitle='" + chapterTitle + '\'' +
                ", pageNo='" + pageNo + '\'' +
                ", chapterContent='" + chapterContent + '\'' +
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
    }
}
