package com.jssj.jiangye.data.res;

import com.jssj.jiangye.base.BaseResponse;
import com.jssj.jiangye.data.StartsNewsData;

import java.util.List;

/**
 * Created by cc on 17-6-8.
 */
public class StartsNesListResp extends BaseResponse {
    private List<StartsNewsData> data;

    public List<StartsNewsData> getData() {
        return data;
    }

    public void setData(List<StartsNewsData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StartsNesListResp{" +
                "data=" + data +
                '}';
    }
}
