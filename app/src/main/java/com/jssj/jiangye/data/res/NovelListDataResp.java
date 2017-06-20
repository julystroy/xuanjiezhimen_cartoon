package com.jssj.jiangye.data.res;

import com.jssj.jiangye.base.BaseResponse;
import com.jssj.jiangye.data.NovelListData;

import java.util.List;

/**
 * Created by cc on 17-6-8.
 */
public class NovelListDataResp extends BaseResponse {
    private List<NovelListData> data;

    public List<NovelListData> getData() {
        return data;
    }

    public void setData(List<NovelListData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NovelListDataResp{" +
                "data=" + data +
                '}';
    }
}
