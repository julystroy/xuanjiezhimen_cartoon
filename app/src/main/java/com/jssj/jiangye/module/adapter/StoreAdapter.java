package com.jssj.jiangye.module.adapter;

import android.content.Context;
import android.view.View;

import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseHolder;
import com.jssj.jiangye.base.DefaultAdapter;
import com.jssj.jiangye.data.StoreData;
import com.jssj.jiangye.module.holder.StoreHolder;

/**
 * Created by cc on 17-6-14.
 */
public class StoreAdapter extends DefaultAdapter<StoreData> {


    public StoreAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseHolder<StoreData> getHolder(View v, int viewType) {
        return new StoreHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_card;
    }
}
