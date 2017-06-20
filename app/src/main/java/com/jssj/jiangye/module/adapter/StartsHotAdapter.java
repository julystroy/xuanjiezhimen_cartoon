package com.jssj.jiangye.module.adapter;

import android.content.Context;
import android.view.View;

import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseHolder;
import com.jssj.jiangye.base.DefaultAdapter;
import com.jssj.jiangye.data.StartsHotData;
import com.jssj.jiangye.module.holder.StartsHoteHolder;

/**
 * Created by cc on 17-6-12.
 */
public class StartsHotAdapter extends DefaultAdapter<StartsHotData> {

    public StartsHotAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseHolder<StartsHotData> getHolder(View v, int viewType) {
        return new StartsHoteHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_starts_hotlist;
    }
}
