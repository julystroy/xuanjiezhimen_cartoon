package com.jssj.jiangye.data.res;

import com.jssj.jiangye.base.BaseResponse;
import com.jssj.jiangye.data.HomeRecord;

import java.util.List;

/**
 * Created by cc on 17-6-7.
 */
public class HomeRecordList extends BaseResponse {
    private List<HomeRecord> data;

    public List<HomeRecord> getData() {
        return data;
    }

    public void setData(List<HomeRecord> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HomeRecordList{" +
                "data=" + data +
                '}';
    }
}
