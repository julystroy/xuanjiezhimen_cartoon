package com.jssj.jiangye.data.res;

import com.jssj.jiangye.base.BaseResponse;
import com.jssj.jiangye.data.NovelChapterList;

import java.util.List;

/**
 * Created by cc on 17-6-13.
 */
public class NovelChapterRes extends BaseResponse {
    private List<NovelChapterList> data;

    public List<NovelChapterList> getData() {
        return data;
    }

    public void setData(List<NovelChapterList> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NovelChapterRes{" +
                "data=" + data +
                '}';
    }
}
