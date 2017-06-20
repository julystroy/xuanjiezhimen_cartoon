package com.jssj.jiangye.data.res;

import com.jssj.jiangye.base.BaseResponse;
import com.jssj.jiangye.data.StartsHotData;

import java.util.List;

/**
 * Created by cc on 17-6-12.
 */
public class StartsHotDataResp extends BaseResponse {
    private List<StartsHotData> data;

    public List<StartsHotData> getData() {
        return data;
    }

    public void setData(List<StartsHotData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StartsHotDataResp{" +
                "data=" + data +
                '}';
    }
}
