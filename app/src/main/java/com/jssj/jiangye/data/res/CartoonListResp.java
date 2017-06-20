package com.jssj.jiangye.data.res;


import com.jssj.jiangye.base.BaseResponse;
import com.jssj.jiangye.data.Cartoon;

import java.util.List;

/**
 * 漫画列表
 * <p>
 * Created by David on 16/6/11.
 */
public class CartoonListResp extends BaseResponse {

    private List<Cartoon> list;

    public List<Cartoon> getList() {
        return list;
    }

    public void setList(List<Cartoon> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return super.toString() +
                "CartoonListResp{" +
                "list=" + list +
                '}';
    }
}
