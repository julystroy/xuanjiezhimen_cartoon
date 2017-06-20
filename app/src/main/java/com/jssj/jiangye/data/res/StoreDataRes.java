package com.jssj.jiangye.data.res;

import com.jssj.jiangye.base.BaseResponse;
import com.jssj.jiangye.data.StoreData;

import java.util.List;

/**
 * Created by cc on 17-6-14.
 */
public class StoreDataRes extends BaseResponse {
    private List<StoreData> data;

    public List<StoreData> getData() {
        return data;
    }

    public void setData(List<StoreData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StoreDataRes{" +
                "data=" + data +
                '}';
    }
}
